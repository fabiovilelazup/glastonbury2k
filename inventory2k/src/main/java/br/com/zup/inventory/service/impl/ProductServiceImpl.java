package br.com.zup.inventory.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.inventory.entity.Product;
import br.com.zup.inventory.repository.ProductRepository;
import br.com.zup.inventory.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Product product) {

		productRepository.save(product);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Product findById(String id) {

		Optional<Product> optional = productRepository.findById(id);

		return optional.orElse(null);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Product> findAll() {
		
		return this.productRepository.findAll();
	}
}
