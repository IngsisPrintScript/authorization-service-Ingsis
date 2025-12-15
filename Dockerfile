# --- Stage 1: build ---
FROM gradle:8.4-jdk21-alpine AS builder

WORKDIR /home/gradle/project

# Cache de dependencias
COPY build.gradle settings.gradle gradlew gradle ./
RUN gradle --no-daemon build -x test

# Código
COPY . .
RUN gradle --no-daemon clean bootJar


# --- Stage 2: runtime ---
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# App
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# New Relic (runtime)
RUN mkdir -p /app/newrelic \
 && wget -q https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip \
 && unzip newrelic-java.zip -d /app/newrelic \
 && rm newrelic-java.zip

# Config básica (podés versionarla o inyectarla)
COPY newrelic.yml /app/newrelic/newrelic.yml

ENV JAVA_OPTS=""
ENV NEW_RELIC_APP_NAME="permission-service"
ENV NEW_RELIC_LOG=stdout

EXPOSE 8086

ENTRYPOINT ["sh", "-c", "java -javaagent:/app/newrelic/newrelic.jar $JAVA_OPTS -jar app.jar"]
