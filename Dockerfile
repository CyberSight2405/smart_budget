FROM amazoncorretto:17.0.10-alpine

WORKDIR app

COPY target/SmartBudget.jar .

ENTRYPOINT ["java","-jar", "SmartBudget.jar"]