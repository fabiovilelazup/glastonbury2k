package br.com.zup.inventory.controller.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.inventory.controller.InventoryApi;
import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.response.ProductResponse;
import br.com.zup.inventory.exception.ServiceException;
import br.com.zup.inventory.service.BookItemService;
import br.com.zup.inventory.service.ProductService;

@RestController
@RequestMapping("/inventory")
public class InventoryController implements InventoryApi {

	private ProductService productService;

	private BookItemService bookItemService;

	@Autowired
	public InventoryController(ProductService productService, BookItemService bookItemService) {
		this.productService = productService;
		this.bookItemService = bookItemService;
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductResponse> getProducts() throws ServiceException {

		return productService.findAll().stream().map(ProductResponse::fromEntity).collect(Collectors.toList());
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void book(@RequestBody BookRequest request) throws ServiceException {

		bookItemService.book(request);
	}
}
