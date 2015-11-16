package com.zealot.model.entity;

import java.io.Serializable;

public class Operator<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7857023811407468143L;

	private T id;
	
	private String account;
	
	private String name;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
}
