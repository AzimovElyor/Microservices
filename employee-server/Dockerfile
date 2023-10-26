FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=build/libs/employee-server-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} employee-service
ENTRYPOINT ["java","-jar","employee-service"]
