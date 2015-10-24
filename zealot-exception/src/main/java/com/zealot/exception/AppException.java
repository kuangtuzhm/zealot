package com.zealot.exception;

/**
 * 程序运行中可能出现的异常,需要输出exception信息
 * 
 */
public class AppException extends Exception
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -6613208305497195589L;
	
	private String code;
    
    public AppException(String code,String msg,Throwable e)
    {
        super(msg,e);
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
