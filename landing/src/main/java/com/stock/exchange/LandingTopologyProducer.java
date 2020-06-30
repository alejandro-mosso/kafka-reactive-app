package com.stock.exchange;

import com.stock.exchange.dto.StockExchange;
import io.quarkus.kafka.client.serialization.JsonbSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class LandingTopologyProducer {
    private static final Logger LOG = Logger.getLogger(LandingTopologyProducer.class);

    /* landing-area - This topic receives a string with CSV records. */
    private static final String STREAMS_STOCK_EXCHANGE_INPUT_TOPIC = "landing-area";
    /* staging-area - This topic receives a string with JSON records. */
    private static final String STREAMS_STOCK_EXCHANGE_STAGING_TOPIC = "staging-area";


    /**
     * Data transformation:
     * Reads data from <code>landing-area<code/> topic, and transforms
     * DELIMITED records into JSON format; then, the process stores JSON
     * records in <code>staging-area<code/> topic.
     **/
    @Produces
    public Topology buildTopology() {
        StreamsBuilder builder = new StreamsBuilder();
        JsonbSerde<StockExchange> stockExchangeSerde = new JsonbSerde<>(StockExchange .class);

        /*Input topic*/
        builder
                .stream(STREAMS_STOCK_EXCHANGE_INPUT_TOPIC,
                        Consumed.with(Serdes.Integer(), Serdes.String()))
                .mapValues( (key, value) -> {
                    //LOG.infov("Landing: {0}", value);
                    String[] parts = value.split(",");
                    return new StockExchange(Integer.parseInt(parts[0]), parts[2], Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Double.parseDouble(parts[6]),
                            Double.parseDouble(parts[7]), Double.parseDouble(parts[8]), Double.parseDouble(parts[9]),
                            Double.parseDouble(parts[10]), Double.parseDouble(parts[11]));
                })
                .to(STREAMS_STOCK_EXCHANGE_STAGING_TOPIC,
                        Produced.with(Serdes.Integer(), stockExchangeSerde));

        return builder.build();
    }
}
