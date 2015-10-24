package com.zealot.orm.model;

import java.util.List;

public class Pagination<T> extends SimplePage implements java.io.Serializable,
		Paginable {


	private static final long serialVersionUID = -621426001882549038L;
	/**
	 * 当前页的数据
	 */
	private List<T> list;
	private String paginateURL;
	private String returnurl;
	private Integer rowCount;

	public Pagination() {
	}

	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	public Pagination(int pageNo, int pageSize, int totalCount, List<T> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	public String getPaginateURL() {
		return paginateURL;
	}

	public void setPaginateURL(String paginateURL) {
		this.paginateURL = paginateURL;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getReturnurl() {
		return returnurl;
	}

	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}

	public Integer getRowCount() {

		setRowCount();
		return rowCount;
	}

	public void setRowCount() {
		if (getTotalCount() == 0)
			rowCount = 0;
		else if (getPageNo() < getTotalPage())
			rowCount = pageSize;
		else
			rowCount = getTotalCount() - (getTotalPage() - 1) * pageSize;
	}

}
