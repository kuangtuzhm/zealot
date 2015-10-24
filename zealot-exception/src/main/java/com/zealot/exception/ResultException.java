package com.zealot.exception;

/**
 * 业务Exception
 *
 */
public class ResultException extends Exception
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8952870862203905329L;
	
	private String code;
    
    public ResultException(String[] errorMessage)
    {
        this(errorMessage[0],errorMessage[1]);
    }
    
    public ResultException(String code,String msg)
    {
        super(msg);
        this.code = code;
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
