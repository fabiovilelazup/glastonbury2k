package br.com.zup.order.orchestrator.integration.order;

import org.hibernate.service.spi.ServiceException;

import br.com.zup.order.orchestrator.integration.order.request.CancelOrderRequest;
import br.com.zup.order.orchestrator.integration.order.request.FinishOrderRequest;
import br.com.zup.order.orchestrator.integration.order.request.SoldOutItemRequest;
import feign.Headers;
import feign.RequestLine;

public interface OrderApi {

	@RequestLine("POST /soldouting")
    @Headers("Content-Type: application/json")
	void soldouting(SoldOutItemRequest request);

	@RequestLine("POST /cancel")
	@Headers("Content-Type: application/json")
	void cancel(CancelOrderRequest request) throws ServiceException;

	@RequestLine("POST /finish")
	@Headers("Content-Type: application/json")
	void finish(FinishOrderRequest request) throws ServiceException;
}
