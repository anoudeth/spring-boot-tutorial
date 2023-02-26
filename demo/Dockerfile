FROM openjdk:8-jdk-alpine
RUN echo "Asia/Bangkok" > /etc/timezone && date
WORKDIR /usr/apps
COPY target/*.jar ./
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $(ls | grep .jar -m 1)"]