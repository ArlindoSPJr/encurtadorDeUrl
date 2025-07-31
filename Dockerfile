FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y

COPY . .

# Executa o build pulando os testes
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

EXPOSE 8080

# Copia o .jar gerado na primeira stage
COPY --from=build /target/encurtador-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
