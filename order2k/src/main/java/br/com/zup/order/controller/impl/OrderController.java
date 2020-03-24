package br.com.zup.order.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.order.controller.OrderApi;
import br.com.zup.order.controller.request.CancelOrderRequest;
import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.FinishOrderRequest;
import br.com.zup.order.controller.request.SoldOutItemRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.service.OrderService;
import br.com.zup.order.service.exception.ServiceException;

@RestController
@RequestMapping("/orders")
public class OrderController implements OrderApi {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String create(@RequestBody CreateOrderRequest request) throws ServiceException {
		return this.orderService.save(request);
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderResponse> getOrders() throws ServiceException {
		return this.orderService.findAll();
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/soldouting", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void soldouting(@RequestBody SoldOutItemRequest request) throws ServiceException {

		orderService.soldouting(request);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cancel(@RequestBody CancelOrderRequest request) throws ServiceException {

		orderService.cancel(request);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/finish", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void finish(@RequestBody FinishOrderRequest request) throws ServiceException {

		orderService.finish(request);
	}
}
