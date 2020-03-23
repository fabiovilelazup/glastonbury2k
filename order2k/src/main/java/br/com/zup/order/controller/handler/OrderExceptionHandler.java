package br.com.zup.order.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.zup.order.service.exception.ServiceException;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	public final ResponseEntity<ExceptionDetails> handleItemSoldOutException(ServiceException ex,
			WebRequest request) {

		ExceptionDetails errorDetails = new ExceptionDetails(ex.getClass().getSimpleName(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
