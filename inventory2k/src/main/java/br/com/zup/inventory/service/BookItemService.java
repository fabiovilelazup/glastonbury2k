package br.com.zup.inventory.service;

import br.com.zup.inventory.controller.request.BookRequest;
import br.com.zup.inventory.exception.ServiceException;

public interface BookItemService {

	void book(BookRequest request) throws ServiceException;
}
