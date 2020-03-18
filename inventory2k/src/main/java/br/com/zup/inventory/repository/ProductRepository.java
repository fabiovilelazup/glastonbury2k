package br.com.zup.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.inventory.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
