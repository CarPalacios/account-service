FROM openjdk:11
COPY "./target/account-service-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8096
ENTRYPOINT ["java","-jar","app.jar"]