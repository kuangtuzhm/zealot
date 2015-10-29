package com.zealot.common;

import org.springframework.beans.factory.BeanFactory;

/**
 * 公用的常量
 * @author tangl
 *
 */
public final class CommonConsts {
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
