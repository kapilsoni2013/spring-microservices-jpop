FROM openjdk:8
EXPOSE 8888
WORKDIR usr/jpop
COPY ./config-server/target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar
COPY config-store config-store
ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]