package fr.dta.formafond.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "user_gen", sequenceName = "user_seq", initialValue = 100, allocationSize = 1)
@Table(name="user_")
public class User extends PrimeModel {
	
	@Id
	@GeneratedValue(generator="user_gen")
	private long id;
	
	private String lastName;
	private String firstName;
	
	private String username;

	private Role role;

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String lastName, String firstName, String username,Role role) {
		super();
		this.lastName = lastName;
		this.username = username;
		this.firstName = firstName;
		this.role = role;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Role getRole() {
		return role;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
