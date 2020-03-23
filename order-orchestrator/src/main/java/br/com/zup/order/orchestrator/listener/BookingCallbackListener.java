package br.com.zup.order.orchestrator.listener;

import java.io.IOException;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.configuration.KafkaConfiguration;
import br.com.zup.order.orchestrator.event.OrderCreatedEvent;

@Component
public class BookingCallbackListener {

    private ObjectMapper objectMapper;
    private RuntimeService runtimeService;

    public BookingCallbackListener(ObjectMapper objectMapper, RuntimeService runtimeService) {
        this.objectMapper = objectMapper;
        this.runtimeService = runtimeService;
    }

    @KafkaListener(topics = "payment-event", groupId = KafkaConfiguration.CONSUMER_GROUP)
    public void listen(String message) throws IOException {
        // Read message, parse, check orderId and payment result
        String orderId = "TO BE DEFINED";

        runtimeService.createMessageCorrelation("payment_callback")
                .processInstanceBusinessKey("ORDER-" + orderId)
                .setVariable("PAYMENT_RESULT", true)
                .correlateWithResult();
    }
}
