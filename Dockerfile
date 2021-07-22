FROM openjdk:8
ADD ./target/citi_scrum_master_assistant-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar"]
