package br.com.zup.order.orchestrator.integration.inventory.request;

import java.util.Map;

public class RestoreRequest {

	private Map<String, Integer> orderEntries;

	public RestoreRequest() {
		super();
	}

	public RestoreRequest(Map<String, Integer> orderEntries) {
		super();
		this.orderEntries = orderEntries;
	}

	public Map<String, Integer> getOrderEntries() {
		return orderEntries;
	}

	public void setOrderEntries(Map<String, Integer> orderEntries) {
		this.orderEntries = orderEntries;
	}

	@Override
	public String toString() {
		return "BookRequest [orderEntries=" + orderEntries + "]";
	}
}
