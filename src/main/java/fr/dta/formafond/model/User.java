package fr.dta.formafond.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
	@NotNull
	private String mail;
	private String address;
	@NotNull
	private String password;
	private Long phone;

	private String role;

	public User() {}

	public User(String lastName, String firstName, String mail, String address, long phone,String password) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.mail = mail;
		this.address = address;
		this.phone = phone;
		this.password=password;
		this.role = "visitor";
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

	public String getRole() {
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

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public ObjectNode toJson() {
		ObjectNode node=JsonNodeFactory.instance.objectNode();
		node.put("firstname", this.firstName);
		node.put("lastname", this.lastName);
		node.put("role", this.role);
		node.put("mail", this.mail);
		node.put("address", this.address);
		node.put("phone", this.phone);
		node.put("password", this.password);
		
		return node;
	}
	
	public List<GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(this.role));
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", mail=" + mail
				+ ", address=" + address + ", password=" + password + ", phone=" + phone + ", role=" + role + "]";
	}	
}
