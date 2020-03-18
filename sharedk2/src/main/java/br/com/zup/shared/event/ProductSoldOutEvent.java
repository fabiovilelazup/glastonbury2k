package br.com.zup.shared.event;

import java.math.BigDecimal;

public class ProductSoldOutEvent extends AbstractOrderEvent {

	public ProductSoldOutEvent() {
		super();
	}

	public ProductSoldOutEvent(String orderId, String customerId, BigDecimal amount) {
		super(orderId, customerId, amount);
	}
}
