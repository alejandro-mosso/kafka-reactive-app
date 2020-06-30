# reactivekafkawebsocket
The application shows pipe messages from kafka to a websocket endpoint in a reactive manner.

This application uses project reactors reactive-kafka as well as spring-webflux. 
Both libraries work together to provide a completely reactive stack, providing access to a kafka 
topic hosted on confluent cloud.

## Standalone execution
To run this application you need to enter your confluent cloud credential into 
ccloud.properties.

Now you can run it with 
`java -jar messenger-1.0.0.jar`

## Run with Docker
`docker build -t stock-messenger .`
`docker run --network="host" --cpus="1" stock-messenger`

`docker container ls | grep stock-messenger`
`docker kill <CONTAINER ID>`