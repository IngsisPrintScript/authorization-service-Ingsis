# permission-service

Servicio Spring Boot (Java 21, Gradle) con Postgres. Incluye formatter (Spotless), linter (Checkstyle), cobertura (JaCoCo) y CI (GitHub Actions).

## Requisitos
- Java 21 (Temurin recomendado)
- Docker y Docker Compose
- Git

## Servicios locales
- Postgres 16 en `docker-compose.yml` (usuario `app`, password `app`, DB `permissions`)
- Adminer en `http://localhost:8080`

## Configuración de aplicación
Archivo `src/main/resources/application.yml` apunta a Postgres local. Variables override opcionales:
- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`

## Ejecutar para levantar
- Docker: `docker compose up -d`
- Spring: `./gradlew bootRun`

## Builds
- Compilar y testear: `./gradlew build`
- Sólo formatear código: `./gradlew spotlessApply`

## Formatter / Linter / Coverage
- Verificar formato: `./gradlew spotlessCheck`
- Linter (Checkstyle): `./gradlew checkstyleMain`
- Cobertura (JaCoCo):
  - Reporte HTML: `build/reports/jacoco/test/html/index.html`
  - Verificación: `./gradlew jacocoTestCoverageVerification` (umbral actual 80%)

## Hooks de Git
- Instalar hooks locales: `./scripts/install-hooks.sh`
- Hooks incluidos:
  - `pre-commit`: aplica Spotless y re-stagea cambios
  - `pre-push`: ejecuta tests

## CI (GitHub Actions)
- Workflow en `.github/workflows/ci.yml`
- Dispara en push/PR y ejecuta `./gradlew build`

## Base de datos (Docker)
- Levantar Postgres: `docker compose up -d postgres`
- Adminer: `docker compose up -d adminer` → `http://localhost:8080`

## Troubleshooting
- Si falla `spotless*`: ejecutar `./gradlew spotlessApply`.
- Si falla cobertura en `check`: `./gradlew test jacocoTestReport` y abrir el reporte HTML para identificar clases sin cubrir.
