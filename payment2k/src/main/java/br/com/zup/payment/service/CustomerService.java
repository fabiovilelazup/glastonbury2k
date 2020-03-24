package br.com.zup.payment.service;

import java.util.List;

import br.com.zup.payment.controller.response.CustomerResponse;
import br.com.zup.payment.entity.Customer;

public interface CustomerService {

	void update(Customer customer) ;

	List<CustomerResponse> findAll();

	Customer findById(String id);
}
