package com.zealot.util;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @desc Sql表达式
 * @author zhangliang
 * @date 2015-01-20
 */
public class SqlExpress {
	protected Log logger = LogFactory.getLog(this.getClass());
	/** 源名称参数 **/
	private Map<String, Object> paramsMap;
	/** 目标名称参数 **/
	private Map<String, Object> queryParamsMap;
	/** 占位符参数 **/
	private List<Object> queryParamsList;
	/** 需要追加的sql字符串 **/
	private StringBuilder sql;
	/** 是否为名称参数 **/
	private boolean isNamed;
	
	public SqlExpress() {
		super();
	}

	/**
	 * 追加名称参数的sql/hql语句
	 * @param sql				追加sql/hql语句
	 * @param params			源名称参数集合
	 * @param queryParamsMap	目标名称参数集合
	 */
	public SqlExpress(StringBuilder sql, Map<String, Object> params, Map<String, Object> queryParamsMap){
		this.initParams(String.valueOf(sql), params, queryParamsMap, null, true);
	}
	
	/**
	 * 追加名称参数的sql/hql语句
	 * @param sql				追加sql/hql语句
	 * @param params			源名称参数集合
	 * @param queryParamsMap	目标名称参数集合
	 */
	public SqlExpress(String sql, Map<String, Object> params, Map<String, Object> queryParamsMap){
		this.initParams(sql, params, queryParamsMap, null, true);
	}
	
	/**
	 * 追加占位符的sql/hql语句
	 * @param sql/hql		追加sql/hql语句
	 * @param params		源名称参数集合
	 * @param queryParamsList	目标名称参数集合
	 */
	public SqlExpress(StringBuilder sql, Map<String, Object> params, List<Object> queryParamsList){
		this.initParams(String.valueOf(sql), params, null, queryParamsList, false);
	}
	
	/**
	 * 追加占位符的sql/hql语句
	 * @param sql/hql		追加sql/hql语句
	 * @param params		源名称参数集合
	 * @param queryParamsList	目标名称参数集合
	 */
	public SqlExpress(String sql, Map<String, Object> params, List<Object> queryParamsList){
		this.initParams(sql, params, null, queryParamsList, false);
	}
	
	/**
	 * 初始化参数值
	 * @param sql				sql/hql语句
	 * @param params			源参数集合
	 * @param queryParamsMap	存放的目标参数集合 Map
	 * @param queryParamsList	存放的目标参数集合 List
	 * @param isNamed			是否为名称参数
	 */
	private void initParams(String sql, Map<String, Object> params, Map<String, Object> queryParamsMap, List<Object> queryParamsList, boolean isNamed){
		if(sql == null){
			sql = new String();
		}
		if(params == null){
			params = new HashMap<String, Object>();
		}
		if(queryParamsMap == null){
			queryParamsMap = new HashMap<String, Object>();
		}
		if(queryParamsList == null){
			queryParamsList = new ArrayList<Object>();
		}
		this.sql = new StringBuilder(sql);
		this.paramsMap = params;
		this.queryParamsMap = queryParamsMap;
		this.queryParamsList = queryParamsList;
		this.isNamed = isNamed;
	}
	
	/**
	 * 追加And相等条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress andEq(String field, String property){
		return this.eq(field, property, true);
	}
	
	/**
	 * 追加And大于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress andGt(String field, String property){
		return this.gt(field, property, true);
	}
	
	/**
	 * 追加And大于等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress andGte(String field, String property){
		return this.gte(field, property, true);
	}
	
	/**
	 * 追加And小于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress andLt(String field, String property){
		return this.lt(field, property, true);
	}
	
	/**
	 * 追加And小于等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress andLte(String field, String property){
		return this.lte(field, property, true);
	}
	
	/**
	 * 追加And不等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress andNe(String field, String property){
		return this.ne(field, property, true);
	}
	
	/**
	 * 追加And模糊查询条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress andLike(String field, String property){
		return this.like(field, property, true);
	}
	
	/**
	 * 追加Or相等条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress orEq(String field, String property){
		return this.eq(field, property, false);
	}
	
	/**
	 * 追加Or大于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress orGt(String field, String property){
		return this.gt(field, property, false);
	}
	
	/**
	 * 追加Or大于等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress orGte(String field, String property){
		return this.gte(field, property, false);
	}
	
	/**
	 * 追加Or小于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress orLt(String field, String property){
		return this.lt(field, property, false);
	}
	
	/**
	 * 追加Or小于等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress orLte(String field, String property){
		return this.lte(field, property, false);
	}
	
	/**
	 * 追加Or不等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress orNe(String field, String property){
		return this.ne(field, property, false);
	}
	
	/**
	 * 追加Or模糊查询条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @return
	 */
	public SqlExpress orLike(String field, String property){
		return this.like(field, property, false);
	}
	
