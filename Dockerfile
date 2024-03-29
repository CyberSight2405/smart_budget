FROM amazoncorretto:17.0.10-alpine

WORKDIR app

COPY target/UserService.jar .

ENTRYPOINT ["java","-jar", "-Xms256m", "-Xmx2024m", "UserService.jar"]