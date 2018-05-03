package fr.dta.formafond.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
	CLIMBING("Climbing"), DIVING("Diving"), HIKING("Hiking");

	private String name = "";

	Category(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public static List<String> getCategories(){
		return Stream.of(Category.values()).map(c -> c.name).collect(Collectors.toList());
	}
}
