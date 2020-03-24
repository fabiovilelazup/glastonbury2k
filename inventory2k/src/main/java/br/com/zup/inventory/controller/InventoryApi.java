package br.com.zup.inventory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.RestoreRequest;
import br.com.zup.inventory.controller.response.ProductResponse;
import br.com.zup.inventory.service.exception.ServiceException;

public interface InventoryApi {

	@GetMapping("/products")
	List<ProductResponse> products() throws ServiceException;

	@PostMapping("/booking")
	void booking(@RequestBody BookRequest request) throws ServiceException;

	@PostMapping("/restore")
	void restore(@RequestBody RestoreRequest request) throws ServiceException;
}
