package com.example.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class School {
	@Id
	private int id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String username;

	public School() {

	}

	public School(int id, String name, String description, String username) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.username = username;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
