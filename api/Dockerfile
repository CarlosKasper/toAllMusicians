FROM maven:3.6-jdk-8

WORKDIR /app

ADD . /app

RUN mvn clean package -Pprod

EXPOSE 80

ENTRYPOINT ["java","-jar", "target/carlos.kasper-1.0.0-SNAPSHOT.jar"]