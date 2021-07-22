FROM openjdk:8
EXPOSE 8761
WORKDIR usr/jpop
COPY ./eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "eureka-server-0.0.1-SNAPSHOT.jar"]
