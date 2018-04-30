package fr.dta.formafond.model;

public enum Category {
	CLIMBING("Climbing"), DIVING("Diving"), HIKING("Hiking");

	private String name = "";

	Category(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
