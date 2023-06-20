FROM amazoncorretto:17-alpine

# Time Zone America/Bogota
ENV TZ="America/Bogota"

EXPOSE 8080

WORKDIR /opt/app

COPY build/libs/RetoPichincha.jar /opt/app/aplicaciones/RetoPichincha.jar

USER nobody

ENTRYPOINT ["java", "-jar", "/opt/app/aplicaciones/RetoPichincha.jar"]

