package com.lexiang.utils.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 星期工具类
 * @author miaowu
 *
 */
public class WeekUtils {
	public static final int week = 7;
	/**
	 * 返回相差天数
	 * @param thisDate 当前时间
	 * @param week_index 下一个周几 1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六 
	 * @return 相差天数
	 */
	public static int getDays(Date thisDate,int week_index){
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(thisDate);
		int thisWeek =cal.get(Calendar.DAY_OF_WEEK);
		if(thisWeek==week_index){
			return week;
		}else if(week_index < thisWeek){
			return week-thisWeek+week_index;
		}else{
			return week_index-thisWeek;
		}
	}
	/**
	 * 返回相差天数
	 * @param thisDate 当前时间
	 * @param week_index 下一个周几 1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
	 * @return 相差天数
	 */
	public static int getDays(Date thisDate,String week_index){
		if(week_index == null || week_index.trim().length() == 0 ){
			throw new  RuntimeException(week_index+":不合法");
		}
		return getDays(thisDate, Integer.valueOf(week_index));
	}


	/**
	 * 获取当前是周几
	 * @param thisDate
	 * @return
	 */
	public static int getWeek(Date thisDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisDate);
		int thisWeek =cal.get(Calendar.DAY_OF_WEEK);
		return thisWeek;
	}
	
}
