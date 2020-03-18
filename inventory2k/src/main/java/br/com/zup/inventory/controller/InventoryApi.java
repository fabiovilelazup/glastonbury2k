package br.com.zup.inventory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.response.ProductResponse;
import br.com.zup.inventory.exception.ItemSoldOutException;

public interface InventoryApi {

	@GetMapping("/products")
    List<ProductResponse>  getProducts();
	
    @PostMapping("/booking")
    void book(@RequestBody BookRequest request) throws ItemSoldOutException;
}
