package com.zealot.common;

import java.util.regex.Pattern;

import org.springframework.beans.factory.BeanFactory;

/**
 * 公用的常量
 *
 */
public final class CommonConsts {
	
	public static String SYSTEM_PROPERTY_PATH_KEY = "system.properties.file";
	
	public static String DEFAULT_SYSTEM_PROPERTY_PATH = "system.properties";
	
	public static final String CONTAINER_KEY = "web.container";
	
	public static final String CONTAINER_DEFAULT = "spring";
	
	public static final Pattern COMMA_SPLIT_PATTERN                = Pattern
            .compile("\\s*[,]+\\s*");
	
	public static String WEB_PORT="web.port";
	
	public static String WEB_PORT_DEFAULT="8080";
	
	public static String WEB_CONTEXT="web.context";
	
	public static String WEB_CONTEXT_DEFAULT="/";
	
	/**spring beanFactory @see CacheSpringBeanFactoryListener**/
	public static BeanFactory beanFactory;
	
	/**
	 * 状态为无效
	 */
	public static final int STATE_INVALID=0;
	
	/**
	 * 状态为有效
	 */
	public static final int STATE_VALID=1;

	
	public static final class operObject
	{
	    /** 用户 **/
	    public static final int USER =1;
	    /** 角色 **/
	    public static final int ROLE =2;
	    /** 权限 **/
	    public static final int RIGHT =2;
	}
	
	/** 
	 * 操作日志
	 * @author cj
	 * @since 2013-1-4
	 * @version 1.0
	 */
	public static final class operator
	{
		/** 登录 **/
		public static final int OPERATOR_LOGIN=1;
		/** 添加 **/
		public static final int OPERATOR_ADD=2;
		/** 修改 **/
		public static final int OPERATOR_MODIFY=3;
		/** 删除 **/
		public static final int OPERATOR_DELETE=4;
		/** 导出 **/
		public static final int OPERATOR_EXPORT=5;
		
		/** 授权 **/
		public static final int OPERATOR_GRANT=6;
	}
}
