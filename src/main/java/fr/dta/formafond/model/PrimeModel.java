package fr.dta.formafond.model;

public abstract class PrimeModel {

	public abstract Long getId();

	@Override
	public int hashCode() {
		return getId().intValue();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.getClass().equals(o.getClass()) && this.hashCode()==o.hashCode();
	}
}