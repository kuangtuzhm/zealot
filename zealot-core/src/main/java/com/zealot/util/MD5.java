package com.zealot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class MD5{
	
	private static final char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/**
     * 32位MD5加密算法
     * @param bytes
     * @return
     */
	public static String convert32(String s)
	{
        try {
            byte[] bytes = s.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        }
        catch (Exception e){
            return null;
        }
    }
	
	/**
     * 16位MD5加密算法
     * @param bytes
     * @return
     */
	public static String convert16(String s)
	{
        try {
            byte[] bytes = s.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0x0f];
                chars[k++] = hexChars[b & 0x0f];
            }
            return new String(chars).substring(8, 24);
        }
        catch (Exception e)
        {
            return null;
        }
	}
    
    /**
     * 获取文件的MD5值 32位
     * @param file 文件
     * @return 32位MD5值
     */
    public static String convertFile32(File file) 
    {
    	if(file==null || !file.exists())
    	{
    		return null;
    	}
    	InputStream fis = null;
        try {
        	fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) 
            {
                md5.update(buffer, 0, numRead);
            }
            byte[] b=md5.digest();
            StringBuilder sb = new StringBuilder(b.length * 2);
            for (int i = 0; i < b.length; i++) {
                sb.append(hexChars[(b[i] & 0xf0) >>> 4]);
                sb.append(hexChars[b[i] & 0x0f]);
            }
            return sb.toString();
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        finally
        {
        	if(fis!=null)
        	{
        		try
				{
					fis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
        	}
        }
        return null;
    }
}