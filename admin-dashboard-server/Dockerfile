FROM openjdk:8
EXPOSE 8080
WORKDIR usr/jpop
COPY ./admin-dashboard-server/target/admindashboardserver-0.0.1-SNAPSHOT.jar admindashboardserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "admindashboardserver-0.0.1-SNAPSHOT.jar"]
