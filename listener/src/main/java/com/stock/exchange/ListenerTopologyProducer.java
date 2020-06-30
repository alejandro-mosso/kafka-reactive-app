package com.stock.exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.stock.exchange.dto.StockPreview;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.Consumed;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ListenerTopologyProducer {
    private static final Logger LOG = Logger.getLogger(ListenerTopologyProducer.class);

    /* input-topic - This topic receives a string with CSV records. */
    private static final String STREAMS_STOCK_EXCHANGE_INPUT_TOPIC = "input-topic";
    /* landing-area - This topic receives a string with CSV records. */
    private static final String STREAMS_STOCK_EXCHANGE_OUTPUT_TOPIC = "landing-area";
    private static final String STREAMS_STOCK_EXCHANGE_ERROR_TOPIC = "landing-error";

    @Produces
    public Topology buildTopology() {
        StreamsBuilder builder = new StreamsBuilder();

        /*Input topic*/
        KStream<Integer,String> stream = builder
                .stream(STREAMS_STOCK_EXCHANGE_INPUT_TOPIC,
                        Consumed.with(Serdes.Integer(), Serdes.String()));

        KStream<Integer,StockPreview>[] streams = stream
                .mapValues( (key, value) -> {
                    String[] parts = value.split(",");
                    return new StockPreview(parts, value);
                })
                .branch(
                        (key, value) -> {
                            return value.isValid();
                        },
                        (key, value) -> {
                            return true;
                        });

        streams[0]
                .mapValues( (key, value) -> {
                    //LOG.infov("value: {0}", value);
                    return value.getOriginal();
                })
                .to(STREAMS_STOCK_EXCHANGE_OUTPUT_TOPIC,
                Produced.with(Serdes.Integer(), Serdes.String()));

        streams[1]
                .mapValues( (key, value) -> {
                    //LOG.errorv("value: {0}", value);
                    return value.getOriginal();
                })
                .to(STREAMS_STOCK_EXCHANGE_ERROR_TOPIC,
                Produced.with(Serdes.Integer(), Serdes.String()));

        return builder.build();
    }

}
