package com.zealot.model.entity;

import java.io.Serializable;

public class Search implements Serializable {
	
	private static final long serialVersionUID = -5311824924675089490L;
	
	private String searchBy;
	private String keyword;
	private String orderField;
	private String orderDirection;

	public String getSearchBy() {
		return this.searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrderField() {
		return this.orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return this.orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
}
