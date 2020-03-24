package br.com.zup.order.controller.request;

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
