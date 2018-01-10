package com.huatuo.clinics.cms.comm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



/**
 * Date工具类
 *@author ydj
 *
 */
public class DateUtil {
	
	/**
	 * 获取当前时间的前一天
	 * @return List
	 */
	public static List<String> getNextDay() {
		Date date = new Date();
		List<String> dateList = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);// 获取前一天的日期
		date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化日期，(yyyy-MM-dd)
		String str = sdf.format(date);
		String year = str.substring(0, 4);// 取出年
		String month = str.substring(5, 7);// 取出月
		String day = str.substring(8, 10);// 取出天
		dateList.add(year);
		dateList.add(month);
		dateList.add(day);
		return dateList;
	}
	
	
	/**
	 * 获取当前时间的上一个月
	 * @return List
	 */
	public static List<String> getLastMonth() {
		Date date = new Date();
		List<String> dateList = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);// 获取前一天的日期
		date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化日期，(yyyy-MM-dd)
		String str = sdf.format(date);
		String year = str.substring(0, 4);// 取出年
		String month = str.substring(5, 7);// 取出月
		String day = str.substring(8, 10);// 取出天
		dateList.add(year);
		dateList.add(month);
		dateList.add(day);
		return dateList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//测试
	public static void main(String[] args) {
		Date date= new Date();
		List<String>  sss=getNextDay();
		System.out.println(sss.get(0)+sss.get(1)+sss.get(2));
	}
	
}
