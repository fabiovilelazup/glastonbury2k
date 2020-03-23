package br.com.zup.shared.event;

import java.math.BigDecimal;
import java.util.Map;

public class RejectedOrderPaymentEvent extends AbstractOrderEvent {

	private Map<String, Integer> items;

	public RejectedOrderPaymentEvent() {
		super();
	}

	public RejectedOrderPaymentEvent(String orderId, String customerId, BigDecimal amount, Map<String, Integer> items) {
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
