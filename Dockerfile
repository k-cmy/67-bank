# ============================================================
# Stage 1: Build — compiles your Java source code into a JAR
# ============================================================

# Start from a base image that has Java 21 JDK (the full development kit
# needed to compile code). "jammy" means it's based on Ubuntu 22.04.
# "AS builder" gives this stage a name so we can reference it later.
FROM eclipse-temurin:21-jdk-jammy AS builder

# Set /app as the working directory inside the container.
# All commands after this will run from /app.
WORKDIR /app

# Copy the Maven Wrapper config folder into the container.
# This ensures the wrapper knows which Maven version to download (3.9.12).
COPY .mvn/ .mvn/

# Copy the Maven Wrapper script (mvnw) and the pom.xml (your project's
# dependency list) into the container. These are copied BEFORE the source
# code so Docker can cache the dependency download layer — if pom.xml
# hasn't changed, Docker skips re-downloading dependencies on rebuilds.
COPY mvnw pom.xml ./

# Make the Maven Wrapper script executable, then download all project
# dependencies. "-B" means batch/non-interactive mode (no progress bars).
# This layer gets cached, so rebuilds are fast when only code changes.
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# NOW copy your actual Java source code into the container.
# This is done last so code changes don't invalidate the dependency cache.
COPY src ./src

# Compile the source code and package it into a fat JAR (a single .jar
# file that contains your app + all dependencies). Tests are skipped
# because we don't need to run them during the Docker image build.
RUN ./mvnw clean package -Dmaven.test.skip=true


# ============================================================
# Stage 2: Runtime — a minimal image that only runs the JAR
# ============================================================

# Start from a SMALLER base image that only has the Java 21 JRE (runtime).
# It can run Java apps but can't compile them — that's all we need here.
# This makes the final image much smaller (~200MB vs ~450MB with the JDK).
FROM eclipse-temurin:21-jre-jammy

# Create a dedicated non-root user and group called "appuser".
# Running as root inside a container is a security risk — if the
# container is compromised, the attacker would have root privileges.
RUN groupadd --system appgroup && useradd --system --gid appgroup appuser

# Set /app as the working directory in this new stage.
WORKDIR /app

# Copy ONLY the compiled JAR file from the builder stage. This is the
# key benefit of multi-stage builds: the final image has no JDK, no
# Maven, no source code — just the small JRE and your application JAR.
COPY --from=builder /app/target/*.jar app.jar

# Give the non-root user ownership of the JAR file so it can read it.
RUN chown appuser:appgroup app.jar

# Switch to the non-root user. Everything after this (including the
# ENTRYPOINT) runs as "appuser" instead of root.
USER appuser

# Document that the container listens on port 8090 at runtime.
# This doesn't actually open the port — it's metadata for anyone
# reading the Dockerfile or using tools like Docker Compose.
# The actual port mapping happens with "docker run -p 8090:8090".
EXPOSE 8090

# The command that runs when the container starts.
# This launches your Spring Boot application from the JAR file.
# The exec form ["...", "..."] is used instead of shell form so that
# Java receives OS signals (like SIGTERM) directly for graceful shutdown.
ENTRYPOINT ["java", "-jar", "app.jar"]
