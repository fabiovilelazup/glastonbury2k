package br.com.zup.shared.event;

import java.math.BigDecimal;

public class ApprovedOrderPaymentEvent extends AbstractOrderEvent {

	public ApprovedOrderPaymentEvent() {
		super();
	}

	public ApprovedOrderPaymentEvent(String orderId, String customerId, BigDecimal amount) {
		super(orderId, customerId, amount);
	}

}
