FROM openjdk:8
EXPOSE 8021
WORKDIR usr/jpop
ADD ./book-service/target/bookservice-0.0.1-SNAPSHOT.jar bookservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "bookservice-0.0.1-SNAPSHOT.jar"]
