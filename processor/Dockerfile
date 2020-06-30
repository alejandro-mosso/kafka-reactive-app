FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /
COPY ./target/processor-1.0.0-runner.jar processor-1.0.0-runner.jar
COPY ./target/lib/*.jar ./lib/
CMD java -jar processor-1.0.0-runner.jar
