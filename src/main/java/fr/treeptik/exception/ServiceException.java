package fr.treeptik.exception;

public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(Exception e, String message) {
		super(message,e);
	}

}
