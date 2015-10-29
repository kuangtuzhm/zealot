package com.zealot.util;

/**
 * 规则条件
 * @author zhangliang
 * @date 2015-01-20
 */
public enum Criterias {
	
	AND("AND"), OR("OR"),
	IN("IN"), NOT_IN("NOT IN"), LIKE("LIKE"), NE("!="),
	GT(">"), GTE(">="), LT("<"), LTE("<="), EQ("=");
	
	/** 条件符 **/
	private String operate;
	
	private Criterias(String operate){
		this.operate = operate;
	}

	@Override
	public String toString() {
		return this.operate;
	}
	
}
