package com.zealot.exception;

/**
 * 程序运行中可能出现的异常,需要输出exception信息
 * 
 */
public class AppException extends Exception
{
    private static final long serialVersionUID = 1111L;
    
    private String code;
    
    public AppException(String[] errorMessage,Throwable e)
    {
        this(errorMessage[0],errorMessage[1],e);
    }
    
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
