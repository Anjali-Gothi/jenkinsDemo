FROM openjdk-11
EXPOSE 8086
ADD target/devops-integration.jar devops-integration.jar
ENTRYPOINT["java", "-jar", "/devops-integration.jar"]
