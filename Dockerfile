FROM amazoncorretto:21
WORKDIR /app
ADD build/libs/rinha-lazy-exception.jar .
CMD ["java", "-XX:MaxRAMPercentage=80", "-jar", "rinha-lazy-exception.jar"]