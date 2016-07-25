package com.vmezhevikin.blog.form;

import java.io.Serializable;

public class SignUpForm implements Serializable {

	private static final long serialVersionUID = -4085983345204414938L;
	
	private String name;
	private String email;
	private String password;
	private String confirm;

	public SignUpForm() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
}