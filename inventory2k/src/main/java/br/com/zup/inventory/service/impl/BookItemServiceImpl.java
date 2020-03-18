package br.com.zup.inventory.service.impl;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.entity.Product;
import br.com.zup.inventory.exception.ItemSoldOutException;
import br.com.zup.inventory.service.BookItemService;
import br.com.zup.inventory.service.ProductService;

@Service
@Transactional
public class BookItemServiceImpl implements BookItemService {

	private ProductService productService;

	@Autowired
	public BookItemServiceImpl(ProductService productService) {
		this.productService = productService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {ItemSoldOutException.class})
	public void book(BookRequest request) throws ItemSoldOutException {

		for (Entry<String, Integer> item : request.getOrderEntries().entrySet()) {

			Product product = productService.findById(item.getKey());

			if (product.getInventory().equals(0) || product.getInventory() < item.getValue()) {

				throw new ItemSoldOutException("Item [" + product.getId() + "] Soldout");
			} else {

				product.setInventory(product.getInventory() - item.getValue());
				productService.update(product);
			}
		}
	}
}
