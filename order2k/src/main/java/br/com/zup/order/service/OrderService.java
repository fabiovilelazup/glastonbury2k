package br.com.zup.order.service;

import java.util.List;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.SoldOutItemRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.entity.Order;
import br.com.zup.order.service.exception.ServiceException;

public interface OrderService {

	String save(CreateOrderRequest request);

	List<OrderResponse> findAll();

	Order findById(String id);

	void update(Order order);

	void soldouting(SoldOutItemRequest request) throws ServiceException;
}
