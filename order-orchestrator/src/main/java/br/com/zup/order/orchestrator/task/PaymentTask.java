package br.com.zup.order.orchestrator.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.shared.event.AbstractOrderEvent;
import br.com.zup.shared.event.WaitPaymentEvent;

@Component
public class PaymentTask implements JavaDelegate {

	private KafkaTemplate<String, AbstractOrderEvent> kafkaTemplate;
	private ObjectMapper objectMapper;

	public PaymentTask(KafkaTemplate<String, AbstractOrderEvent> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		String orderVariable = (String) delegateExecution.getVariable("ORDER");

		WaitPaymentEvent event = this.objectMapper.readValue(orderVariable, WaitPaymentEvent.class);

		this.kafkaTemplate.send("wait-payment", event);
	}
}
