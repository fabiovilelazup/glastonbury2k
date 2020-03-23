package br.com.zup.order.orchestrator.task;

import javax.ws.rs.BadRequestException;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.order.orchestrator.event.OrderCreatedEvent;
import br.com.zup.order.orchestrator.integration.inventory.InventoryApi;
import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;

@Component
public class BookingTask implements JavaDelegate {

	private InventoryApi api;
	private ObjectMapper objectMapper;

	public BookingTask(InventoryApi api, ObjectMapper objectMapper) {
		this.api = api;
		this.objectMapper = objectMapper;
	}

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {

		String orderVariable = (String) delegateExecution.getVariable("ORDER");

		OrderCreatedEvent event = this.objectMapper.readValue(orderVariable, OrderCreatedEvent.class);

		BookRequest request = new BookRequest();
		request.setOrderEntries(event.getItems());

		try {
			this.api.booking(request);
		} catch (BadRequestException e) {

			throw new BpmnError(String.valueOf(e.getResponse().getStatus()));
		}
	}
}
