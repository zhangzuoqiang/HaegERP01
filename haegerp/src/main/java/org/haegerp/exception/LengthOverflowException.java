package org.haegerp.exception;

public class LengthOverflowException extends Exception {

	private static final long serialVersionUID = 5154836254343247234L;

	public LengthOverflowException(String fieldName) {
		super("Field " + fieldName + " overflow");
	}
}
