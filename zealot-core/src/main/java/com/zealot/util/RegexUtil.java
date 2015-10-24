package com.zealot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 正则表达式工具类
 * @version 	1.0 2012-11-9
 * @author		tangl
 * @history	
 *
 */
public class RegexUtil
{
	private static Logger LOGGER=Logger.getLogger(RegexUtil.class);
	
	/**
	 * 返回指定正则表达式内容
	 * @param content 查找到内容
	 * @param regex 正则表达式
	 * @return 匹配后的内容
	 */
	public static String getMatcherStr(String content,String regex)
	{
		return getMatcherStr(content,regex,null);
	}
	
	/**
	 * 返回指定正则表达式内容数组
	 * @param content 查找到内容
	 * @param regex 正则表达式
	 * @return null or empty
	 */
	public static String[] getMatcherArray(String content,String regex)
	{
		String split=";-;";
		String arrays=getMatcherStr(content,regex,split);
		return "".equals(arrays)?null:arrays.split(split);
	}
	
	/**
	 * 返回指定正则表达式内容
	 * @param content 查找到内容
	 * @param regex 正则表达式
	 * @param groupSplit 指定匹配组后添加分配内容
	 * @return 匹配后的内容
	 */
	public static String getMatcherStr(String content,String regex,String groupSplit)
	{
		try
		{
			StringBuffer sb=new StringBuffer();
			Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
			Matcher matcher=pattern.matcher(content);
			while(matcher.find())
			{
				sb.append(matcher.group());
				if(groupSplit!=null)
				{
					sb.append(groupSplit);
				}
			}
			return sb.toString();
		}catch(Exception e)
		{
			LOGGER.error("正则匹配异常!",e);
		}
		return "";
	}
	
	/**
	 * 判断输入内容是否包含字符[a-zA-Z0-9]或者汉字
	 * @param input 字符
	 * @return true/false
	 */
	public static boolean includeChar(String input)
	{
		return Pattern.matches(".*(\\d|[a-zA-Z]|[\\u4E00-\\u9FA5])+.*",input);
	}
	
	/**
	 * 是否是一个[0-9]的整数
	 * @param input 匹配内容
	 * @return true/false
	 */
	public static boolean isNumber(String input)
	{
		if(StringUtils.isEmpty(input))
		{
			return false;
		}
		return input.matches("\\d+");
	}

	/**
	 * 是否是一个有效的邮件地址
	 * @param email 邮件地址
	 * @return 是否有效
	 */
	public static boolean isEmail(String email)
	{
       String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
       return Pattern.matches(regex,email);
	}
	
	/**
	 * 验证是否是有效IP地址 0.0.0.0-255.255.255.255
	 * @param ip IP地址
	 * @return true/false
	 */
	public static boolean isIp(String ip)
	{
		String regex = "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])";
		return Pattern.matches(regex,ip);
	}
	
	/**
	 * 验证是否为url地址
	 * @param url
	 * @return
	 */
	public static boolean isUrl(String url){
		String regex = "(((f|ht){1}tp://)[-a-zA-Z0-9@:%_\\+\\.~#?&//=]+)";
		return Pattern.matches(regex, url);
		
	}
}
