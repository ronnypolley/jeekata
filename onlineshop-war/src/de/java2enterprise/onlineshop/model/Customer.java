package de.java2enterprise.onlineshop.model;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 7400510414063580289L;
	private String email;
	private String password;
	private Long id;

	public Customer() {
	}

	public Customer(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [email=" + email + ", password=" + password + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
