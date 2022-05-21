FROM maven:3.6.1-jdk-11-slim AS backend
RUN mkdir -p /workspace
COPY src /workspace/src
COPY pom.xml /workspace
WORKDIR /workspace
RUN mvn -f pom.xml clean package

FROM openjdk:11.0-slim
EXPOSE 3000
COPY --from=backend /workspace/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
