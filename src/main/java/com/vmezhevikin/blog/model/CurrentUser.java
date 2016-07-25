package com.vmezhevikin.blog.model;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.vmezhevikin.blog.Constants;
import com.vmezhevikin.blog.entity.Author;

public class CurrentUser extends User {
	
	private static final long serialVersionUID = 5473215165995605379L;
	
	private Long id;
	private String name;

	public CurrentUser(Author author) {
		super(author.getName(), author.getPassword(), true, true, true, true, Collections.singleton(new SimpleGrantedAuthority(Constants.USER)));
		this.id = author.getId();
		this.name = author.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}