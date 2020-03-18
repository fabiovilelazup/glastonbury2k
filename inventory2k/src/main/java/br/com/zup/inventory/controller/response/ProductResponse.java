package br.com.zup.inventory.controller.response;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.zup.inventory.entity.Product;

public class ProductResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private BigDecimal price;

	private Integer inventory;

	public ProductResponse() {
	}

	public ProductResponse(String id, String name, BigDecimal price, Integer inventory) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.inventory = inventory;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "ProductResponse [id=" + id + ", name=" + name + ", price=" + price + ", inventory=" + inventory + "]";
	}

	public static ProductResponse fromEntity(Product product) {
		return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getInventory());
	}
}
