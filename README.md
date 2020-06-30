![image](../images/confluent-logo-300-2.png)

# Start docker
    `sudo systemctl status docker`
    `sudo systemctl start docker`

# Start all environment with docker compose
    `docker-compose up`


# Topics setup

After environment is started with docker-compose, go to http://localhost:9021 in your browser.
and create following topics:
- input-topic
- landing-area
- landing-error
- staging-area
- stock-value
- stock-ratio-out
- stock-value-out

# Overview

This [docker-compose.yml](docker-compose.yml) launches all services in Confluent Platform and runs them in containers in your local host, enabling you to build your own development environments.
For an example of how to use this Docker setup, refer to the [Confluent Platform quickstart](https://docs.confluent.io/current/quickstart/index.html?utm_source=github&utm_medium=demo&utm_campaign=ch.cp-all-in-one_type.community_content.cp-all-in-one)

# Additional Examples

For additional examples that showcase streaming applications within an event streaming platform, please refer to [these demos](https://github.com/confluentinc/examples).

# Changes
This configuration was complemented with ScyllaDB connectors.
To integrate ScyllaDB connector download code:
`git clone https://github.com/scylladb/kafka-connect-scylladb.git`
`cd kafka-connect-scylladb/`
`mvn clean install -DskipTests=true`
`cd target/components/packages`

`mkdir ./scyllaDB-sink-connector`

And copy all jars under `target/components/packages` local `Scylla-Sink-Connector` directory



