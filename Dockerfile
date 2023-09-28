FROM adoptopenjdk:20-jre-hotspot

WORKDIR /app

COPY target/sboot-ministerio-recomeco.jar .

EXPOSE 8080

CMD ["java", "-jar", "sboot-ministerio-recomeco.jar"]
