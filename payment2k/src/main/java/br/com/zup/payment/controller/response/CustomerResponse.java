package br.com.zup.payment.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.zup.payment.entity.Customer;

public class CustomerResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private BigDecimal balance;

	public CustomerResponse() {
	}

	public CustomerResponse(String id, String name, BigDecimal balance) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "CustomerResponse [id=" + id + ", name=" + name + "]";
	}

	public static CustomerResponse fromEntity(Customer customer) {
		return new CustomerResponse(customer.getId(), customer.getName(), customer.getBalance());
	}
}
