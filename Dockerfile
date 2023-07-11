FROM openjdk:11
EXPOSE 8080
ADD target/customer-profile-docker.jar customer-profile-docker.jar
ENTRYPOINT ["java", "-jar", "/customer-profile-docker.jar"]