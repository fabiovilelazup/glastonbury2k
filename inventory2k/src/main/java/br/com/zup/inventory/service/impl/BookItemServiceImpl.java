package br.com.zup.inventory.service.impl;

import java.util.Map.Entry;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.entity.Product;
import br.com.zup.inventory.service.BookItemService;
import br.com.zup.inventory.service.ProductService;
import br.com.zup.inventory.service.exception.ItemSoldOutException;
import br.com.zup.inventory.service.exception.ServiceException;

@Service
@Transactional
public class BookItemServiceImpl implements BookItemService {

	private Logger logger = LoggerFactory.getLogger(BookItemServiceImpl.class);

	private ProductService productService;

	@Autowired
	public BookItemServiceImpl(ProductService productService) {
		this.productService = productService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { ItemSoldOutException.class })
	public void book(BookRequest request) throws ServiceException {

		logger.debug("Booking items [{}]", request);

		try {

			for (Entry<String, Integer> item : request.getOrderEntries().entrySet()) {

				Product product = productService.findById(item.getKey());

				if (product.getInventory().equals(0) || product.getInventory() < item.getValue()) {

					throw new ItemSoldOutException("Item [" + product.getId() + "] Soldout");
				} else {

					product.setInventory(product.getInventory() - item.getValue());
					productService.update(product);
				}
			}

		} catch (ServiceException e) {

			String errorMessage = new StringFormattedMessage("Error while booking items [{}]", request).toString();

			logger.error(errorMessage, e);

			throw e;
		} catch (Exception e) {

			String errorMessage = new StringFormattedMessage("Error while booking items [{}]", request).toString();

			logger.error(errorMessage, e);

			throw new ServiceException(errorMessage, e);
		}
	}
}
