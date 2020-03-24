package br.com.zup.order.orchestrator.integration.inventory;

import br.com.zup.order.orchestrator.integration.inventory.request.BookRequest;
import br.com.zup.order.orchestrator.integration.inventory.request.RestoreRequest;
import feign.Headers;
import feign.RequestLine;

public interface InventoryApi {

    @RequestLine("POST /booking")
    @Headers("Content-Type: application/json")
    void booking(BookRequest request);

	@RequestLine("POST /restore")
	@Headers("Content-Type: application/json")
	void restore(RestoreRequest request);
}
