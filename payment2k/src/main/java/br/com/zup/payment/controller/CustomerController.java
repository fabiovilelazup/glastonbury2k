package br.com.zup.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.payment.controller.response.CustomerResponse;
import br.com.zup.payment.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerResponse> getOrders() {
		return this.customerService.findAll();
	}
}
