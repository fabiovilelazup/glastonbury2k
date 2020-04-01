FROM fabioborgesoliveiravilela/alpine-openjdk8:1.0.0

ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]