package com.stock.exchange;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import com.stock.exchange.dto.StockExchange;
import org.jboss.logging.Logger;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class ValuesGenerator {
    private static final Logger LOG = Logger.getLogger(ValuesGenerator.class);
    private Random random = new Random();
    private List<StockExchange> stocks = Arrays.asList(
            new StockExchange( 0,       "Afirme",  200, 0.9, 0.8, 0.2, 0.5, 0.8, 0.05, 21.8, 4.5),
            new StockExchange( 1,        "Gruma",  300, 1.2, 0.4, 0.1, 0.4, 1.2, 0.02, 40.1, 7.4),
            new StockExchange( 2,     "La COmer",  400, 0.8, 0.9, 0.3, 0.2, 0.9, 0.03, 32.3, 9.7),
            new StockExchange( 3,  "Grupo Bimbo",  500, 0.7, 0.7, 0.4, 0.1, 1.1, 0.01, 74.7, 5.8),
            new StockExchange( 4,    "Citigroup",  600, 1.1, 0.4, 0.2, 0.4, 0.8, 0.04, 24.3, 0.3),
            new StockExchange( 5,        "Homex",  700, 0.4, 0.9, 0.1, 0.3, 0.6, 0.05, 75.7, 6.5),
            new StockExchange( 6,    "Elementia",  800, 1.5, 0.6, 0.3, 0.2, 0.4, 0.06, 47.5, 3.8),
            new StockExchange( 7,        "Vitro",  900, 0.6, 0.8, 0.2, 0.1, 0.9, 0.04, 85.8, 8.4),
            new StockExchange( 8,       "Unefon", 1000, 0.8, 0.9, 0.1, 0.3, 1.0, 0.01, 36.3, 5.8),
            new StockExchange( 9,        "Alpek",  200, 0.9, 0.8, 0.2, 0.5, 0.8, 0.05, 21.8, 4.5),
            new StockExchange(10,        "Axtel",  300, 1.2, 0.4, 0.1, 0.4, 1.2, 0.02, 40.1, 7.4),
            new StockExchange(11,"Grupo Elektra",  400, 0.8, 0.9, 0.3, 0.2, 0.9, 0.03, 32.3, 9.7),
            new StockExchange(12,  "Grupo Jumex",  500, 0.7, 0.7, 0.4, 0.1, 1.1, 0.01, 74.7, 5.8),
            new StockExchange(13,       "Maseca",  600, 1.1, 0.4, 0.2, 0.4, 0.8, 0.04, 24.3, 0.3),
            new StockExchange(14,         "Izzi",  700, 0.4, 0.9, 0.1, 0.3, 0.6, 0.05, 75.7, 6.5),
            new StockExchange(15,"America Movil",  800, 1.5, 0.6, 0.3, 0.2, 0.4, 0.06, 47.5, 3.8),
            new StockExchange(16,         "AT&T",  900, 0.6, 0.8, 0.2, 0.1, 0.9, 0.04, 85.8, 8.4),
            new StockExchange(17,    "TV Azteca", 1000, 0.8, 0.9, 0.1, 0.3, 1.0, 0.01, 36.3, 5.8)
    );

    /*
      ./bin/kafka-topics.sh --create \
            --bootstrap-server localhost:9092 \
            --replication-factor 1 \
            --partitions 1 \
            --topic input-topic \
            --config cleanup.policy=delete
     */
    @Outgoing("input-topic")
    public Flowable<KafkaRecord<Integer, String>> generate() {
        LOG.infov("Creating flowable Kafka record");
        return Flowable.interval(3000, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .map(tick -> {
                    StockExchange stock = stocks.get(random.nextInt(stocks.size()));
                    double value = BigDecimal.valueOf(random.nextGaussian() * 35 + stock.getPrice())
                            .setScale(1, RoundingMode.HALF_UP)
                            .doubleValue();

                    LOG.infov("stock: {0}, value: {1}", stock.getCompany(), value);
                    return KafkaRecord.of(stock.getId(),
                            String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                                    stock.getId(), Instant.now(), stock.getCompany(), value,
                                    stock.getPriceBookRatio(), stock.getDebtEquityRatio(),
                                    stock.getPriceEarningsGrowthRatio(), stock.getPriceEarningsRatio(),
                                    stock.getBeta(), stock.getDividends(), stock.getRevenue(),
                                    stock.getAdjustedEarnings()) );
                });
    }

}
