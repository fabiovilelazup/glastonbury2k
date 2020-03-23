package br.com.zup.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
}
