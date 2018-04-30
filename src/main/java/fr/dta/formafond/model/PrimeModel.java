package fr.dta.formafond.model;

public abstract class PrimeModel {

	public abstract long getId();

	@Override
	public int hashCode() {
		return (int)getId();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.getClass().equals(o.getClass()) && this.hashCode()==o.hashCode();
	}
}