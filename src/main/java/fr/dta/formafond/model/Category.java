package fr.dta.formafond.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
	CLIMBING("Alpinisme / Escalade"), DIVING("Plongée"), HIKING("Randonnée");

	private String name = "";

	Category(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public static List<String> getCategories() {
		return Stream.of(Category.values()).map(c -> c.name).collect(Collectors.toList());
	}

	public static Category fromString(String s) {
		for (Category c : values()) {
			if (c.name.equals(s))
				return c;
		}
		return null;
	}
}
