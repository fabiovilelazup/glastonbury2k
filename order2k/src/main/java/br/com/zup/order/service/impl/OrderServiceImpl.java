package br.com.zup.order.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.zup.order.controller.request.CancelOrderRequest;
import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.request.FinishOrderRequest;
import br.com.zup.order.controller.request.SoldOutItemRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.entity.Order;
import br.com.zup.order.entity.OrderItem;
import br.com.zup.order.enums.Status;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import br.com.zup.order.service.exception.ServiceException;
import br.com.zup.shared.event.AbstractOrderEvent;
import br.com.zup.shared.event.OrderCreatedEvent;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private KafkaTemplate<String, AbstractOrderEvent> kafkaTemplate;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, KafkaTemplate<String, AbstractOrderEvent> kafkaTemplate) {
		this.orderRepository = orderRepository;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public String save(CreateOrderRequest request) {

		// Convert to Entity
		Order order = request.toEntity();

		// Save Order
		String orderId = this.orderRepository.save(order).getId();

		// Create Event on Kafka
		OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getCustomerId(), order.getAmount(),
				order.getItems().stream().collect(Collectors.toMap(OrderItem::getProductId, OrderItem::getQuantity)));

		this.kafkaTemplate.send("created-orders", event);

		return orderId;
	}

	@Override
	public List<OrderResponse> findAll() {
		return this.orderRepository.findAll().stream().map(OrderResponse::fromEntity).collect(Collectors.toList());
	}

	@Override
	public Order findById(String id) {

		Optional<Order> optional = orderRepository.findById(id);

		return optional.orElse(null);
	}

	@Override
	public void update(Order order) {

		orderRepository.save(order);
	}

	@Override
	public void soldouting(SoldOutItemRequest request) throws ServiceException {

		Order order = findById(request.getOrderId());

		order.setStatus(Status.SOLD_OUT);
		update(order);
	}

	@Override
	public void cancel(CancelOrderRequest request) throws ServiceException {

		Order order = findById(request.getOrderId());

		order.setStatus(Status.REJECTED);
		update(order);
	}

	@Override
	public void finish(FinishOrderRequest request) throws ServiceException {

		Order order = findById(request.getOrderId());

		order.setStatus(Status.APROVED);
		update(order);
	}
}
