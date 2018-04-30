package fr.dta.formafond.exception;

public class UserAlreadyExistsException extends Exception{
	
	private String username;

	public UserAlreadyExistsException(String username) {
		this.username=username;
	}
	
	public String getUsername() {return username;}

}
