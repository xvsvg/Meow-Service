FROM openjdk:17-jdk-slim

COPY --chown=root . /backend

WORKDIR /backend

USER root
RUN chmod +x gradlew

CMD ["./gradlew", "bootRun"]
