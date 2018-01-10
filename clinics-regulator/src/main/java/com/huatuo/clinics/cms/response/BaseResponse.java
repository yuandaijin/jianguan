package com.huatuo.clinics.cms.response;

import com.huatuo.common.MessageUtil;

public class BaseResponse {

	/**
	 * 消息状态码
	 */
	private String code = MessageUtil.CODE_SUCCESS;
	
	/**
	 * 返回消息
	 */
	private String message = MessageUtil.SUCCESS;
	
	private String resul;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResul() {
		return resul;
	}

	public void setResul(String resul) {
		this.resul = resul;
	}
	
	/**
	 * 操作成功
	 * @return
	 */
	public static BaseResponse reSuccess(){
		BaseResponse resp = new BaseResponse();
		resp.setCode(MessageUtil.CODE_SUCCESS);
		resp.setMessage(MessageUtil.SUCCESS);
		return resp;
	}
	
	/**
	 * 操作失败
	 * @return
	 */
	public static BaseResponse refailure(){
		BaseResponse resp = new BaseResponse();
		resp.setCode(MessageUtil.CODE_FAILURE);
		resp.setMessage(MessageUtil.FAILURE);
		return resp;
	}
	
	/**
	 * 未登录
	 * @return
	 */
	public static BaseResponse lostToken(){
		BaseResponse resp = new BaseResponse();
		resp.setCode(MessageUtil.CODE_LOST_TOKEN);
		resp.setMessage(MessageUtil.LOST_TOKEN);
		return resp;
	}
	
	/**
	 * 改用户名已存在
	 * @return
	 */
	public static BaseResponse reRepeat(){
		BaseResponse resp = new BaseResponse();
		resp.setCode(MessageUtil.CODE_FAILURE);
		resp.setMessage(MessageUtil.USERNAME_EXISTS);
		return resp;
	}
	
}
