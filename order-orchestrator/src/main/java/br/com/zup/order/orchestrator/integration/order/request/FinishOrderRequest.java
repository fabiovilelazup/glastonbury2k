package br.com.zup.order.orchestrator.integration.order.request;

public class FinishOrderRequest {

	private String orderId;

	public FinishOrderRequest() {
		super();
	}

	public FinishOrderRequest(String orderId) {
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
