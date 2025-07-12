#echo "==> switch to java 11"
#source ~/.java11

echo "==> check mvn and java version"
java -version && mvn -v

echo "==> running command 'mvn clean compile package'"
mvn clean compile package -DskipTests

echo "==> docker build on Apple silicon"
docker build --platform linux/amd64 -f Dockerfile -t service-b:latest .

#echo "==> docker push to server"
#docker push ubuntu-prdapi.indochinabank.com:5000/teller:latest

#echo "==> copy props to server: " $env

#echo "==> copy props to server pro"
#scp -r src/main/resources/* admindev@10.10.20.68:/home/admindev/docker/app/teller/config_props

#echo "==> copy props to server dev"
#scp -r src/main/resources/* admindev@10.10.20.154:/home/admindev/docker/app/teller/config_props

echo "...success..."
