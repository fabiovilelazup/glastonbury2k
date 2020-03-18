package br.com.zup.shared.event;

import java.math.BigDecimal;

public abstract class AbstractOrderEvent {

	private String orderId;

	private String customerId;

	private BigDecimal amount;

	public AbstractOrderEvent() {
	}

	public AbstractOrderEvent(String orderId, String customerId, BigDecimal amount) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}