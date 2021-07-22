FROM openjdk:8
EXPOSE 8080
WORKDIR usr/jpop
COPY ./api-gateway-service/target/apigatewayservice-0.0.1-SNAPSHOT.jar apigatewayservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "apigatewayservice-0.0.1-SNAPSHOT.jar"]
