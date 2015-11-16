package com.zealot.exception.message;

public class MessageCode {
	
	/**
	 * 服务器错误
	 */
	public final static String [] SERVER_BUSY={"M1000","服务器繁忙"};
	
	public final static String [] SERVER_EXCUTE_ERROR={"M1001","程序处理错误!"};
	
	
	/**
     * 用户错误
     */
	public final static String [] USER_NOT_EXITS={"M1100","用户不存在!"};
	
    public final static String [] USER_OLDPWD_ERROR={"M1101","用户旧密码填写不正确!"};

    public final static String [] USER_LOGIN_ERR={"M1102","用户名或密码错误!"};
    
    public final static String [] USER_LOGINNAME_EXITS={"M1103","用户登录名已经存在!"};
    
    public final static String [] USER_STOPED={"M1104","用户已经被暂停!"};
    
    public final static String [] USER_ADMIN_NODELETE={"M1105","超级用户不能被删除!"};
    
    /**
     * 角色错误
     */
    public final static String [] ROLE_NAME_EXITS={"M1201","角色名已经存在!"};
    
    public final static String [] ROLE_NOT_EXITS={"M1202","角色已经不存在!"};
    
    public final static String [] ROLE_STOPED={"M1203","角色已经被暂停，不能授权!"};
    
    public final static String [] ADMIN_CANNOT_STOP={"M1204","超级管理员角色不能被暂停!"};
    
    /**
     * 权限错误
     */
    public final static String [] RIGHTS_CODE_EXITS={"M1301","菜单编码已经存在!"};
    
    /**
     * 部门错误
     */
    public final static String [] DEP_CHILD_EXITS={"M1401","部门下有子部门,不能执行删除操作!"};
    
    public final static String [] DEP_USER_EXITS={"M1402","部门下有成员存在,不能执行删除操作!"};
}
