package com.huatuo.clinics.cms.comm;

import com.huatuo.common.PropertyUtils;

public class ConfigProperites {

	private static final String webUrl = "web_url";
	
	private static final String imageUrl = "imageUrl";
	
	private static final String messageUrl = "messageUrl";
	
	private static final String TIMER_URL = "timerUrl";
	
	private static final String ftpUrl = "ftpUrl";
	
	private static final String port = "port";
	
	private static final String userName = "userName";
	
	private static final String password = "password";
	
	private static final String realm_name = "realm_name";
	
	private static final String MANAGER_URL = "manager_url";
	
	private static final String SWF = "swf";
	
	private static final String desKey = "des.key";
	
	private static final String districtMap = "dictionary.districtMap";
	
	private static final String familyRelationMap = "dictionary.familyRelationMap";
	
	private static final String icdMap = "dictionary.icdMap";
	
	private static final String xtZdOrgMap = "dictionary.xtZdOrgMap";
	
	private static final String ypZdKindMap = "dictionary.ypZdKindMap";
	
	private static final String ypZdSupplyMap = "dictionary.ypZdSupplyMap";
	
	private static final String xtSpecialtyMap = "dictionary.xtSpecialtyMap";
	
	private static final String xtealthExamMap = "dictionary.xtealthExamMap";
	
	private static final String taskExeMap = "dictionary.taskExeMap";
	
	private static final String cleanTime = "clean_time";
	
	private static final String REDIS_MZ_ORDER_TIMER = "redis.mz_order_timer";
	
	private static final String REDIS_MZ_ORDER_MINUTE = "redis.mz_order_minute";
	
	private static final String REDIS_PAY_ORDER_TIMER = "redis.pay_order_timer";
	
	private static final String REDIS_PAY_ORDER_MINUTE = "redis.pay_order_minute";
	
	private static final String REDIS_PAY_ORDER_TIMER_HOUR = "redis.pay_order_timer_hour";
	
	private static final String REDIS_PAY_ORDER_HOUR = "redis.pay_order_hour";
	
	private static final String REDIS_NOTE_TIMER = "redis.note_timer";
	
	private static final String REDIS_NOTE_MINUTE = "redis.note_minute";
	
	private static final String companyMap = "dictionary.companyMap";
	
	private static final String unitMap = "dictionary.unitMap";
	
	private static final String occupationMap = "dictionary.occupationMap";
	
	private static final String registerUrl = "registerCenter.url";
	
	
	
	public static String getRegisterurl() {
		return PropertyUtils.get(registerUrl);
	}


	public static String getWebUrl(){
		return PropertyUtils.get(webUrl);
	}
	
	
	public static String getImageUrl(){
		return PropertyUtils.get(imageUrl);
	}

	public static String getFtpurl() {
		return PropertyUtils.get(ftpUrl);
	}

	public static String getPort() {
		return PropertyUtils.get(port);
	}

	public static String getUsername() {
		return PropertyUtils.get(userName);
	}

	public static String getPassword() {
		return PropertyUtils.get(password);
	}
	
	public static String getMessageUrl() {
		return PropertyUtils.get(messageUrl);
	}
	
	public static String getTimerUrl() {
		return PropertyUtils.get(TIMER_URL);
	}
	
	
	public static String getRealmName() {
		return PropertyUtils.get(realm_name);
	}
	
	public static String getSWF() {
		return PropertyUtils.get(SWF);
	}
	
	public static String getDesKey() {
		return PropertyUtils.get(desKey);
	}

	public static String getDistrictmap() {
		return PropertyUtils.get(districtMap);
	}

	public static String getFamilyrelationmap() {
		return PropertyUtils.get(familyRelationMap);
	}

	public static String getIcdmap() {
		return PropertyUtils.get(icdMap);
	}

	public static String getXtzdorgmap() {
		return PropertyUtils.get(xtZdOrgMap);
	}

	public static String getYpzdkindmap() {
		return PropertyUtils.get(ypZdKindMap);
	}

	public static String getYpzdsupplymap() {
		return PropertyUtils.get(ypZdSupplyMap);
	}

	public static String getXtspecialtymap() {
		return PropertyUtils.get(xtSpecialtyMap);
	}

	public static String getXtealthexammap() {
		return PropertyUtils.get(xtealthExamMap);
	}

	public static String getTaskexemap() {
		return PropertyUtils.get(taskExeMap);
	}
	
	public static String getManagerUrl() {
		return PropertyUtils.get(MANAGER_URL);
	}
	
	public static String getCleanTime(){
		return PropertyUtils.get(cleanTime);
	}

	public static String getRedisMzOrderTimer() {
		return PropertyUtils.get(REDIS_MZ_ORDER_TIMER);
	}

	public static String getRedisMzOrderMinute() {
		return PropertyUtils.get(REDIS_MZ_ORDER_MINUTE);
	}

	public static String getRedisPayOrderTimer() {
		return PropertyUtils.get(REDIS_PAY_ORDER_TIMER);
	}

	public static String getRedisPayOrderMinute() {
		return PropertyUtils.get(REDIS_PAY_ORDER_MINUTE);
	}

	public static String getRedisPayOrderTimerHour() {
		return PropertyUtils.get(REDIS_PAY_ORDER_TIMER_HOUR);
	}

	public static String getRedisPayOrderHour() {
		return PropertyUtils.get(REDIS_PAY_ORDER_HOUR);
	}

	public static String getCompanymap() {
		return PropertyUtils.get(companyMap);
	}

	public static String getUnitmap() {
		return PropertyUtils.get(unitMap);
	}

	public static String getOccupationmap() {
		return PropertyUtils.get(occupationMap);
	}

	public static String getRedisNoteTimer() {
		return PropertyUtils.get(REDIS_NOTE_TIMER);
	}

	public static String getRedisNoteMinute() {
		return PropertyUtils.get(REDIS_NOTE_MINUTE);
	}
	
}
