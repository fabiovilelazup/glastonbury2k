FROM fabioborgesoliveiravilela/alpine-openjdk8:1.0.0

COPY target/order-orchestrator*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]