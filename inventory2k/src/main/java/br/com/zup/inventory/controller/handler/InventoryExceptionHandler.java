package br.com.zup.inventory.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.zup.inventory.service.exception.ItemSoldOutException;

@ControllerAdvice
public class InventoryExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ItemSoldOutException.class)
	public final ResponseEntity<ExceptionDetails> handleItemSoldOutException(ItemSoldOutException ex,
			WebRequest request) {

		ExceptionDetails errorDetails = new ExceptionDetails(ex.getClass().getSimpleName(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
