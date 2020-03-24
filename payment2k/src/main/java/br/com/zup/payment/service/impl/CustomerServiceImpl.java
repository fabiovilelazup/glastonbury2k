package br.com.zup.payment.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.payment.controller.response.CustomerResponse;
import br.com.zup.payment.entity.Customer;
import br.com.zup.payment.repository.CustomerRepository;
import br.com.zup.payment.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	public void update(Customer customer) {

		customerRepository.save(customer);
	}

	@Override
	public List<CustomerResponse> findAll() {
		return this.customerRepository.findAll().stream().map(CustomerResponse::fromEntity).collect(Collectors.toList());
	}
	
	@Override
	public Customer findById(String id) {

		Optional<Customer> optional = customerRepository.findById(id);

		return optional.orElse(null);
	}
}
