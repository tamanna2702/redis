FROM java:8-jdk-alpine

COPY ./target/redis-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch redis-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","redis-0.0.1-SNAPSHOT.jar"]