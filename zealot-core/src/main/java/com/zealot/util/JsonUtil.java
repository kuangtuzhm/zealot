package com.zealot.util;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private final static ObjectMapper mapper = new ObjectMapper();
	static{
		//此设置允许属性名没有被双引号引起来，如果不加需要这样{"name":"zzzz"},加了可以{name:"zzzzz"}
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setDateFormat(new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS));
	}
	public static <T> T fromJson(String json, Class<T> clazz){
		T t = null;
		if(!StringUtil.hasText(json))
			return null;
		try {
		t = mapper.readValue(json, clazz);	
		}catch(Exception e){
			logger.warn(e.getMessage(),e);
		}
		return t;
	}

	public static String toJson(Object object) {
		String json = null;
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.warn(e.getMessage(),e);
		}
		return json;
	}
}