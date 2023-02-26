#--- base image to build a JRE
FROM amazoncorretto:17.0.3-alpine as corretto-jdk

#--- required for strip-debug to work
RUN apk add --no-cache binutils

#--- Build small JRE image
RUN $JAVA_HOME/bin/jlink \
         --verbose \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /customjre

#--- main app image
FROM alpine:latest
RUN echo "Asia/Bangkok" > /etc/timezone && date
ENV JAVA_HOME=/jre
ENV PATH="${JAVA_HOME}/bin:${PATH}"

#--- copy JRE from the base image
COPY --from=corretto-jdk /customjre $JAVA_HOME

#--- Add app user
#ARG APPLICATION_USER=appuser
#RUN adduser --no-create-home -u 1000 -D $APPLICATION_USER

#--- Configure working directory
#RUN mkdir /app && \
#    chown -R $APPLICATION_USER /app

#USER 1000

#COPY --chown=1000:1000 ./target/teller-service.jar /app/teller-service.jar
#WORKDIR /app

#--- EXPOSE 8080
#ENTRYPOINT [ "/jre/bin/java", "-jar", "/app/teller-service.jar" ]

WORKDIR /usr/apps
COPY target/*.jar ./
#RUN mkdir ./config

ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $(ls | grep .jar -m 1)"]