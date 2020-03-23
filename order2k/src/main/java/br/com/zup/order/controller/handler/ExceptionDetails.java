package br.com.zup.order.controller.handler;

import java.io.Serializable;
import java.util.Date;

public class ExceptionDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timestamp;
	private String type;
	private String message;
	private String details;

	public ExceptionDetails(String type, String message, String details) {
		super();
		this.timestamp = new Date();
		this.type = type;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", type=" + type + ", message=" + message + ", details="
				+ details + "]";
	}
}