From openjdk:8
copy ./target/ConfigManager-0.0.1-SNAPSHOT.jar ConfigManager-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","ConfigManager-0.0.1-SNAPSHOT.jar"]