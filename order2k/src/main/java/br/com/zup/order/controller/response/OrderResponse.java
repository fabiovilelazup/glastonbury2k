package br.com.zup.order.controller.response;

import br.com.zup.order.entity.Order;
import br.com.zup.order.entity.OrderItem;
import br.com.zup.order.enums.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderResponse {

	private String id;

	private String customerId;

	private BigDecimal amount;

	private List<OrderItemPart> items;

	private Status status;

	public OrderResponse() {
	}

	public OrderResponse(String id, String customerId, BigDecimal amount, List<OrderItemPart> items,
			Status status) {
		this.id = id;
		this.customerId = customerId;
		this.amount = amount;
		this.items = items;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<OrderItemPart> getItems() {
		return items;
	}

	public void setItems(List<OrderItemPart> items) {
		this.items = items;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static OrderResponse fromEntity(Order order) {
		return new OrderResponse(order.getId(), order.getCustomerId(), order.getAmount(),
				order.getItems().stream().map(OrderItemPart::fromEntity).collect(Collectors.toList()),
				order.getStatus());
	}

	public static class OrderItemPart {

		private String id;

		private String productId;

		private BigDecimal amount;

		private Integer quantity;

		public OrderItemPart(String id, String productId, BigDecimal amount, Integer quantity) {
			super();
			this.id = id;
			this.productId = productId;
			this.amount = amount;
			this.quantity = quantity;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public static OrderItemPart fromEntity(OrderItem orderItem) {
			return new OrderItemPart(orderItem.getId(), orderItem.getProductId(), orderItem.getPrice(),
					orderItem.getQuantity());
		}
	}
}
