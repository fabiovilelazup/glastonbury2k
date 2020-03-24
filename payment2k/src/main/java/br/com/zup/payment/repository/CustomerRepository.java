package br.com.zup.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.payment.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
