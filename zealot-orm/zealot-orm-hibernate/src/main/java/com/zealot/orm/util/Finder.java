package com.zealot.orm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

/**
 * HQL语句分页查询
 * 
 * @author tangl
 * 
 */
public class Finder {
	
	private static final Logger logger=Logger.getLogger(Finder.class);
	private String pkField;//表的主键
	protected Finder() {
	}

	/**
	 * 如果是使用group by 语句时，请指定主键字段<br/>
	 * 使用分组后分布hibernate 不能用
	 * <code>select count(*) from (select from t group by f1) a</code>
	 * 故使用
	 * <code>select count(*) from t where pkField in(
	 * select max(id) from t group by f1)
	 * </code>
	 * 性能非常差，不建议使用HQL做分组分页，使用视图，或者hibernate的subselect
	 * @param hql
	 * @param pkField
	 */
	public Finder(String hql,String pkField) {
		hqlBuilder = new StringBuilder(hql);
		this.pkField=pkField;
		logger.warn("HQL group by分页性能非常差，不健议使用!|");
	}

	public Finder(String hql) {
		hqlBuilder = new StringBuilder(hql);
	}

	public Finder append(String hql) {
		hqlBuilder.append(hql);
		return this;
	}

	/**
	 * 获得原始hql语句
	 * 
	 * @return
	 */
	public String getOrigHql() {
		return hqlBuilder.toString();
	}

	/**
	 * 获得查询数据库记录数的hql语句。
	 * 
	 * @return
	 */
	public String getRowCountHql() {
		String hql = hqlBuilder.toString();
	
		int fromIndex = hql.toLowerCase().indexOf(FROM);
		String projectionHql = hql.substring(0, fromIndex);
		hql = hql.substring(fromIndex);
		String rowCountHql = hql;//.replace(HQL_FETCH, "");
		int index = rowCountHql.indexOf(ORDER_BY);
		if (index > 0) {
			rowCountHql = rowCountHql.substring(0, index);
		}
		
		//TODO 如果分组用分布，方法需要再次修改，此功能数据一大，挂
		if(hql.matches(".*"+GROUP+"( )+.*")){
			if(pkField==null){
				throw new RuntimeException("使用分组分页查询，必须指定唯一主键!");
			}
			//TODO hql 不支持select count(*)  from  (select 1 from App_Report aa group by id) ct
			String tableName=getTableName(rowCountHql);
			return ROW_COUNT+" from "+tableName+" where "+pkField+" in(select min("+pkField+") "+rowCountHql+")";
			
		}
		return wrapProjection(projectionHql) + rowCountHql;
	}

