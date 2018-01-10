package com.huatuo.clinics.cms.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.huatuo.common.MD5Util;
import com.huatuo.exception.UtilsException;


public class Utils {
	
	
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	private static Map s_chineseFont;
    private final static int WB = 1;

    private final static int KEY_BEGIN = 0;
    private final static int KEY_END = 1;

    private final static int VALUE_BEGIN = 1;
    private final static int VALUE_END = 3;
	

	/**
	 * 添加token
	 * 
	 * @param bean
	 * @return
	 */
	public static String generateTonken(long userId, String userName) {
		return MD5Util.MD5(userId + userName + Utils.getNowTime()) + userId;
	}

	// public static void updateUserInfo(HttpServletRequest request,
	// BaseUserInfo userInfo) {
	// BaseUserInfo origUserInfo = getUserInfo(request);
	// if (origUserInfo == null) {
	// return;
	// }
	// if (origUserInfo.userId == userInfo.userId) {
	// SecurityContext securityContext = (SecurityContext)
	// request.getSession().getAttribute(
	// HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	// if (securityContext == null) {
	// return;
	// }
	// userInfo.setToken(origUserInfo.getToken());
	// UsernamePasswordAuthenticationToken authentication = new
	// UsernamePasswordAuthenticationToken(
	// userInfo.getUserName(), userInfo);
	// securityContext.setAuthentication(authentication);
	// }
	// }

