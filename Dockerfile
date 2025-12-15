# --- Stage 1: build the application ---
FROM gradle:8.4-jdk21-alpine AS builder
# (usa la imagen oficial de Gradle con JDK 21)

WORKDIR /home/gradle/project

# Copiar solo archivos necesarios para cachear dependencias
COPY build.gradle settings.gradle gradlew gradle /home/gradle/project/

# Descargar dependencias
RUN chmod +x gradlew && ./gradlew --no-daemon assemble -x test || true

# Copiar el resto del código
COPY . /home/gradle/project

# Build real + descarga y unzip de New Relic
RUN chmod +x gradlew && \
    ./gradlew --no-daemon clean bootJar unzipNewRelic -x test


# --- Stage 2: runtime ---
FROM eclipse-temurin:21-jre-alpine
# (imagen base JRE 21 liviana)

WORKDIR /app

# Copiar el JAR
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

# Copiar New Relic (path REAL generado por Gradle)
RUN mkdir -p /app/newrelic
COPY --from=builder /home/gradle/project/build/newrelic/newrelic/newrelic.jar /app/newrelic/newrelic.jar
COPY --from=builder /home/gradle/project/build/newrelic/newrelic/newrelic.yml /app/newrelic/newrelic.yml

# Variables de entorno
ENV JAVA_OPTS=""
ENV NEW_RELIC_LOG=stdout

EXPOSE 8086

ENTRYPOINT ["sh", "-c", "java -javaagent:/app/newrelic/newrelic.jar $JAVA_OPTS -jar app.jar"]