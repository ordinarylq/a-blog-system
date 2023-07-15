FROM openjdk:8-alpine
ARG EXTRACTED=blog-system-web/target/extracted
COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]