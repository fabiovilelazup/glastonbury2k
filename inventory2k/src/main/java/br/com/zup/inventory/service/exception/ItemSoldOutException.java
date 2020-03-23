package br.com.zup.inventory.service.exception;

public class ItemSoldOutException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public ItemSoldOutException(String message) {
		super(message);
	}
}