	/**
	 * 追加相等条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @param isAnd		条件： and/or
	 * @return
	 */
	protected SqlExpress eq(String field, String property, boolean isAnd){
		if(this.isNamed){
			return this.appendNamedParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.EQ);
		}else{
			return this.appendPlaceParams(field, property,
					isAnd? Criterias.AND : Criterias.OR, Criterias.EQ);
		}
	}
	
	/**
	 * 追加大于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @param isAnd		条件： and/or
	 * @return
	 */
	protected SqlExpress gt(String field, String property, boolean isAnd){
		if(this.isNamed){
			return this.appendNamedParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.GT);
		}else{
			return this.appendPlaceParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.GT);
		}
	}
	
	/**
	 * 追加大于等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @param isAnd		条件： and/or
	 * @return
	 */
	protected SqlExpress gte(String field, String property, boolean isAnd){
		if(this.isNamed){
			return this.appendNamedParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.GTE);
		}else{
			return this.appendPlaceParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.GTE);
		}
	}
	
	/**
	 * 追加小于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @param isAnd		条件： and/or
	 * @return
	 */
	protected SqlExpress lt(String field, String property, boolean isAnd){
		if(this.isNamed){
			return this.appendNamedParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.LT);
		}else{
			return this.appendPlaceParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.LT);
		}
	}
	
	/**
	 * 追加小于等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @param isAnd		条件： and/or
	 * @return
	 */
	protected SqlExpress lte(String field, String property, boolean isAnd){
		if(this.isNamed){
			return this.appendNamedParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.LTE);
		}else{
			return this.appendPlaceParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.LTE);
		}
	}
	
	/**
	 * 追加不等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @param isAnd		条件： and/or
	 * @return
	 */
	protected SqlExpress ne(String field, String property, boolean isAnd){
		if(this.isNamed){
			return this.appendNamedParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.NE);
		}else{
			return this.appendPlaceParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.NE);
		}
	}
	
	/**
	 * 追加不等于条件的sql/hql语句
	 * @param field	字段名称
	 * @param property	属性值名称
	 * @param isAnd		条件： and/or
	 * @return
	 */
	protected SqlExpress like(String field, String property, boolean isAnd){
		if(this.isNamed){
			return this.appendNamedParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.LIKE);
		}else{
			return this.appendPlaceParams(field, property, 
					isAnd? Criterias.AND : Criterias.OR, Criterias.LIKE);
		}
	}
	
	/**
	 * 追加名称参数的sql/hql语句
	 * @param field		字段名称
	 * @param property		属性值名称
	 * @param whereWith		条件： and/or
	 * @param compareWith	运算符条件
	 */
	protected SqlExpress appendPlaceParams(String field, String property, Criterias whereWith, Criterias compareWith){
		if(ValidateUtil.contains(this.paramsMap, property)){
			this.sql.append(" ").append(whereWith).append(" ").append(field);
			if("LIKE".equals(compareWith.toString())){
				this.sql.append(" ").append(compareWith).append(" '%").append(StringUtil.escapeSQL(this.paramsMap.get(property).toString())).append("%'");
				
			}else{
				Object value = this.paramsMap.get(property);
				this.sql.append(compareWith).append("?");
				
				boolean isExistsDate = (property.trim().toUpperCase().endsWith("TIME") 
						|| property.trim().toUpperCase().endsWith("DATE"));
				if(isExistsDate){
					if(ValidateUtil.isDate(value)){
						switch(getValueType(value)){
						case Types.DATE:
						case Types.TIMESTAMP:
							this.queryParamsList.add(this.getDate(property, value));
							break;
						default:
							this.queryParamsList.add(this.getDateStr(property, value));
							break;
						}
					}else{
						this.queryParamsList.add(value);
					}
				}else{
					this.queryParamsList.add(value);
				}
			}
		}
		return this;
	}
	
	/**
	 * 追加占位符参数的sql/hql语句
	 * @param field			字段名称
	 * @param property		属性值名称
	 * @param whereWith		条件： and/or
	 * @param compareWith	运算符条件
	 */
	protected SqlExpress appendNamedParams(String field, String property, Criterias whereWith, Criterias compareWith){
		if(ValidateUtil.contains(this.paramsMap, property)){
			this.sql.append(" ").append(whereWith).append(" ").append(field);
			Object value = this.paramsMap.get(property);
			if("LIKE".equals(compareWith.toString())){
				this.sql.append(" ").append(compareWith).append(" '%").append(StringUtil.escapeSQL(value.toString())).append("%'");
				
			}else{
				this.sql.append(compareWith).append(":").append(property);
				boolean isExistsDate = (property.trim().toUpperCase().endsWith("TIME") 
										|| property.trim().toUpperCase().endsWith("DATE"));
				if(isExistsDate){
					if(ValidateUtil.isDate(value)){
						switch(getValueType(value)){
						case Types.DATE:
						case Types.TIMESTAMP:
							this.queryParamsMap.put(property, this.getDate(property, value));
							break;
							default:
								this.queryParamsMap.put(property, this.getDateStr(property, value));
								break;
						}
					}else{
						this.queryParamsMap.put(property, value);
					}
				}else{
					this.queryParamsMap.put(property, value);
				}
			}
		}
		return this;
	}
	
	/**
	 * 分组
	 * @param fields	分组字段
	 * @return
	 */
	public SqlExpress groupBy(String... fields){
		if(ValidateUtil.isNotEmpty(fields)){
			if(ValidateUtil.contains(this.sql.toString(), " ", "GROUPBY")){
				this.sql.append(",");
			}else{
				this.sql.append(" GROUP BY");
			}
			if(fields.length == 1){
				this.sql.append(" ").append(fields[0]);
			}else{
				for(String field : fields){
					this.sql.append(" ").append(field).append(",");
				}
				this.sql.deleteCharAt(this.sql.lastIndexOf(","));
			}
		}
		return this;
	}
	
	/**
	 * 降序
	 * @param fields	排序字段
	 * @return
	 */
	public SqlExpress desc(String... fields){
		return this.orderBy(Order.DESC, fields);
	}
	
	/**
	 * 升序
	 * @param fields	升序字段
	 * @return
	 */
	public SqlExpress asc(String... fields){
		return this.orderBy(Order.ASC, fields);
	}
	
	/**
	 * 限制查询结果集条数
	 * @param num	数量
	 * @param limit	限制数量
	 * @return
	 */
	public SqlExpress limit(Integer num, Integer limit){
		this.sql.append(" LIMIT ");
		if(this.isNamed){
			if(num!=null && limit!=null){
				this.sql.append(":num, :limit");
				this.queryParamsMap.put("num", num);
				this.queryParamsMap.put("limit", limit);
			}else if(num != null){
				this.sql.append(":num");
				this.queryParamsMap.put("num", num);
			}else if(limit != null){
				this.sql.append(":limit");
				this.queryParamsMap.put("limit", limit);
			}
		}else{
			if(num!=null && limit!=null){
				this.sql.append("?, ?");
				this.queryParamsList.add(num);
				this.queryParamsList.add(limit);
			}else if(num != null){
				this.sql.append("?");
				this.queryParamsList.add(num);
			}else if(limit != null){
				this.sql.append("?");
				this.queryParamsList.add(limit);
			}
		}
		return this;
	}
	
	/**
	 * 追加sql/hql语句的排序条件
	 * @param order		排序方式
	 * @param fields	排序字段
	 * @return
	 */
	protected SqlExpress orderBy(Order order, String... fields){
		if(ValidateUtil.isNotEmpty(fields)){
			if(ValidateUtil.contains(this.sql.toString(), " ", "ORDERBY")){
				this.sql.append(",");
			}else{
				this.sql.append(" ORDER BY");
			}
			if(fields.length == 1){
				this.sql.append(" ").append(fields[0]).append(" ").append(order);
			}else{
				for(String field : fields){
					this.sql.append(" ").append(field).append(" ").append(order).append(",");
				}
				this.sql.deleteCharAt(this.sql.lastIndexOf(","));
			}
		}
		return this;
	}
	
	/**
	 * 获取查询总行数sql/hql语句
	 * @return
	 */
	public String getRowCountSql(StringBuilder _sql){
		if(_sql != null){
			return this.getRowCountSql(_sql.toString());
		}
		return "";
	}
	
	/**
	 * 获取查询总行数sql/hql语句
	 * @param sql	查询语句
	 * @return
	 */
	public String getRowCountSql(String _sql){
		if(ValidateUtil.isNotEmpty(_sql)){
			int index = _sql.toUpperCase().indexOf("FROM");
			if(index != -1){
				return "SELECT COUNT(*) " + _sql.substring(index);
			}
		}
		return _sql;
	}
	
	/**
	 * 获取查询总行数sql/hql语句
	 * @return
	 */
	public String getRowCountSql(){
		return this.getRowCountSql(this.sql);
	}
	
	/**
	 * 获取日期字符串
	 * @param property
	 * @param value
	 * @return
	 */
	protected String getDateStr(String property, Object value){
		boolean isExistsBegin = property.trim().toUpperCase().startsWith("BEGIN");
		boolean isExistsEnd = property.trim().toUpperCase().startsWith("END");
		
		if(isExistsBegin){
			return DateUtil.getBeginDate(value);
		}else if(isExistsEnd){
			return DateUtil.getEndDate(value);
		}
		return String.valueOf(value);
	}
	
	/**
	 * 获取日期值
	 * @param property
	 * @param value
	 * @return
	 */
	protected Date getDate(String property, Object value){
		return DateUtil.parseDate(getDateStr(property, value), 
				DateUtil.YYYY_MM_DD_HH_MM_SS);
	}
	
	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public void setQueryParamsList(List<Object> queryParamsList) {
		this.queryParamsList = queryParamsList;
	}

	public Map<String, Object> getQueryParamsMap() {
		return queryParamsMap;
	}
	
	public List<Object> getQueryParamsList() {
		return queryParamsList;
	}

	public StringBuilder getSql() {
		return sql;
	}
	
	public void setSql(StringBuilder sql) {
		this.sql = sql;
	}

	public void setNamed(boolean isNamed) {
		this.isNamed = isNamed;
	}
	
	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public boolean isNamed() {
		return isNamed;
	}

	public void setQueryParamsMap(Map<String, Object> queryParamsMap) {
		this.queryParamsMap = queryParamsMap;
	}

	/**
	 * 获取值的类型
	 * @param value	值
	 * @return java.sql.Types
	 */
	public static Integer getValueType(Object value){
		if(value instanceof java.lang.Integer){
			return Types.INTEGER;
		}else if(value instanceof java.lang.Float){
			return Types.FLOAT;
		}else if(value instanceof java.lang.Double){
			return Types.DOUBLE;
		}else if(value instanceof java.lang.String){
			return Types.VARCHAR;
		}else if(value instanceof java.util.Date){
			return Types.DATE;
		}else if(value instanceof java.sql.Time){
			return Types.TIME;
		}else if(value instanceof java.sql.Timestamp){
			return Types.TIMESTAMP;
		}
		return Types.NULL;
	}
	
	/**
	 * @desc 排序规则
	 * @author zhangliang
	 * @date 2014-01-23
	 */
	public enum Order {
		ASC, DESC
	}
	
