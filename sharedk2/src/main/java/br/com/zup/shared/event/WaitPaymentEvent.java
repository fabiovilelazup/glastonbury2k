package br.com.zup.shared.event;

import java.math.BigDecimal;
import java.util.Map;

public class WaitPaymentEvent extends AbstractOrderEvent {

	private Map<String, Integer> items;

	public WaitPaymentEvent() {
		super();
	}

	public WaitPaymentEvent(String orderId, String customerId, BigDecimal amount, Map<String, Integer> items) {
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
