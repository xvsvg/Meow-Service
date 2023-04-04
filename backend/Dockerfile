FROM gradle as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon


FROM eclipse-temurin:17-jdk-alpine

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/meow-service-application.jar

ENTRYPOINT ["java","-jar", "/app/meow-service-application.jar"]
