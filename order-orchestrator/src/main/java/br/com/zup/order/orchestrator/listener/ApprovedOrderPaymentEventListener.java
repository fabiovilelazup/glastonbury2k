package br.com.zup.order.orchestrator.listener;

import java.io.IOException;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.shared.event.ApprovedOrderPaymentEvent;

@Configuration
public class ApprovedOrderPaymentEventListener {

	private ObjectMapper objectMapper;
	private RuntimeService runtimeService;

	public ApprovedOrderPaymentEventListener(ObjectMapper objectMapper, RuntimeService runtimeService) {
		super();
		this.objectMapper = objectMapper;
		this.runtimeService = runtimeService;
	}

	@KafkaListener(topics = "approved-orders-payment", groupId = "order-group-id")
	public void listen(String message) throws IOException {
		ApprovedOrderPaymentEvent event = this.objectMapper.readValue(message, ApprovedOrderPaymentEvent.class);

		runtimeService.createMessageCorrelation("payment_callback")
				.processInstanceBusinessKey("ORDER-" + event.getOrderId()).setVariable("PAYMENT_RESULT", true)
				.correlateWithResult();
	}
}
