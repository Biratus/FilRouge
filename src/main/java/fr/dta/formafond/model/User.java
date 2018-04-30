package fr.dta.formafond.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "user_gen", sequenceName = "user_seq", initialValue = 100, allocationSize = 1)
public class User extends PrimeModel {
	
	@Id
	@GeneratedValue(generator="user_seq")
	private long id;
	
	private String lastName;
	private String firstName;
	
	private Role role;

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public long getId() {
		return this.id;
	}

}
