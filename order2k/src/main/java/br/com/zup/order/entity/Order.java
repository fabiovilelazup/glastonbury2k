package br.com.zup.order.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.com.zup.order.enums.Status;

/**
 * @author zupper
 *
 */
/**
 * @author zupper
 *
 */
@Entity(name = "orders")
public class Order {

	@Id
	private String id;

	private String customerId;

	@ManyToMany
	@Cascade(CascadeType.ALL)
	private List<OrderItem> items;

	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	private Status status;

	public Order() {
	}

	public Order(String id, String customerId, BigDecimal amount, List<OrderItem> items, Status status) {
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

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customerId=" + customerId + ", items=" + items + ", amount=" + amount
				+ ", status=" + status + "]";
	}
}
