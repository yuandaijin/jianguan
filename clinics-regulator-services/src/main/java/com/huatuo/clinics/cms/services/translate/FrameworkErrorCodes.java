package com.huatuo.clinics.cms.services.translate;

/**
 * 框架错误代码表
 * @author william.zhang
 */
public class FrameworkErrorCodes
{
    /**
     * 上下文值不能改变
     */
    public static final String ContextValueCannotChanged = "FRAMEWORK-ERR-00000001";
    
    /**
     * 用户认证失败
     */
    public static final String AuthenticationFailure = "FRAMEWORK-ERR-00000002";
    
    /**
     * 检查是否已登录失败
     */
    public static final String CheckLoginFailure = "FRAMEWORK-ERR-00000003";
    
    /**
     * 加载应用配置失败
     */
    public static final String LoadConfigFailure = "FRAMEWORK-ERR-00000004";
    
    /**
     * 初始化应用程序上下文失败
     */
    public static final String InitAppContextFailure = "FRAMEWORK-ERR-00000005";
    
    /**
     * 登录失败
     */
    public static final String LoginFailure = "FRAMEWORK-ERR-00000006";
    
    /**
     * 注销失败
     */
    public static final String LogoutFailure = "FRAMEWORK-ERR-00000007";
    
    /**
     * 改变用户密码失败
     */
    public static final String ChangePasswordFailure = "FRAMEWORK-ERR-00000008";
    
    /**
     * 页面启动上下文为NULL
     */
    public static final String ModuleContextIsNull = "FRAMEWORK-ERR-00000009";
    
    /**
     * 页面参数个数或配置错
     */
    public static final String ModuleArgumentsError = "FRAMEWORK-ERR-00000010";
    
    /**
     * 找不到对应的翻译器
     */
    public static final String TranslatorNotFound = "FRAMEWORK-ERR-00000011";
    
    /**
     * 未定义翻译结果存储字段名称
     */
    public static final String MissingTargetFieldName = "FRAMEWORK-ERR-00000012";
    
    /**
     * 执行翻译发生未知错误
     */
    public static final String TranslateUnknownException = "FRAMEWORK-ERR-00000013";

    /**
     * 执行翻译发生未知错误
     */
    public static final String UnSupportDialectException = "FRAMEWORK-ERR-00000014";

    /**
     * 没有发现MyBaties定义的ID错误
     */
    public static final String NoFoundStatementIdException = "FRAMEWORK-ERR-00000015";

    /**
     * 新增员工组成员失败，不能选择一个已存在的员工进行关联.
     */
    public static final String AddEmpGroupIsExistException = "FRAMEWORK-ERR-00000016";
    
}