	// /**
	// * 获取用户信息
	// *
	// * @param request
	// * @return
	// */
	// public static BaseUserInfo getUserInfo(HttpServletRequest request) {
	// return getUserInfo(request.getSession());
	// }
	//
	// /**
	// * 获取用户信息
	// *
	// * @param request
	// * @return
	// */
	// public static BaseUserInfo getUserInfo(HttpSession session) {
	// SecurityContext securityContext = (SecurityContext) session
	// .getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
	// if (securityContext == null) {
	// return null;
	// }
	// Authentication authentication = securityContext.getAuthentication();
	// if (authentication == null) {
	// return null;
	// }
	// Object credentials = authentication.getCredentials();
	// if (credentials != null && credentials instanceof BaseUserInfo) {
	// return (BaseUserInfo) credentials;
	// }
	// Object principal = authentication.getPrincipal();
	// if (principal == null || !(principal instanceof HuatuoUserDetails)) {
	// return null;
	// }
	// return ((HuatuoUserDetails) principal).getUserInfo();
	// }

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 获取当前时间yyyyMMddHHmmss串
	 * 
	 * @return
	 */
	public static String getNowTimeStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 获取当日开始和结束时间
	 * 
	 * @param startOrEnd
	 *            true:开始时间 false:结束时间
	 * @return
	 */
	public static String getNowDayBeginOrEnd(boolean startOrEnd) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");// 设置日期格式
		String timeEnds = startOrEnd ? "00:00:00" : "23:59:59";
		return df.format(new Date()) + timeEnds;
	}

	/**
	 * 获取今天或者昨天的年月日
	 * 
	 * @param TodayOrYesterday
	 *            true:今天 false:昨天
	 * @return
	 */
	public static String getTodayOrYestorday(boolean TodayOrYesterday) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		Calendar calendar = Calendar.getInstance();
		if (TodayOrYesterday) {
			return df.format(calendar.getTime());
		} else {
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			return df.format(calendar.getTime());
		}
	}

	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getBirthDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());
	}
	
	/**
	 * 获取前n个月的时间
	 * @param months	月份差(负数:往后计算时间,正数:往过去计算时间)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getBeforeDate(int months) {
		Date dNow = new Date(); //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MONTH, - months); //设置为前3月
		dBefore = calendar.getTime(); //得到前3月的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		return sdf.format(dBefore);
	}
	
	/**
	 * 获取前n个小时的时间
	 * @param hours	小时差(负数:往后计算时间,正数:往过去计算时间)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getBeforeHour(int hours) {
		Date dNow = new Date(); //当前时间
		Date hBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.HOUR_OF_DAY, - hours); //设置为前n个小时
		hBefore = calendar.getTime(); //得到前3月的时间

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
		return sdf.format(hBefore);
	}
	
	/**
	 * 计算指定日期前后的时间hours
	 * @param date	指定日期
	 * @param hours	小时差(正数:往后计算时间,负数:往过去计算时间)
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("static-access")
	public static String getDateBeforeHour(String date, int hours) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
		Date dNow = sdf.parse(date); //将String格式转换成date格式
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.HOUR_OF_DAY, hours); //设置为n个小时以后

		return sdf.format(calendar.getTime());//得到前计算后的时间的时间
	}
	
	/**
	 * 返回minute分钟以后的时间
	 * @param minute	分钟差(正数:往后计算时间,负数:往过去计算时间)
	 * @return
	 */
	public static String getBeforeMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}
	
	/**
	 * 计算指定日期前后的时间minute
	 * @param date		指定日期
	 * @param minute	分钟差(正数:往后计算时间,负数:往过去计算时间)
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("static-access")
	public static String getDateBeforeMinute(String date, int minute) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
		Date dNow = sdf.parse(date); //将String格式转换成date格式
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(calendar.MINUTE, minute); //设置为n分钟以后

		return sdf.format(calendar.getTime());//得到前计算后的时间的时间
	}
	
	/**
	 * 返回某天是星期几
	 * @param date		日期
	 * @return
	 * @throws ParseException
	 */
	public static String getWeek(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = format.parse(date);	//String类型转换为打他类型
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE"); //转换为week格式
		return sdf.format(parse);	//返回星期几
	}
	
	

	public static long getTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取请求的真实IP，主要应用在有代理，或是负载均衡的场景下
	 * 该类适用于，内部系统间的调用。如果对外的话，用户可能伪造x-forwarded-for请求头，以达到伪造IP的目的。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		try {
			// 第一步首先获取代理类传过来的用户真实IP,如果有，直接返回
			// 接下来，获取Squid透传过来的ip
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Real-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			if (!(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))) {
				int dotIdx = ip.indexOf(",");
				if (dotIdx == -1) {
					// squid
					// 透传过来的IP以一个空格分隔，第二个固定为客户的真实IP
					String[] ipToken = ip.split(" ");
					if (ipToken.length > 1) {
						return ipToken[1];
					}
				} else {
					// squid,nginx
					// 透传过来的IP以一个,分隔，第一个固定为客户的真实IP
					String[] ipToken = ip.split(",");
					if (ipToken.length > 1) {
						return ipToken[0];
					}
				}
				return ip;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取浏览器版本
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowser(HttpServletRequest request) {
		return request.getHeader("user-agent");
	}

	/**
	 * 年龄转换时间
	 * 
	 * @param age
	 * @param ageType
	 * @return
	 */
	public static String getBirthDate(int age, int ageType) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		if (ageType == 1) {
			date.set(Calendar.DATE, date.get(Calendar.DATE) - age);
		} else if (ageType == 2) {
			date.set(Calendar.MONTH, date.get(Calendar.MONTH) - age);
		} else if (ageType == 3) {
			date.set(Calendar.YEAR, date.get(Calendar.YEAR) - age);
		}
		return dft.format(date.getTime());
	}

	/**
	 * 生日转换年龄(年)
	 * 
	 * @param birthTime
	 * @return
	 * @throws ParseException
	 */
	public static int getAge(String birthTime) throws ParseException {
		if (StringUtils.isEmpty(birthTime)) {
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthTime)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(sdf.parse(birthTime));
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}

		return age;
	}

	/**
	 * 取得经过本地化的大致年龄
	 * 
	 * @param birthdate
	 * @return 大致的年龄（天/月/年）
	 */
	public static String getLocalizedAge(String birthdate) {
		if (birthdate == null || birthdate.length() != 10) {
			return "0岁";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long birth = sdf.parse(birthdate).getTime() / (24 * 60 * 60 * 1000);
			long now = System.currentTimeMillis() / (24 * 60 * 60 * 1000);
			long days = now - birth;
			if (days < 0) {
				return "";
			}
			if (days < 200) {
				return days + "天";
			}
			if (days < 365 * 3) {
				return (days / 30) + "月";
			}
			return (days / 365) + "岁";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static long getTimeNum(String time1, String time2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = sdf.parse(time1);
			Date date2 = sdf.parse(time2);
			long time = date1.getTime() - date2.getTime();
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getFileExt(String fileName) {
		if (fileName == null || fileName.length() == 0) {
			return "";
		}
		int pos = fileName.lastIndexOf('.');
		if (pos < 0) {
			return "";
		}
		return fileName.substring(pos + 1).toLowerCase();
	}

	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	public static String getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}

		return age + "";
	}

	/**
	 * 禁止浏览器缓存本次应答的数据
	 * 
	 * @param response
	 */
	public static void disableBrowserCache(HttpServletResponse response) {
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Cache", "no-cache");
		response.setHeader("Pragma", "no-cache");
	}

	public static boolean isEmail(String email) {
		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(pattern1);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isMobile(String mobile) {
		// Pattern pattern = Pattern.compile("/(^1\\d{10})$/");
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	public static String getmembername() {
		String name = "";
		try {
			for (int i = 0; i < 5; i++) {
				int intValue = (int) (Math.random() * 26 + 97);
				name = name + (char) intValue;
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 利用spring工具类将两个bean对象对应转换,注意调用强制转换
	 * 
	 * @param <BOUser>
	 * @param source
	 *            源javabean对象
	 * @param target
	 *            目标javabean对象
	 * @return
	 */
	public static <T> T exchangeObject(Object source, Class<T> target) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		T t = null;
		try {
			t = target.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		BeanUtils.copyProperties(source, t);
		return t;
	}

	public static <T> T copyProperties(Object source, T target) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		BeanUtils.copyProperties(source, target);
		return target;
	}

	/**
	 * 获取汉字串拼音首字母，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音首字母
	 */
	public static String getFirstSpell(String chinese) {
		if (chinese == null || chinese.trim().length() == 0) {
			return "";
		}
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
					if (temp != null) {
						pybf.append(temp[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().trim().toUpperCase();
	}

	/**
	 * 把一个list<String> 转化为以","分割的字符串
	 * 
	 * @param stringList
	 * @return
	 */
	public static String listToString(List<String> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	/**
	 * 把多个','分割的数字串转为List<Long>
	 * 
	 * @param menuId
	 * @return
	 */
	public static List<Long> getList(String menuId) {
		if (menuId == null || menuId.length() == 0) {
			return null;
		}
		String[] array = menuId.split(",");
		List<Long> list = new ArrayList<Long>();
		for (String s : array) {
			list.add(Long.parseLong(s));
		}
		return list;
	}

	/**
	 * 集合去重
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> repeatList(List<T> list) {
		HashSet<T> h = new HashSet<T>(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	// public static <T> T exchangeObject(Object source,Class<T> target){
	// if(StringUtils.isEmpty(source)){
	// return null;
	// }
	// T t = null;
	// try {
	// t = target.newInstance();
	// } catch (InstantiationException | IllegalAccessException e) {
	// e.printStackTrace();
	// }
	// BeanUtils.copyProperties(source, t);
	// return t;
	// }

	public static <T> String getWhereList(List<T> list) {
		StringBuilder src = new StringBuilder();
		for (T obj : list) {
			src.append("'").append(obj.toString()).append("',");
		}
		return src.substring(0, src.length() - 1).toString();
	}

	/**
	 * 获取中文的年月日
	 * 
	 * @return
	 */
	public static String getFullDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 生成订单号
	 * 
	 * @param serviceType
	 *            根据服务类型生产订单号
	 * @return
	 */
	public static String getOrderNo(Integer serviceType) {
		String orderNo;
		long now = System.currentTimeMillis();
		String r;
		while (true) {
			r = (int) (Math.random() * (1000)) + "";
			if(r.length() == 3) break;
		}

		String t = now + "";
		t = t.substring(t.length() - 8, t.length());
		orderNo = serviceType.toString() + t + r;
		if (orderNo.length() != 12) {
			orderNo = getOrderNo(serviceType);
		}
		return orderNo;
	}

	/**
	 * 判断一个字符串是否为null或者空字符串
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() < 1;
	}
	
	/**
	 * 返回中国式某一天在一星期中是第几天
	 * @return
	 */
	public static int getDayOfWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	/**
	 * 根据字符串时间格式,
	 * 以及格式规则返回日期
	 */
	public static Date getDateByStrPattern(String source, String pattern){
		try {
			return new SimpleDateFormat(pattern).parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *	比较两个时间字符串的大小
	 *	默认看第一个字符串表示的时间,是否大于第二个字符串表示的时间
	 */
	public static Boolean compareStrDate(String dateOne, String dateTwo){
		if(dateTwo==null){
			return true;
		}
		return Long.parseLong(dateOne.replaceAll(":","")) > Long.parseLong(dateTwo.replaceAll(":",""));
	}
	
	/**
	 *	比较两个日期字符串大小，可传入指定要替换的字符串
	 *	默认看第一个字符串表示的年月日,是否大于第二个字符串表示的年月日
	 */
	public static Boolean compareStrDateAndReplaceStr(String dateOne, String dateTwo, String replaceStr){
		if(dateTwo==null){
			return true;
		}
		return Long.parseLong(dateOne.replaceAll(replaceStr,"")) > Long.parseLong(dateTwo.replaceAll(replaceStr,""));
	}
	
	/**
	 * 比较yyyy-MM-dd HH:mm:ss格式下的字符串时间大小
	 * 
	 * @param dateOne
	 * @param dateTwo
	 * @return
	 */
	public static Boolean compareStrDayTime(String dateOne, String dateTwo){
		if(dateTwo==null){
			return true;
		}
		if(dateOne.replaceAll(":","").replaceAll("-", "").replaceAll(" ", "").trim().length() != 14){
			throw new UtilsException("传入的第一个时间格式有误");
		}
		if(dateTwo.replaceAll(":","").replaceAll("-", "").replaceAll(" ", "").trim().length() != 14){
			throw new UtilsException("传入的第二个时间格式有误");
		}
		return Long.parseLong(dateOne.replaceAll(":","").replaceAll("-", "").replaceAll(" ", "").trim()) >
			Long.parseLong(dateTwo.replaceAll(":","").replaceAll("-", "").replaceAll(" ", "").trim());
	}
	
	/**
	 * 获得汉字五笔码的方法
	 * @param input
	 * @return
	 */
	public static String getWbCode (String input)
    {
        return getCode (input, WB);
    }
	/**
     * 
     * @param input 输入字符 .
     * @param flag 0拼音,1五笔 .
     * @return String
     */
    private static String getCode (String input, int flag)
    {
        if (input == null)
        {
            return "";
        }
        //剔除非中文字符后，如果为空，则返回空字符串
        input = extractionChinese(input);
        if(input.isEmpty ())
        {
            return "";
        }
        
        StringBuffer wb = new StringBuffer ();
        try
        {
            //int chineseFontLength = s_chineseFont != null ? s_chineseFont.size () : 0;
            if (s_chineseFont == null || s_chineseFont.isEmpty ())
            {
            	s_chineseFont = readChineseFont ();
            }
            if (s_chineseFont == null || s_chineseFont.isEmpty ())
            {
                logger
                      .info ("readChineseFont is failure" + ",please check Utils.jar classpath  chinese_font.txt!!!");
                throw new Exception ("readChineseFont is failure"
                                     + ",please check Utils.jar classpath  chinese_font.txt!!!");
            }

            char[] words = input.toCharArray ();
            for (int i = 0; i < words.length; i++)
            {
                String key=String.valueOf (words[i]);
                Object value = s_chineseFont.get (key);
                String pywbCode = value == null ? "" : String.valueOf (value);
                try
                {
                    wb.append ((pywbCode != null && !"".equals (pywbCode)) ? pywbCode : key);
                }
                catch (Exception e)
                {
                    // TODO: handle exception
                    logger.info ("call Utils.getWbCode(String input) is error:" + e.getMessage () + "PywbCode is:"
                                 + pywbCode);
                    e.printStackTrace ();
                }

            }
        }
        catch (Exception e)
        {
            logger.info ("call Utils.getWbCode(String input) is error:" + e.getMessage ());
            e.printStackTrace ();
        }
        return wb.toString ();
    }
    private static String extractionChinese(String input)
    {
        String result = "";
        String regex = "[\u4e00-\u9fa5a-zA-Z0-9]";
        char[] array = input.trim ().toCharArray ();
        for(char single : array)
        {
            if((single+"").matches (regex))
            {
                result += single; 
            }
        }
        return result;
    }
    /**
     * 读font.txt中文字库.
     * 
     * @return
     */
    private static Map readChineseFont ()
    {
        logger.info ("call Utils.readChineseFont () begin... ...");
        BufferedReader br = null;
        Map <String, String> o = new HashMap <String, String> ();
        try
        {
            br = new BufferedReader (new InputStreamReader (Utils.class.getResourceAsStream ("chinese_font.txt"),Charset.forName("utf-8")));
            String temp;
            while ((temp = br.readLine ()) != null)
            {
                temp = temp.trim ();
                o.put (temp.substring (KEY_BEGIN, KEY_END), temp.substring (VALUE_BEGIN, VALUE_END));
            }
        }
        catch (Exception e)
        {
            logger.info ("call Utils.readChineseFont () is error:" + e.getMessage ());
            e.printStackTrace ();
        }
        finally
        {
            try
            {
                br.close ();
            }
            catch (IOException e)
            {
                logger.info ("call Utils.readChineseFont () is error:" + e.getMessage ());
                e.printStackTrace ();
            }
        }
        logger.info ("call Utils.readChineseFont () end... ...");
        return o;
    }
    
    /**
     * 某一天的第二天
     * @param today
     * @return
     */
    public static String addOneDay(String theDay){   
        SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try   {   
            Date  d  =  new Date(f.parse(theDay).getTime()+24*3600*1000);     
              return  f.format(d);   
        }   
        catch(Exception ex) {   
            return   "输入格式错误";     
        }   
    }
    
    /**传入的日期加上任意天数
     * @return
     */
    public static String addAnyDay(String theDay, String format,Integer days){   
        SimpleDateFormat f =  new SimpleDateFormat(format);   
        try   {   
            Date  d  =  new Date(f.parse(theDay).getTime()+24*3600*1000 * days);     
              return  f.format(d);
        }   
        catch(Exception ex) {   
            return   "输入格式错误";     
        }   
    }
    
    /**
	 * 返回某个年月日是一个星期的周几
	 * @param date		日期
	 * @return
	 * @throws ParseException
	 */
	public static Integer getIntOfWeek(String date) throws ParseException {
		Calendar cal = Calendar.getInstance();  
		cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));  
		return cal.get(Calendar.DAY_OF_WEEK) - 1;	//返回星期几
	}
	
	/**
	 * 验证的错误信息获取
	 * @param allErrors	验证信息
	 * @return
	 */
	public static String beansValidAjax(BindingResult result) {
		List<ObjectError> allErrors = result.getAllErrors();
		String str = null;
		if(allErrors != null){
			for (ObjectError objectError : allErrors) {
				if(str != null){
					str += "; " + objectError.getDefaultMessage(); 
				}else{
					str = objectError.getDefaultMessage();
				}
			}
		}
    	return str;	
    	
    	//调用此方法后copy下边代码执行
//    	String valid = Utils.beansValid(result);
//    	resp.setCode(MessageUtil.CODE_ERROR);
//    	//将错误信息装入message
//    	resp.setMessage(valid);
//      return resp;  
	}
	
	public static Map<String, Object>  beansValidHtml(BindingResult result) {
		List<ObjectError> allErrors = result.getAllErrors();
		Map<String, Object> map = new HashMap<>();
		if(allErrors != null){
			for (ObjectError objectError : allErrors) {
				//设置想要获取的字段"field"
				try {
					Field field = objectError.getClass().getDeclaredField("field");
					// 设置访问权限
					field.setAccessible(true);
					try {
						String str = field.get(objectError).toString();
						String message = objectError.getDefaultMessage();
						map.put(str, message);
						
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				
			}
		}
		return map;	
	}
    
	/**
	 * 获取日期之差
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @return
	 */
	public static String getBetweenTime(String startTime, String endTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			Date d1 = df.parse(endTime);
			Date d2 = df.parse(startTime);
			long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
			return "" + days + "天" + hours + "小时" + minutes + "分";
		} catch (Exception e) {
		}
		return "";
	}
	
	
	/**
	 * 获取所有的地区id
	 * @param district
	 * @return
	 */
	public static String[] getParentsDistrict(String district){
		String[] districtList = {};
		if(district.length()>4){
			String str = district.substring(2, district.lastIndexOf(","));
			districtList = str.split(",");
		}
		return districtList;
	}
	
	/**
	 * 清分总表单据号生成
	 * @return
	 */
	public static String getPayClearNo(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
		return df.format(new Date());
	}
}