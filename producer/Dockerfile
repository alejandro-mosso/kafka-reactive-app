FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /
COPY ./target/producer-1.0.0-runner.jar producer-1.0.0-runner.jar
COPY ./target/lib/*.jar ./lib/
CMD java -jar producer-1.0.0-runner.jar
