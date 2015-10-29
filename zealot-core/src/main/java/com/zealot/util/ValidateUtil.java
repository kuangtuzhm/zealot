package com.zealot.util;

import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @desc 校验工具
 * @date 2015-01-30
 */
public class ValidateUtil {
	
	/**
	 * 是否为空或者值等于 -1
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Integer value){
		if(value == null){
			return true;
		}
		if(value == -1){
			return true;
		}
		return false;
	}
	
	/**
	 * 字符串是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value){
		return (value==null || value.trim().length()<=0);
	}
	
	/**
	 * 字节数组是否为空
	 * @param bytes
	 * @return
	 */
	public static boolean isEmpty(byte[] bytes){
		return (bytes==null || bytes.length<=0);
	}
	
	/**
	 * 字符数组是否为空
	 * @param chars
	 * @return
	 */
	public static boolean isEmpty(char[] chars){
		return (chars==null || chars.length<=0);
	}
	
	/**
	 * 字符串数组是否为空
	 * @param values
	 * @return
	 */
	public static boolean isEmpty(String[] values){
		return (values==null || values.length<=0);
	}
	
	/**
	 * Collection以及子类集合是否不为空
	 * @param collection
	 * @return
	 */
	public static <V> boolean isEmpty(Collection<V> collection){
		return (collection==null || collection.size()<=0);
	}
	
	/**
	 * map以及子类集合是否为空
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map){
		return (map==null || map.size()<=0);
	}
	
	/**
	 * 对象是否为空
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object){
		return (object == null);
	}
	
	/**
	 * 对象数组是否为空
	 * @param objects
	 * @return
	 */
	public static boolean isEmpty(Object[] objects){
		return (objects==null || objects.length<=0);
	}
	
	/**
	 * 是否不为空并且值不等于 -1
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(Integer value){
		return (!isEmpty(value));
	}
	
	/**
	 * 字符串是否不为空
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value){
		return (!isEmpty(value));
	}
	
	/**
	 * 字节数组是否不为空
	 * @param bytes
	 * @return
	 */
	public static boolean isNotEmpty(byte[] bytes){
		return (!isEmpty(bytes));
	}
	
	/**
	 * 字符数组是否不为空
	 * @param chars
	 * @return
	 */
	public static boolean isNotEmpty(char[] chars){
		return (!isEmpty(chars));
	}
	
	/**
	 * 字符串数组是否不为空
	 * @param values
	 * @return
	 */
	public static boolean isNotEmpty(String[] values){
		return (!isEmpty(values));
	}
	
	/**
	 * Collection以及子类集合是否不为空
	 * @param collection
	 * @return
	 */
	public static <V> boolean isNotEmpty(Collection<V> collection){
		return (!isEmpty(collection));
	}
	
	/**
	 * map以及子类集合是否不为空
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isNotEmpty(Map<K, V> map){
		return (!isEmpty(map));
	}
	
	/**
	 * 对象是否不为空
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Object object){
		return (!isEmpty(object));
	}
	
	/**
	 * 对象数组是否不为空
	 * @param objects
	 * @return
	 */
	public static boolean isNotEmpty(Object[] objects){
		return (!isEmpty(objects));
	}
	
	/**
	 * 是否为日期
	 * @param text
	 * @return
	 */
	public static boolean isDate(Object text){
		Integer type = SqlExpress.getValueType(text);
		boolean isDate = false;
		if(Types.DATE==type || Types.TIMESTAMP==type){
			isDate = true;
		}
		if(isDate == false){
			isDate = isDate(String.valueOf(text));
		}
		return isDate;
	}
	
	/**
	 * 是否为日期
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date){
		boolean isDate = false;
		if(isNotEmpty(date)){
			String[] patterns = {"yyyy-MM-dd", "yyyy/MM/dd" ,"yyyy年MM月dd日"};
			SimpleDateFormat format = null;
			for(String pattern : patterns){
				format = new SimpleDateFormat(pattern);
				try {
					format.parse(date);
					isDate = true;
					break;
				} catch (ParseException e) {
				}
			}
			if(isDate == false){
				int length = StringUtil.getNumberStr(date).length();
				if(length==8 || length==14){
					isDate = true;
				}
			}
		}
		return isDate;
	}
	
	/**
	 * 是否为时间
	 * @param text
	 * @return
	 */
	public static boolean isTime(Object text){
		Integer type = SqlExpress.getValueType(text);
		boolean isTime = false;
		if(Types.TIME == type){
			isTime = true;
		}
		if(isTime == false){
			isTime = isTime(String.valueOf(text));
		}
		return isTime;
	}
	
	/**
	 * 是否为时间
	 * @param time
	 * @return
	 */
	public static boolean isTime(String time){
		boolean isTime = false;
		if(isNotEmpty(time)){
			String[] patterns = {"HH:mm:ss", "HH/mm/ss", "HH时mm分ss秒"};
			SimpleDateFormat format = null;
			for(String pattern : patterns){
				format = new SimpleDateFormat(pattern);
				try {
					format.parse(time);
					isTime = true;
					break;
				} catch (ParseException e) {
				}
			}
			if(isTime == false){
				if(StringUtil.getNumberStr(time).length() == 6){
					isTime = true;
				}
			}
		}
		return isTime;
	}
	
	/**
	 * 清除指定的字符并去掉空格后是否包含指定的子字符串,不区分大小写
	 * @param value
	 * @param separator
	 * @param subStr
	 * @return
	 */
	public static boolean contains(String value, String separator, String subStr){
		if(isNotEmpty(value)){
			ArrayList<String> list = StringUtil.divideString2(value, separator);
			if(isNotEmpty(list)){
				StringBuilder parentStr = new StringBuilder("");
				for(String s : list){
					parentStr.append(s);
				}
				return parentStr.toString().toUpperCase().indexOf(subStr) != -1;
			}
		}
		return false;
	}
	
	/**
	 * Map集合中是否存在指定的key
	 * @param params
	 * @param key
	 * @return
	 */
	public static <K, V> boolean contains(Map<K, V> params, K key){
		if(isNotEmpty(params) && isNotEmpty(key)){
			if(params.containsKey(key)){
				V value = params.get(key);
				if(isNotEmpty(value)){
					int type = SqlExpress.getValueType(value);
					switch(type){
					case Types.INTEGER:
						return (Integer.parseInt(String.valueOf(value)) != -1);
						
					case Types.DOUBLE:
						return (Double.parseDouble(String.valueOf(value)) != -1.0);
						
					case Types.VARCHAR:
						return isNotEmpty(String.valueOf(value));
						
					default:
						return isNotEmpty(value);
					}
				}
			}
		}
		return false;
	}
}
