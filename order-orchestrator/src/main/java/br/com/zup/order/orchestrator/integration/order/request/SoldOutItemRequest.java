package br.com.zup.order.orchestrator.integration.order.request;

public class SoldOutItemRequest {

	private String orderId;

	public SoldOutItemRequest() {
		super();
	}

	public SoldOutItemRequest(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
