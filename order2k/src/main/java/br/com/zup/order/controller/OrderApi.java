package br.com.zup.order.controller;

import java.util.List;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.SoldOutItemRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.service.exception.ServiceException;

public interface OrderApi {

	String create(CreateOrderRequest request) throws ServiceException;

	List<OrderResponse> getOrders() throws ServiceException;

	void soldouting(SoldOutItemRequest request) throws ServiceException;
}
