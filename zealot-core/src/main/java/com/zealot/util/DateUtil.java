package com.zealot.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 * @author tangl
 *
 */
public final class DateUtil {
	/**日期格式 yyyyMMdd**/
	public static final String YYYYMMDD="yyyyMMdd";
	
	/**日期格式 yyyy-MM-dd**/
	public static final String YYYY_MM_DD="yyyy-MM-dd";
	
	/**日期格式 yyyy-MM-dd HH:mm:ss**/
	public static final String YYYY_MM_DD_HH_MM_SS=YYYY_MM_DD+" HH:mm:ss";
	
	/**日期格式 yyyyMMddHHmmss**/
	public static final String YYYYMMDDHHMMSS="yyyyMMddHHmmss";
	
	/**
	 * 获取现时
	 */
	 public static Date currentTimeMillis(){
		 return new Date(System.currentTimeMillis());
	 }
	
	/**
	 * 得到当前日期如果参数为空返回yyyy-MM-dd
	 * @param pattern 格式化格式
	 * @return
	 */
	public static String formatDate(String pattern){
		DateFormat simpleDateFormat=new SimpleDateFormat(pattern==null?YYYY_MM_DD:pattern);
		String sdate=simpleDateFormat.format(new Date(System.currentTimeMillis()));
		return sdate;
	}
	
	
	/**
	 * 得到当前日期如果参数为空返回yyyy-MM-dd
	 * @param date
	 * @param pattern 格式化格式
	 * @return
	 */
	public static String formatDate(Date date,String pattern){
		DateFormat simpleDateFormat=new SimpleDateFormat(pattern==null?YYYY_MM_DD:pattern);
		String sdate=simpleDateFormat.format(date==null?new Date():date);
		return sdate;
	}
	
