package fr.dta.formafond.model;

public enum Role {
	ADMIN("Admin"),USER("User");
	
	private String name;
	
	Role(String str) {
		this.name=str;
	}

	public String toString() {
		return this.name();
	}
}
