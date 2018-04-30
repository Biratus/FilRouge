package fr.dta.formafond.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Entity
@SequenceGenerator(name = "user_gen", sequenceName = "user_seq", initialValue = 100, allocationSize = 1)
@Table(name="user_")
public class User extends PrimeModel {

	@Id
	@GeneratedValue(generator="user_gen")
	private long id;

	private String lastName;
	private String firstName;
	private String mail;
	private String address;
	private long phone;

	@Enumerated
	private Role role;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String lastName, String firstName, String mail, String address, long phone, Role role) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.mail = mail;
		this.address = address;
		this.phone = phone;
		this.role = role;
	}


	@Override
	public Long getId() {
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
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ObjectNode toJson() {
		ObjectNode node=JsonNodeFactory.instance.objectNode();
		node.put("firstname", this.firstName);
		node.put("lastname", this.lastName);
		node.put("role", this.role.toString());
		node.put("email", this.mail);
		node.put("address", this.address);
		node.put("telephone", this.phone);
		
		return node;
	}
}
