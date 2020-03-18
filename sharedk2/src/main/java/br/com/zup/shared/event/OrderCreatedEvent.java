package br.com.zup.shared.event;

import java.math.BigDecimal;
import java.util.Map;

public class OrderCreatedEvent extends AbstractOrderEvent {

	private Map<String, Integer> items;

	public OrderCreatedEvent() {
		super();
	}

	public OrderCreatedEvent(String orderId, String customerId, BigDecimal amount, Map<String, Integer> items) {
		super(orderId, customerId, amount);
		this.items = items;
	}

	public Map<String, Integer> getItems() {
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}
}
