package com.stock.exchange;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.stock.exchange.dto.StockExchange;
import com.stock.exchange.dto.StockRatio;
import com.stock.exchange.dto.StockValue;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.Consumed;

import io.quarkus.kafka.client.serialization.JsonbSerde;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ProcessorTopologyProducer {
    private static final Logger LOG = Logger.getLogger(ProcessorTopologyProducer.class);


    /* Input topic. */
    private static final String STREAMS_STOCK_EXCHANGE_STAGING_TOPIC = "staging-area";

    /* Intermediate topics, only used to materialize each partition. */
    private static final String STREAMS_STOCK_EXCHANGE_VALUE_TOPIC = "stock-value";
    private static final String STREAMS_STOCK_EXCHANGE_RATIO_TOPIC = "stock-ratio";

    /* Output topics. staging-area topic is partitioned into two topics. */
    private static final String STREAMS_STOCK_EXCHANGE_RATIO_OUT_TOPIC = "stock-ratio-out";
    private static final String STREAMS_STOCK_EXCHANGE_VALUE_OUT_TOPIC = "stock-value-out";

    @Produces
    public Topology buildTopology() {
        StreamsBuilder builder = new StreamsBuilder();

        JsonbSerde<StockExchange> stockExchangeSerde = new JsonbSerde<>(StockExchange.class);
        JsonbSerde<StockRatio> stockRatioSerde = new JsonbSerde<>(StockRatio.class);
        JsonbSerde<StockValue> stockValueSerde = new JsonbSerde<>(StockValue.class);
        /*Input topic*/
        KStream<Integer, StockExchange> stream = builder
                .stream(STREAMS_STOCK_EXCHANGE_STAGING_TOPIC,
                        Consumed.with(Serdes.Integer(), stockExchangeSerde));

        /* Partition with all values */
        stream
                .through(STREAMS_STOCK_EXCHANGE_VALUE_TOPIC,
                        Produced.with(Serdes.Integer(), stockExchangeSerde))
                .mapValues((key, value) -> {
                    //LOG.infov("Value: {0}", value);
                    StockValue stockValue = new StockValue();
                    stockValue.setAdjustedEarnings(value.getAdjustedEarnings());
                    stockValue.setBeta(value.getBeta());
                    stockValue.setCompany(value.getCompany());
                    stockValue.setDividends(value.getDividends());
                    stockValue.setId(value.getId());
                    stockValue.setPrice(value.getPrice());
                    stockValue.setRevenue(value.getRevenue());
                    return stockValue;
                })
                .to(STREAMS_STOCK_EXCHANGE_VALUE_OUT_TOPIC,
                        Produced.with(Serdes.Integer(), stockValueSerde));

        /* Partition with all ratios */
        stream
                .through(STREAMS_STOCK_EXCHANGE_RATIO_TOPIC,
                        Produced.with(Serdes.Integer(), stockExchangeSerde))
                .mapValues( (key, value) -> {
                    //LOG.infov("Ratio: {0}", value);
                    StockRatio stockRatio = new StockRatio();
                    stockRatio.setDebtEquityRatio(value.getDebtEquityRatio());
                    stockRatio.setId(value.getId());
                    stockRatio.setPriceBookRatio(value.getPriceBookRatio());
                    stockRatio.setPriceEarningsGrowthRatio(value.getPriceEarningsGrowthRatio());
                    stockRatio.setPriceEarningsRatio(value.getPriceEarningsRatio());
                    return stockRatio;
                })
                .to(STREAMS_STOCK_EXCHANGE_RATIO_OUT_TOPIC,
                        Produced.with(Serdes.Integer(), stockRatioSerde));

        return builder.build();
    }
}
