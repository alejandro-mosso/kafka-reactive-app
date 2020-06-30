# producer project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

The project requires Maven 3.6.3
You can run your application in dev mode that enables live coding using:
```
mvn quarkus:dev -Ddebug=5005
```

## Dependency analysis

Find unused dependencies running this command:
`mvn dependency:analyze`

## Packaging and running the application

The application can be packaged using 
`mvn clean package -DskipTests=true`.
It produces the `producer-1.0.0-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using :
`java -jar target/producer-1.0.0-runner.jar`
or run it to override a particular property:
`java -Dquarkus.http.port=8074 -jar ./target/producer-1.0.0-runner.jar`

## Run with Docker
`docker build -t stock-producer .`
`docker run --network="host" --cpus="1" stock-producer`

`docker container ls | grep stock-producer`
`docker kill <CONTAINER ID>`

## Creating a native executable

You can create a native executable using: `mvn package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `mvn package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/producer-1.0.0-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
