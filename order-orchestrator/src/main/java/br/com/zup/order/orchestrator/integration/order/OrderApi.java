package br.com.zup.order.orchestrator.integration.order;

import br.com.zup.order.orchestrator.integration.order.request.SoldOutItemRequest;
import feign.Headers;
import feign.RequestLine;

public interface OrderApi {

	@RequestLine("POST /soldouting")
    @Headers("Content-Type: application/json")
	void soldouting(SoldOutItemRequest request);
}
