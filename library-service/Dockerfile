FROM openjdk:8
EXPOSE 8031
WORKDIR usr/jpop
COPY ./library-service/target/libraryservice-0.0.1-SNAPSHOT.jar libraryservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "libraryservice-0.0.1-SNAPSHOT.jar"]