	/**
	 * 得到日期中的天
	 * @param date
	 * @return 1-日期中最后一天
	 */
	public static int  getDay(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date==null?calendar.getTime():date);
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	/**
	 * 得到日期中的月
	 * @param date
	 * @return 1-12
	 */
	public static int  getMonth(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date==null?new Date():date);
		return calendar.get(Calendar.MONTH)+1;
	}
	
	/**
	 * 得到日期中的小时
	 * @param date 
	 * @return 0-23
	 */
	public static int  getHour(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date==null?new Date():date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 得到日期中日期为周几
	 * @param date 
	 * @return 1-7(周一-周日)
	 */
	public static int  getWeek(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date==null?new Date():date);
		int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
		return week==0?7:week;
	}
	
	/**
	 * 将一个日期数据转换成整型
	 * @param date 日期 2011-01-12
	 * @return 2011011312
	 */
	public static int dateToInt(Date date){
		return (int)dateToLong(formatDate(date,YYYY_MM_DD));
	}
	
	/**
	 * 将一个日期数据转换成整型
	 * @param date 日期 2011-01-12
	 * @return 2011011312
	 */
	public static int dateToInt(String date){
		return dateToInt(parseDate(date,YYYY_MM_DD));
	}
	
	/**
	 * 将一个日期数据转换成整型
	 * @param date 日期 2011-01-12 12:33:12 233
	 * @return 20110113123312233
	 */
	public static long dateToLong(String date){
		long rs=0;
		try{
			rs=Long.parseLong(date.replaceAll("[^\\d]",""));
		}catch(Exception e){
		}
		return rs;
	}
	
	/**
	 * 将一个日期数据转换成整型
	 * @param date 日期 2011-01-12 12:33:12 233
	 * @return 20110113123312233
	 */
	public static long dateToLong(Date date){
		long rs=0;
		try{
			rs=Long.parseLong(formatDate(date,YYYYMMDDHHMMSS+"S"));
		}catch(Exception e){
		}
		return rs;
	}
	/**
	 * 得到月中最后一天
	 * @param date
	 * @return
	 */
	public static int getMontyLastDay(Date date) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date==null?calendar.getTime():date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 将指定字符日期转日期对象
	 * @param pattern 格式化格式默认为(yyyy-MM-dd)
	 * @param date 日期为空当前日期
	 * @return
	 */
	public static Date parseDate(String date,String pattern) {
		DateFormat df= new SimpleDateFormat(pattern==null?YYYY_MM_DD:pattern);
		try {
			return df.parse(date==null?formatDate(new Date(), pattern):date);
		} catch (ParseException e) {
			try {
				return df.parse(formatDate(new Date(), pattern));
			} catch (ParseException e1) {
				return new Date();
			}
		}
	}
	
	/**
	 * 得到开始和结束日期指定周的月天(MMdd)
	 * @param begin 开始日期(yyyy-MM-dd)
	 * @param end   结束日期(yyyy-MM-dd)
	 * @param weeks 周(1-7)
	 * @return 如(2011-05-21,2011-05-30,{1,2})返回:0523,0524,0530
	 */
	public static String getSpecWeekMMdd(Date begin,Date end,int [] weeks){
		if(begin==null || end==null ||
		   weeks==null || weeks.length==0){
			return null;
		}
		Calendar beginc=Calendar.getInstance();
		beginc.setTime(begin);
		
		Calendar endc=Calendar.getInstance();
		endc.setTime(end);
		
		StringBuffer mds=new StringBuffer();
		while(beginc.compareTo(endc)<=0){
			int week=getWeek(beginc.getTime());
			for (int iweek : weeks) {
				if(week==iweek){
					String md=DateUtil.formatDate(beginc.getTime(), "MMdd");
					mds.append(md).append(",");
				}
			}
			beginc.add(Calendar.DATE, 1);
		}
		if(mds.length()!=0){
			mds.delete(mds.length()-1, mds.length());
		}
		return mds.toString();
	}
	
	/**
	 * 跟据指定日期生成一个日期表
	 * 如2011-05
	 * <table border='1'>
	 * 	<tr>
	 * 		<td>星期天</td>
	 * 		<td>星期一</td>
	 * 		<td>星期二</td>
	 * 		<td>星期三</td>
	 * 		<td>星期四</td>
	 * 		<td>星期五</td>
	 * 		<td>星期六</td>
	 *  </tr>
	 *  <tr>
	 * 		<td>1<span>这里放参数</span></td>
	 * 		<td>2</td>
	 * 		<td>3<span>这里放参数</span></td>
	 * 		<td>4</td>
	 * 		<td>5</td>
	 * 		<td>6<span>这里放参数</span></td>
	 * 		<td>7</td>
	 *  </tr>
	 *  <tr>
	 * 		<td>8</td>
	 * 		<td>9</td>
	 * 		<td>10</td>
	 * 		<td>11</td>
	 * 		<td>12</td>
	 * 		<td>13</td>
	 * 		<td>14</td>
	 *  </tr>
	 *  <tr>
	 * 		<td>15</td>
	 * 		<td>16</td>
	 * 		<td>17</td>
	 * 		<td>18</td>
	 * 		<td>19</td>
	 * 		<td>20</td>
	 * 		<td>21</td>
	 *  </tr>
	 *  <tr>
	 * 		<td>22</td>
	 * 		<td>23</td>
	 * 		<td>24</td>
	 * 		<td>25</td>
	 * 		<td>26</td>
	 * 		<td>27</td>
	 * 		<td>28</td>
	 *  </tr>
	 *  <tr>
	 * 		<td>29</td>
	 * 		<td>30</td>
	 * 		<td>31</td>
	 * 		<td>&nbsp;</td>
	 * 		<td>&nbsp;</td>
	 * 		<td>&nbsp;</td>
	 * 		<td>&nbsp;</td>
	 *  </tr>
	 * </table>
	 * @param sdate 日期(yyyy-MM)
	 * @param needTable 是否需要表格
	 * @param params 要指定日中要显示的参数key:日
	 * @return 如果needTable为true，则返回表格，如果为false只返回日期行
	 */
	public static String calendarTable(String sdate, boolean needTable,Map<Integer, String> params) {
		Date date = DateUtil.parseDate(sdate,"yyyy-MM");
		int lastDay = DateUtil.getMontyLastDay(date);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		int sweek = DateUtil.getWeek(cal.getTime());

		cal.set(Calendar.DATE, lastDay);
		int eweek = DateUtil.getWeek(cal.getTime());

		String nt="\n\t";
		
		StringBuffer buff = new StringBuffer();
		if (needTable) {
			buff.append("<table border='1'>").append(nt);
			buff.append("<tr>").append(nt);
			buff.append("<td>星期天</td>").append(nt);
			buff.append("<td>星期一</td>").append(nt);
			buff.append("<td>星期二</td>").append(nt);
			buff.append("<td>星期三</td>").append(nt);
			buff.append("<td>星期四</td>").append(nt);
			buff.append("<td>星期五</td>").append(nt);
			buff.append("<td>星期六</td>").append(nt);
			buff.append("</tr>").append(nt);
		}
		
		for (int i = 1; i <= lastDay; i++) {
			//控制道行
			if (i == 1) {
				buff.append("<tr>").append(nt);
				if (sweek != 7) {
					for (int sw = 0; sw < sweek; sw++) {
						buff.append("<td>&nbsp;</td>").append(nt);
					}
				}
			}
			
			String content="";
			if(params!=null && !params.isEmpty()){
				content=params.get(i);
				content=content==null?"":"<p>"+content+"</p>";
			}
			
			//中间日期部份
			buff.append("<td>").append(i).append(content).append("</td>").append(nt);
			if ((sweek + i) % 7 == 0) {
				buff.append("</tr>").append(nt);
				buff.append("<tr>").append(nt);
			}

			//控制最后一行
			if (i == lastDay) {
				if (eweek != 6) {
					int temp = 6 - ((eweek == 7) ? 0 : eweek);
					while (temp > 0) {
						buff.append("<td>&nbsp;</td>").append(nt);
						temp--;
					}
				}
				buff.append("</tr>");
			}
		}
		if (needTable) {
			buff.append("\r</table>");
		}
		return buff.toString();
	}
	
	/**
	 * 得到日期的上一天的日期
	 * @param date 如果为空，取当时日期
	 * @return
	 */
	public static Date getPrvDayDate(Date date){
		Calendar calendar=Calendar.getInstance();
		if(date!=null){
			calendar.setTime(date);
		}
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
	/**
	 * 得到日期的上一小时的日期
	 * @param date 如果为空，取当时日期
	 * @return
	 */
	public static Date getPrvHourDate(Date date){
		Calendar calendar = Calendar.getInstance();
		if(date != null){
			calendar.setTime(date);
		}
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		return calendar.getTime();
	}
	
	/**
	 * 得到日期的下一天的日期
	 * @param date 如果为空，取当时日期
	 * @return
	 */
	public static Date getNextDayDate(Date date){
		Calendar calendar=Calendar.getInstance();
		if(date!=null){
			calendar.setTime(date);
		}
		calendar.add(Calendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}
	
	/**
	 * 将一个(yyyyMMddHHmmss)格式化
	 * @param date 日期(可选)
	 * @param pattern 返回格式，可选
	 * @return pattern指定格式，如果为空返回 yyyy-MM-dd HH:mm:ss
	 */
	public static String parseFullDate(String date,String pattern){
		if(date==null){
			return formatDate(YYYY_MM_DD_HH_MM_SS);
		}
		while(date.length()<14){
			date+="0";
		}
		return formatDate(parseDate(date,YYYYMMDDHHMMSS),pattern==null?YYYY_MM_DD_HH_MM_SS:pattern);
	}
	
	/**
	 * 将一个(yyyyMMddHHmmss)格式化
	 * @param date(可选)
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String parseFullDate(String date){
		if(date==null || "".equals(date)){
			return formatDate(YYYY_MM_DD_HH_MM_SS);
		}
		while(date.length()<14){
			date+="0";
		}
		return formatDate(parseDate(date,YYYYMMDDHHMMSS),YYYY_MM_DD_HH_MM_SS);
	}
	
	/**
     * 两个时间之间相差距离多少天
     * @param one 时间参数 1：
     * @param two 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2) throws Exception{
    	if(StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)){
    		return 0L;
    	}
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days=0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
    
    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }
	
    /**
     * 获取比较的开始日期
     * @param object
     * @return
     */
	public static String getBeginDate(Object object){
    	if(object==null || object.toString().trim().length()<=0){
    		return null;
    	}
    	Date date = null;
    	if(object instanceof java.lang.String){
    		date = DateUtil.parseDate(object.toString(), YYYY_MM_DD);
    	}else if(object instanceof java.util.Date){
    		date = (Date) object;
    	}
    	return DateUtil.formatDate(date, DateUtil.YYYY_MM_DD) + " 00:00:00";
    }
    
    /**
     * 获取比较的结束日期
     * @param object
     * @return
     */
   	public static String getEndDate(Object object){
       	if(object==null || object.toString().trim().length()<=0){
       		return null;
       	}
       	Date date = null;
       	if(object instanceof java.lang.String){
       		date = DateUtil.parseDate(object.toString(), YYYY_MM_DD);
       	}else if(object instanceof java.util.Date){
       		date = (Date) object;
       	}
   		return DateUtil.formatDate(date, DateUtil.YYYY_MM_DD)+" 23:59:59";
    }
    
    /**
     * 比较两个日期的大小
     * @param beginDate	开始日期
     * @param endDate	结束日期
     * @return
     */
    public static Integer compareToDate(String beginDate, String endDate){
    	if(StringUtils.isEmpty(beginDate)
    			|| StringUtils.isEmpty(endDate)){
    		return 0;
    	}
    	return DateUtil.compareToDate(DateUtil.parseDate(beginDate, YYYY_MM_DD), DateUtil.parseDate(endDate, YYYY_MM_DD));
    }
    
    /**
     * 比较两个日期的大小
     * @param beginDate	开始日期
     * @param endDate	结束日期
     * @return
     */
    public static Integer compareToDate(Date beginDate, Date endDate){
    	if(beginDate==null || endDate==null){
    		return 0;
    	}
    	return beginDate.compareTo(endDate);
    }
    
}
