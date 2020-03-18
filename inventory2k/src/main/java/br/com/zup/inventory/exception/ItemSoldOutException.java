package br.com.zup.inventory.exception;

public class ItemSoldOutException extends Exception {

	private static final long serialVersionUID = 1L;

	public ItemSoldOutException(String message) {
		super(message);
	}
}
