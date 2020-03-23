package br.com.zup.order.controller.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.zup.order.entity.Order;
import br.com.zup.order.entity.OrderItem;
import br.com.zup.order.enums.Status;

public class CreateOrderRequest {

	private String customerId;

	private List<OrderItemPart> items;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<OrderItemPart> getItems() {
		return items;
	}

	public void setItems(List<OrderItemPart> items) {
		this.items = items;
	}

	public Order toEntity() {

		return new Order(UUID.randomUUID().toString(), this.customerId,
				this.items.stream().map(OrderItemPart::getAmount).reduce(BigDecimal.ZERO, (p, q) -> p.add(q)),
				this.items.stream().map(OrderItemPart::toEntity).collect(Collectors.toList()), Status.PENDING);
	}

	public static class OrderItemPart {

		private String productId;

		private BigDecimal amount;

		private Integer quantity;

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

		public OrderItem toEntity() {
			return new OrderItem(UUID.randomUUID().toString(), this.productId, this.quantity, this.amount);
		}
	}
}
