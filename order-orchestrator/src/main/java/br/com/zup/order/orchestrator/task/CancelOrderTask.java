package br.com.zup.order.orchestrator.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.order.OrderApi;
import br.com.zup.order.orchestrator.integration.order.request.CancelOrderRequest;

@Component
public class CancelOrderTask implements JavaDelegate {

	private OrderApi api;
	private ObjectMapper objectMapper;

	public CancelOrderTask(OrderApi api, ObjectMapper objectMapper) {
		this.api = api;
		this.objectMapper = objectMapper;
	}

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		// Get Order From Camunda.
		String orderVariable = (String) delegateExecution.getVariable("ORDER");

		// Parse JSON to Object
		OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);

		// Call OrderAPI to Cancel Order.
		CancelOrderRequest request = new CancelOrderRequest();
		request.setOrderId(event.getOrderId());
		this.api.cancel(request);
	}
}
