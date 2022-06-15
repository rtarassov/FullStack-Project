# define base docker image
FROM openjdk:17
LABEL maintainer="FullStack-Project-DOCKER"
ADD build/libs/project-0.0.1-SNAPSHOT.jar FullStack-Project-DOCKER.jar
ENTRYPOINT ["java", "-jar", "FullStack-Project-DOCKER.jar"]