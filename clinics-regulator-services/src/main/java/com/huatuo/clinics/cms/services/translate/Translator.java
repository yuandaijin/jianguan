package com.huatuo.clinics.cms.services.translate;


/**
 * 翻译器接口
 * 
 * @author william.zhang
 */
public interface Translator
{
    
    /**
     * 获取翻译器名称
     * @return
     */
    public String getTranslatorName();
    
    /**
     * 执行翻译
     * @param fullQuilifiedName 缓存全路径或者字典全路径
     * @param sourceFieldValue 待翻译的字段值
     * @return
     */
    public String translate(String fullQuilifiedName, String sourceFieldValue);
    
    /**
     * 执行翻译-适用与机构相关的字典
     * @param fullQuilifiedName 缓存全路径或者字典全路径
     * @param sourceFieldValue 待翻译的字段值
     * @return
     */
    public String translate(String fullQuilifiedName, String sourceFieldValue, String orgId);
    
    
    
}