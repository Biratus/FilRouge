package fr.dta.formafond.exception;

public class UserAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -320898554653891668L;
	private String username;

	public UserAlreadyExistsException(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