//	public static void main(String[] args) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("channelSid", "LK123");
//		params.put("channelCid", "1001");
//		params.put("channelCode", "LK123_1001");
//		params.put("id", 1);
//		Integer pageNo = 1;
//		Integer pageSize = 20;
//		
//		StringBuilder hql = new StringBuilder("SELECT * FROM iadsupport.ad_channel WHERE 1=1");
//		
//		Map<String, Object> queryParams = new HashMap<String, Object>();
//		SqlExpress sqlExpress = new SqlExpress(hql, params, queryParams);
//		sqlExpress
//			.andEq("channel_sid", "channelSid")
//			.andEq("channel_cid", "channelCid")
//			.andLike("channel_code", "channelCode")
//			.orGt("id", "id")
//			.desc("id")
//			.asc("channel_code")
//			.limit(pageNo, pageSize);
//		
//		System.out.println(sqlExpress.getSql());
//		System.out.println(sqlExpress.getQueryParamsMap());
//		
//		System.out.println("======================================");
//		
//		ArrayList<Object> queryParamsList = new ArrayList<Object>();
//		SqlExpress express = new SqlExpress(hql, params, queryParamsList);
//		express
//		.andEq("channel_sid", "channelSid")
//		.andGt("channel_cid", "channelCid")
//		.andLike("channel_code", "channelCode")
//		.groupBy("channel_sid", "channel_cid", "name")
//		.desc("id", "channel_code")
//		.asc("channel_sid", "channel_cid")
//		.limit(pageNo, pageSize);
//		
//		System.out.println(express.getSql());
//		System.out.println(express.getQueryParamsList());
//		
//		System.out.println("\n===================================\n");
//		
//		System.out.println(express.getRowCountSql(express.getSql()));
//	}
}
