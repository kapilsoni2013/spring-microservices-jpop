# spring-microservices-jpop

#DockerFile build Commands
docker build -f user-service/Dockerfile -t jpop/user-service:0.0.1-SNAPSHOT -t jpop/user-service:latest .
docker build -f book-service/Dockerfile -t jpop/book-service:0.0.1-SNAPSHOT -t jpop/book-service:latest .
docker build -f api-gateway-service/Dockerfile -t jpop/gateway-service:0.0.1-SNAPSHOT -t jpop/gateway-service:latest .
docker build -f config-server/Dockerfile -t jpop/config-server-service:0.0.1-SNAPSHOT -t jpop/config-server-service:latest .
docker build -f eureka-server/Dockerfile -t jpop/eureka-server-service:0.0.1-SNAPSHOT -t jpop/eureka-server-service:latest .
docker build -f library-service/Dockerfile -t jpop/library-service:0.0.1-SNAPSHOT -t jpop/library-service:latest .

