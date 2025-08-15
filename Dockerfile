FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/fsse-2506-backend-1.0.0.jar Project_Backend.jar
ENTRYPOINT ["java","-jar","/Project_Backend.jar"]