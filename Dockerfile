FROM amazoncorretto:17.0.10-alpine

WORKDIR app

COPY target/UserService.jar .

ENTRYPOINT ["java","-jar", "UserService.jar"]