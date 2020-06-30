FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /
COPY ./target/landing-1.0.0-runner.jar landing-1.0.0-runner.jar
COPY ./target/lib/*.jar ./lib/
CMD java -jar landing-1.0.0-runner.jar
