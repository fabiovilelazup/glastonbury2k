package br.com.zup.inventory.controller;

import java.util.List;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.controller.request.RestoreRequest;
import br.com.zup.inventory.controller.response.ProductResponse;
import br.com.zup.inventory.service.exception.ServiceException;

public interface InventoryApi {

	List<ProductResponse> products() throws ServiceException;

	void booking(BookRequest request) throws ServiceException;

	void restore(RestoreRequest request) throws ServiceException;
}
