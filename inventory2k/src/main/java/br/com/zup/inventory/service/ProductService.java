package br.com.zup.inventory.service;

import java.util.List;

import br.com.zup.inventory.entity.Product;
import br.com.zup.inventory.service.exception.ServiceException;

public interface ProductService {

	void update(Product product) throws ServiceException;

	Product findById(String id) throws ServiceException;

	List<Product> findAll() throws ServiceException;
}
