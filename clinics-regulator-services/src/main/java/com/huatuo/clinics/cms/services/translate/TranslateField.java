package com.huatuo.clinics.cms.services.translate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 翻译字段注解 标识业务实体中需要翻译的字段
 * 
 * @author william.zhang
 *        
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TranslateField {
	/**
	 * 翻译器名称
	 * 
	 * @return
	 */
	String translatorName() default "__DictTranslator__";

	/**
	 * 存储翻译结果的字段名称
	 * 
	 * @return
	 */
	String targetFieldName() default "";
	/**
	 * 存储翻译结果的字段名称dict_item_value
	 * 
	 * @return
	 */
	String targetFieldItemValue() default "";

	/**
	 * 缓存全路径或者字典路径全路径 （如/{字典类型编码}/{字典编码})
	 */
	String fullQualifiedName() default "";
}