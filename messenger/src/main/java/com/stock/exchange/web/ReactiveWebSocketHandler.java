package com.stock.exchange.web;

import com.stock.exchange.service.KafkaService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Component()
public class ReactiveWebSocketHandler implements WebSocketHandler {

    private static final Logger LOG = Logger.getLogger(ReactiveWebSocketHandler.class);

    @Autowired()
    private KafkaService kafkaService;

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        return webSocketSession.send(kafkaService.getTopicFlux()
                .map(record -> {
                    //LOG.info(record.value());
                    return record.value();
                })
                .map(webSocketSession::textMessage))
                .and(webSocketSession.receive()
                        .map(WebSocketMessage::getPayloadAsText).log());
    }
}
