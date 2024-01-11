FROM openjdk-11
EXPOSE 8086
ADD target/demoWebClient-0.0.1-SNAPSHOT.jar devops-integration.jar
ENTRYPOINT["java","-jar","/devops-integration.jar"]
