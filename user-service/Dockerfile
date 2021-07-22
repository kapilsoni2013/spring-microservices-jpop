FROM openjdk:8
EXPOSE 8011
WORKDIR usr/jpop
ADD ./user-service/target/userservice-0.0.1-SNAPSHOT.jar userservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "userservice-0.0.1-SNAPSHOT.jar"]
