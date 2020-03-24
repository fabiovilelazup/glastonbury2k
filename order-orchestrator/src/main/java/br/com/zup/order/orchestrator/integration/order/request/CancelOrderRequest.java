package br.com.zup.order.orchestrator.integration.order.request;

public class CancelOrderRequest {

	private String orderId;

	public CancelOrderRequest() {
		super();
	}

	public CancelOrderRequest(String orderId) {
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
