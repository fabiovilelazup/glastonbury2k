package br.com.zup.order.orchestrator.listener;

import java.io.IOException;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.shared.event.RejectedOrderPaymentEvent;

@Configuration
public class RejectedOrderPaymentEventListener {

	private ObjectMapper objectMapper;
	private RuntimeService runtimeService;

	public RejectedOrderPaymentEventListener(ObjectMapper objectMapper, RuntimeService runtimeService) {
		super();
		this.objectMapper = objectMapper;
		this.runtimeService = runtimeService;
	}

	@KafkaListener(topics = "rejected-orders-payment", groupId = "order-group-id")
	public void listen(String message) throws IOException {
		RejectedOrderPaymentEvent event = this.objectMapper.readValue(message, RejectedOrderPaymentEvent.class);

		runtimeService.createMessageCorrelation("payment_callback")
				.processInstanceBusinessKey("ORDER-" + event.getOrderId())
				.setVariable("PAYMENT_RESULT", false).correlateWithResult();
	}
}
