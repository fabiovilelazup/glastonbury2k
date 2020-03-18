package br.com.zup.inventory.service;

import java.util.List;

import br.com.zup.inventory.entity.Product;

public interface ProductService {

	void update(Product product);

	Product findById(String id);

	List<Product> findAll();
}
