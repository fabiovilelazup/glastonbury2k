package br.com.zup.inventory.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.inventory.entity.Product;
import br.com.zup.inventory.repository.ProductRepository;
import br.com.zup.inventory.service.ProductService;
import br.com.zup.inventory.service.exception.ServiceException;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Product product) throws ServiceException {

		logger.debug("Updating Product [{}]", product);

		try {

			productRepository.save(product);

			logger.debug("Product [{}] Updated.", product);
		} catch (Exception e) {

			String errorMessage = new StringFormattedMessage("Error while updating Product [{}]", product).toString();

			logger.error(errorMessage, e);

			throw new ServiceException(errorMessage, e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Product findById(String id) throws ServiceException {

		logger.debug("Getting Product for ID [{}]", id);

		try {

			Optional<Product> optional = productRepository.findById(id);

			Product product = optional.orElse(null);

			logger.debug("Product obtained for ID [{}] was [{}]", id, product);

			return product;
		} catch (Exception e) {

			String errorMessage = new StringFormattedMessage("Error while getting Product for ID [{}]", id).toString();

			logger.error(errorMessage, e);

			throw new ServiceException(errorMessage, e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Product> findAll() throws ServiceException {

		logger.debug("Getting All Products");

		try {

			return this.productRepository.findAll();
		} catch (Exception e) {

			String errorMessage = new StringFormattedMessage("Error while getting All Products").toString();

			logger.error(errorMessage, e);

			throw new ServiceException(errorMessage, e);
		}
	}
}
