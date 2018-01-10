package com.huatuo.clinics.cms.util;

public class Constant {
	/** 
	 * 访问类型：个体诊所
	 */
	public static  final  String VISIT_TYPE_CLINICAL = "00032-0010"; 
	/**
	 * 员工状态：申请加入中
	 */
	public static  final  String EMPLOYEE_TYPE_APPLY = "01001-0001"; 
	/**
	 * 员工状态：正常
	 */
	public static  final  String EMPLOYEE_TYPE_SANITY= "01001-0002";
	/**
	 * 员工状态：已离职
	 */
	public static  final  String EMPLOYEE_TYPE_QUIT= "01001-0003";
	/**
	 * 员工状态：已拒绝
	 */
	public static  final  String EMPLOYEE_TYPE_REFUSE= "01001-0004";
	
	/**
	 * 业务类型：采购入库
	 */
	public static final String BUSI_TYPE_INSTOCK_ORDER="00030-0001";
	/**
	 * 业务类型：退货入库
	 */
	public static final String BUSI_TYPE_INSTOCK_RETURN="00030-0002";
	/**
	 * 业务类型：盘盈入库
	 */
	public static final String BUSI_TYPE_INSTOCK_PROFIT="00030-0003";
	/**
	 * 业务类型：一般出库
	 */
	public static final String BUSI_TYPE_OUTSTOCK_ORDER="00031-0001";
	
	/**
	 * 业务类型： 报损出库
	 */
	public static final String BUSI_TYPE_OUTSTOCK_LOSS="00031-0002";
	
	/**
	 * 业务类型： 盘亏出库
	 */
	public static final String BUSI_TYPE_OUTSTOCK_PROFIT="00031-0003";
	
	
	//#####################################################################
	/****************************************************************/
	/**
	 * 事件类型: 新建
	 */
	public static final String EVENT_METHOD_ADD = "add";
	/**
	 * 事件类型: 修改
	 */
	public static final String EVENT_METHOD_MODIFY = "modify";
	
	/**
	 * 事件类型: 取消
	 */
	public static final String EVENT_METHOD_CANCEL = "cancel";
	
	/**
	 * 事件类型: 删除
	 */
	public static final String EVENT_METHOD_DEL = "del";
	/*****************************chy***********************************/
	/*
	 * 20107-0003:诊疗项
	 */
	public static final String ORDER_TYPE_DIAGNOSE = "20107-0003";
	/*
	 * 20107-0002:中草药
	 */
	public static final String ORDER_TYPE_CY = "20107-0002";
	/*
	 * 20107-0001:西药/中成药
	 */
	public static final String ORDER_TYPE_XYZCY = "20107-0001";
	/*
	 * 10001-0001:西药
	 */
	public static final String DRUG_TYPE_XY = "10001-0001";
	/*
	 * 10001-0003:中成药
	 */
	public static final String DRUG_TYPE_ZCY = "10001-0003";
	/*
	 * 10001-0002:草药
	 */
	public static final String DRUG_TYPE_CY = "10001-0002";
	/*
	 * 10004-0001:西药费
	 */
	public static final String DRUG_TYPE_XYF = "10004-0001";
	/*
	 * 10004-0002:中成药费
	 */
	public static final String DRUG_TYPE_ZCYF = "10004-0002";
	/*
	 * 10004-0003:草药费
	 */
	public static final String DRUG_TYPE_CYF = "10004-0003";
	/*
	 * 10004-0004:诊疗费
	 */
	public static final String DRUG_TYPE_ZLF = "10004-0004";
	/*
	 * 10004-0005:材料费
	 */
	public static final String DRUG_TYPE_CLF = "10004-0005";
	/*
	 * 10004-0006:四舍五入费
	 */
	public static final String DRUG_TYPE_SSWRF = "10004-0006";
	/*
	 *诊所参数值：不启用
	 */
	public static final String PARAM_VALUE_START = "0";
	/*
	 * 诊所参数值：启用
	 */
	public static final String PARAM_VALUE_SEPARATE = "1";
	/*
	 * 0-正常交费
	 */
	public static final String PAY_STATUS_PAY = "0";
	/*
	 *1-部分退费
	 */
	public static final String PAY_STATUS_RETURN_PART = "1";
	/*
	 * 2-已退费
	 */
	public static final String PAY_STATUS_RETURN_ALL = "2";
	/*
	 * 医生表中的医生角色
	 */
	public static final String DOCTOR_ROLE_DOCTOR = "11";
	/*
	 * 医生表中的收费员角色
	 */
	public static final String DOCTOR_ROLE_TOLLKEEPER = "12";
	/*****************************chy***********************************/
	
	/**
	 *诊所编码获取的tblname 
	 */
	public static final String BUSI_TYPE_ORGCODE_TBLNAME="xt_zd_org";
	

	/**
	 *诊所编码公共ORGID
	 */
	public static final String BUSI_TYPE_ORGCODE_PREFIX="ORG";
	
	/**
	 *诊所编码公共ORGID
	 */
	public static final String BUSI_TYPE_ORGCODE_PLACEHOLDER="*";
	
	/**
	 *入库单号获取的tblname 
	 */
	public static final String BUSI_TYPE_INSTOCK_TBLNAME="ta_pha_instock";
	
	/**
	 *入库单号获取的前缀
	 */
	public static final String BUSI_TYPE_INSTOCK_PREFIX="IN";
	
	/**
	 *收费单据号获取的tblname 
	 */
	public static final String BUSI_TYPE_BILLING_TBLName="cl_bi_receipt";
	/**
	 *收费单据号获取的前缀
	 */
	public static final String BUSI_TYPE_BILLING_PREFIX="BI";
	/**
	 *支付业务单号获取的tblname 
	 */
	public static final String BUSI_TYPE_PAYMENT_TBLNAME="cs_bil_paid_route";
	/**
	 *支付业务单号获取的前缀
	 */
	public static final String BUSI_TYPE_PAYMENT_PREFIX="ZF";
	
	/**
	 * 加价方式类型
	 */
	public static final String BUSI_TYPE_PAYMENT_FARECODE="jjfs";
	
	/**
	 * 加价金额或者比例
	 */
	public static final String BUSI_TYPE_PAYMENT_FAREVALUE="jjblje";
	
	/**
	 * 有效标识
	 */
	public static final String BUSI_TYPE_PAYMENT_VALID="00016-0001";
	
	/**
	 * 无效标识
	 */
	public static final String BUSI_TYPE_PAYMENT_INVALID="00016-0000";
}