	private String getTableName(String fromHql){
		StringBuffer one=new StringBuffer(fromHql.substring(FROM.length()));
		for(int i=0;i<one.length();i++){
			if(i+1<one.length() && ' '!=one.charAt(0)){
				break;
			}
			one.deleteCharAt(0);
		}
		String kw=one.substring(0,one.indexOf(" "));
		return kw;
	}
	
	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param param
	 * @param value
	 * @return
	 */
	public Finder setParam(String param, Object value) {
		return setParam(param, value, null);
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param param
	 * @param value
	 * @param type
	 * @return
	 */
	public Finder setParam(String param, Object value, Type type) {
		getParams().add(param);
		getValues().add(value);
		getTypes().add(type);
		return this;
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param paramMap
	 * @return
	 */
	public Finder setParams(Map<String, Object> paramMap) {
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			setParam(entry.getKey(), entry.getValue());
		}
		return this;
	}
	
	
	

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param name
	 * @param vals
	 * @param type
	 * @return
	 */
	public Finder setParamList(String name, Collection<Object> vals, Type type) {
		getParamsList().add(name);
		getValuesList().add(vals);
		getTypesList().add(type);
		return this;
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param name
	 * @param vals
	 * @return
	 */
	public Finder setParamList(String name, Collection<Object> vals) {
		return setParamList(name, vals, null);
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param name
	 * @param vals
	 * @param type
	 * @return
	 */
	public Finder setParamList(String name, Object[] vals, Type type) {
		getParamsArray().add(name);
		getValuesArray().add(vals);
		getTypesArray().add(type);
		return this;
	}

	/**
	 * 设置参数。与hibernate的Query接口一致。
	 * 
	 * @param name
	 * @param vals
	 * @return
	 */
	public Finder setParamList(String name, Object[] vals) {
		return setParamList(name, vals, null);
	}

	/**
	 * 将finder中的参数设置到query中。
	 * 
	 * @param query
	 */
	public Query setParamsToQuery(Query query) {
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				if (types.get(i) == null) {
					if(values.get(i) instanceof Collection<?>){  
	                    query.setParameterList(params.get(i), (Collection<?>)values.get(i)); 
					}else{
						query.setParameter(params.get(i), values.get(i));
					}
				} else {
					query.setParameter(params.get(i), values.get(i), types.get(i));
				}
			}
		}
		if (paramsList != null) {
			for (int i = 0; i < paramsList.size(); i++) {
				if (typesList.get(i) == null) {
					query.setParameterList(paramsList.get(i), valuesList.get(i));
				} else {
					query.setParameterList(paramsList.get(i), valuesList.get(i), typesList.get(i));
				}
			}
		}
		if (paramsArray != null) {
			for (int i = 0; i < paramsArray.size(); i++) {
				if (typesArray.get(i) == null) {
					query.setParameterList(paramsArray.get(i), valuesArray.get(i));
				} else {
					query.setParameterList(paramsArray.get(i), valuesArray.get(i), typesArray.get(i));
				}
			}
		}
		return query;
	}

	public Query createQuery(Session s) {
		return setParamsToQuery(s.createQuery(getOrigHql()));
	}

	private String wrapProjection(String projection) {
//		if (projection.indexOf("select") == -1) {
//			return ROW_COUNT;
//		} else {
//			return projection.replace("select", "select count(") + ") ";
//		}
		return ROW_COUNT;
	}

	private List<String> getParams() {
		if (params == null) {
			params = new ArrayList<String>();
		}
		return params;
	}

	private List<Object> getValues() {
		if (values == null) {
			values = new ArrayList<Object>();
		}
		return values;
	}

	private List<Type> getTypes() {
		if (types == null) {
			types = new ArrayList<Type>();
		}
		return types;
	}

	private List<String> getParamsList() {
		if (paramsList == null) {
			paramsList = new ArrayList<String>();
		}
		return paramsList;
	}

	private List<Collection<Object>> getValuesList() {
		if (valuesList == null) {
			valuesList = new ArrayList<Collection<Object>>();
		}
		return valuesList;
	}

	private List<Type> getTypesList() {
		if (typesList == null) {
			typesList = new ArrayList<Type>();
		}
		return typesList;
	}

	private List<String> getParamsArray() {
		if (paramsArray == null) {
			paramsArray = new ArrayList<String>();
		}
		return paramsArray;
	}

	private List<Object[]> getValuesArray() {
		if (valuesArray == null) {
			valuesArray = new ArrayList<Object[]>();
		}
		return valuesArray;
	}

	private List<Type> getTypesArray() {
		if (typesArray == null) {
			typesArray = new ArrayList<Type>();
		}
		return typesArray;
	}

	private StringBuilder hqlBuilder;

	private List<String> params;
	private List<Object> values;
	private List<Type> types;

	private List<String> paramsList;
	private List<Collection<Object>> valuesList;
	private List<Type> typesList;

	private List<String> paramsArray;
	private List<Object[]> valuesArray;
	private List<Type> typesArray;

	private int firstResult = 0;

	private int maxResults = 0;

	public static final String ROW_COUNT = "select count(*) ";
	public static final String FROM = "from";
	public static final String DISTINCT = "distinct";
	public static final String HQL_FETCH = "fetch";
	public static final String ORDER_BY = "order by";
	public static final String GROUP = "group";
	
	public static void main(String[] args) {
		String orgi="select * from      MyTable   aa ";
		StringBuffer one=new StringBuffer(orgi.substring(orgi.indexOf("from")+4,orgi.length()));
		for(int i=0;i<one.length();i++){
			if(i+1<one.length() && ' '!=one.charAt(0)){
				System.out.println("空格");
				break;
			}
			one.deleteCharAt(0);
		}
		System.out.println(one.substring(0,one.indexOf(" ")));
		System.out.println(one);
	}

}