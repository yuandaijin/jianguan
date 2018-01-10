package com.huatuo.clinics.cms.comm;

public class MessageUtil {

	public static final String CODE_SUCCESS = "000000";
	public static final String CODE_ERROR = "100001";
	public static final String CODE_FAILURE = "100002";
	public static final String CODE_LOST_TOKEN = "100003";

	public static final String SUCCESS = "操作成功";
	public static final String ERROR = "系统繁忙";
	public static final String FAILURE = "操作失败";
	public static final String EXIST = "数据已存在!";
	public static final String SUCCESS_REG = "注册成功";
	public static final String SUCCESS_LOG = "登陆成功";
	public static final String ERROR_LOG = "账号或密码错误";
	public static final String ERROR_TYPE_LOG = "用户类型错误";
	public static final String ERROR_TIME = "时间格式错误";
	public static final String SUCCESS_DEL = "删除成功";
	public static final String USERNAME_EXISTS = "该用户名已存在";
	public static final String USERNAME_NOT_EXISTS = "该用户名不存在";
	public static final String TOKEN_EMPTY = "token为空";
	public static final String TOKEN_INVALIDATE = "token已失效";
	public static final String SUCCESS_UPDATEPASSWORD = "修改密码成功";
	public static final String LOGIN_NOT_FOUND = "账号或密码错误";
	public static final String LOST_TOKEN = "未登录";
	public static final String OLDPASSWORDERROR = "旧密码输入错误";
	public static final String EMAIL_OR_MOBILE_REQUIRED = "手机号或邮箱至少提供一个";
	public static final String MOBILE_ERROR = "手机号错误";
	public static final String MOBILE_ERROR_EXISTS = "该手机号码已被注册";
	public static final String MOBILE_CODE_ERROR = "短信验证码错误";
	public static final String MOBILE_CODE_EMPTY = "请输入您收到的短信验证码！";
	public static final String EMAIL_CODE_ERROR = "邮箱验证码错误";
	public static final String IMG_CODE_ERROR = "图形验证码错误";
	public static final String IMG_CODE_NOT_EXSIT = "图形验证码为空";
	public static final String BIND_EMAIL_SUCCESS = "邮箱绑定成功";
	public static final String EMAIL_EXISTS = "邮箱已存在";
	//public static final String MOBILE_EXISTS = "手机已存在";
	public static final String EMAIL_ERROR = "原邮箱错误";
	public static final String IMG_MOBILE_CODE_ERROR = "手机短信发送太频繁了";
	public static final String CODE_USE = "验证码已使用";
	public static final String FILEPATH_EXIST = "该文件目录已经存在";
	public static final String FILE_TYPE_NONSUPPORT = "该文件类型不支持";
	public static final String FILE_TYPE_NONSUPPORT_FILE = "指定类型无法支持当前传入的文件";
	
	public static final String ERROR_FAMILY_SIZE = "家庭成员最多20人";
	public static final String ERROR_ALREDAY_PRAISE = "已经点赞了";
	
	public static final String USERNAME_EXISTSANDDELETETRUE = "该用药品厂商已存在,当前处于‘作废’状态";
	public static final String USERNAME_EXISTSANDDELETEFALSE = "该用药品厂商已存在";
	
	public static final String FILE_SIZE_IS_ZERO = "文件大小为0";
	
	public static final String SOCIIALID_EXISTS = "身份证号码已存在";
	
	public static final String SUBSCRIBE = "预约人数已满";
	
	public static final String CANCELORDER = "订单已取消";
	
	/**
	 * 预约就诊时将诊号是否存入reidis后的返回信息
	 */
	public static final String MZ_CODE_IS_EXIST = "预约诊号已存在";
	
	public static final String MZ_CODE_IS_NOT_EXIST = "预约诊号保存成功";
	
	public static final String RESERVATION_PAY_SUCCESS_RESULT = "预约成功，请保持电话畅通";
	
	public static final String IS_CLOSE_RESERVATION = "对不起，该医生已经关闭了在线预约就诊";
	
	/**
	 * 远程协作申请成功
	 */
	public static final String COLLABORATIVE_APPLICATION = "您的申请已成功提交，请耐心等候申请反馈！";
	
	/**
	 * 远程协作任务提交失败
	 */
	public static final String COMMIT_FAILED = "任务提交失败!";
	
	/**
	 * 给医生发送预约就诊短信
	 */
	public static final String SEND_DOCTOR_BESPEAK = "医生您好，您有一个患者预约即将开始，请做好接诊准备。";
	
	/**
	 * 给用户发送预约就诊短信
	 */
	public static final String SEND_USER_BESPEAK = "先生您好，您有一个在线门诊预约即将开始，请及时做好就诊准备。";
	
	/**
	 * 短信【】格式签名
	 */
	public static final String MOBILE_HEAD = "【华佗快线】";
}
