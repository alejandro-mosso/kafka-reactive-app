FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /
COPY ./target/listener-1.0.0-runner.jar listener-1.0.0-runner.jar
COPY ./target/lib/*.jar ./lib/
CMD java -jar listener-1.0.0-runner.jar
