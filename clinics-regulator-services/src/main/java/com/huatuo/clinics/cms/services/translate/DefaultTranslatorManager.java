package com.huatuo.clinics.cms.services.translate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.huatuo.clinics.cms.bean.SsDicItemDTO;
import com.huatuo.clinics.cms.services.regulator.DictionaryService;
import com.huatuo.common.Utils;

@Service
public class DefaultTranslatorManager {
	/**
	 * 缓存翻译器的名称
	 */
	public static final String CACHE_TRANSLATOR_NAME = "__CacheTranslator__";

	/**
	 * 字典翻译器的名称
	 */
	public static final String DICT_TRANSLATOR_NAME = "__DictTranslator__";

	@Autowired
	private DictionaryService dictionaryService;
	/**
	 * 日志记录器.
	 */
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultTranslatorManager.class);

	/**
	 * 翻译器容器
	 */
	private final static Map<String, Translator> s_translators = new HashMap<String, Translator>();

	/**
	 * 对象类全名与需要翻译字段的映射表
	 */
	private final static Map<String, List<Field>> s_className2TranslatedFieldMap = new HashMap<String, List<Field>>();

	/**
	 * 获取翻译器
	 * 
	 * @param translatorName
	 *            翻译器名称
	 * @return
	 */
	protected Translator getTranslator(String translatorName) {
		return s_translators.get(translatorName);
	}

	private void chcheTranslateFieldList(Class<?> clazz) {
		// 尝试从缓存中读取需要转换的字段
		List<Field> translateFieldList = s_className2TranslatedFieldMap.get(clazz.getName());
		if (translateFieldList == null) {
			translateFieldList = new ArrayList<Field>();
			Field[] fields = clazz.getDeclaredFields();

			for (Field codeField : fields) {
				TranslateField annotation = codeField.getAnnotation(TranslateField.class);
				if (annotation != null) {
					translateFieldList.add(codeField);
					logger.info("found one filed:[" + codeField.getName() + "]");
				}
			}

			s_className2TranslatedFieldMap.put(clazz.getName(), translateFieldList);
		}
	}

	/**
	 * 翻译对象中需要被翻译的字段
	 */
	public <T extends Translatable> T translate(T object, String orgId) {
		StringBuilder errorMessage = new StringBuilder();
		Class<?> clazz = object.getClass();
		chcheTranslateFieldList(clazz);

		List<Class<?>> classList = new ArrayList<Class<?>>();
		classList.add(clazz);

		while (null != clazz.getSuperclass()) {
			clazz = clazz.getSuperclass();
			chcheTranslateFieldList(clazz);
			classList.add(clazz);
		}

		for (Class<?> tempClazz : classList) {
			List<Field> translateFieldList = s_className2TranslatedFieldMap.get(tempClazz.getName());
			// 翻译数据
			for (Field sourceField : translateFieldList) {
				try {
					sourceField.setAccessible(true);
					Object sourceFieldValueAsObject = sourceField.get(object);
					String sourceFieldValue, targetFieldValue,targetFieldItemValueValue = null;

					// 源字段值为空或者NULL不进行翻译处理
					if (sourceFieldValueAsObject == null || sourceFieldValueAsObject.equals("")) {
						sourceFieldValue = null;
						continue;
					} else {
						sourceFieldValue = sourceFieldValueAsObject.toString();
					}

					TranslateField annotation = sourceField.getAnnotation(TranslateField.class);
					String translatorName = annotation.translatorName();

					// 字段没有定义翻译器名称，则不进行翻译处理
					if (translatorName == null || translatorName.equals("")) {
						errorMessage.setLength(0);
						errorMessage.append("translator not found.[translatorName:" + translatorName + ",domainObject=" + tempClazz.getName() + "]");
						throw new BusinessException(FrameworkErrorCodes.TranslatorNotFound, errorMessage.toString());
					}

					// 获取存储翻译结果的字段名称
					String targetFieldName = annotation.targetFieldName();
					String targetFieldItemValue = annotation.targetFieldItemValue();
					if (targetFieldName == null || targetFieldName.equals("")) {
						errorMessage.setLength(0);
						errorMessage.append("translate annotation missing targetFieldName.[domainObject:"+tempClazz.getName()+"]");
						throw new BusinessException(FrameworkErrorCodes.MissingTargetFieldName, errorMessage.toString());
					}

					// 获取缓存全路径，若用户没有设置缓存路径，则直接使用翻译器名称作为缓存全路径
					String fullQualifiedName = annotation.fullQualifiedName();
					//从redis中取对应对段
					SsDicItemDTO target=null;
					//当orgId存在时去查询，当查询不到时再去查询为null的模板
					if (!Utils.isBlank(orgId)) {
						//可传入多个code码进行翻译
						String[] split = fullQualifiedName.split(",");
						for (String string1 : split) {
							target = dictionaryService.getDicByCode(orgId,string1,sourceFieldValue);
							if (target == null) {
								target = dictionaryService.getDicByCode("",string1,sourceFieldValue);
							}
							if (target != null) {
								break;
							}
						}
					}
					if(target==null){
						targetFieldValue=sourceFieldValue;
						if (!Utils.isBlank(targetFieldItemValue)) {
							targetFieldItemValueValue = sourceFieldValue;
						}
					}else{
						targetFieldValue=target.getDictItemName();
						if (!Utils.isBlank(targetFieldItemValue)) {
							targetFieldItemValueValue = target.getDictItemValue();
						}
					}
					
					
					
					Field targetField = tempClazz.getDeclaredField(targetFieldName);
					targetField.setAccessible(true);
					targetField.set(object, targetFieldValue);
					Field targetField2 = null;
					if (!Utils.isBlank(targetFieldItemValue)) {
						targetField2 = tempClazz.getDeclaredField(targetFieldItemValue);
						targetField2.setAccessible(true);
						targetField2.set(object, targetFieldItemValueValue);
					}

					logger.trace("Translate field " + sourceField.getName() + " to " + targetField.getName()  + ", sourceFieldValue:"
							+ sourceFieldValue + " , targetFieldValue:" + targetFieldValue + " , targetFieldItemValueValue:" + targetFieldItemValueValue);
				} catch (Throwable e) {
					e.printStackTrace();
					errorMessage.setLength(0);
					errorMessage.append("translate occur exception.[");
					errorMessage.append("domainObject=");
					errorMessage.append(tempClazz.getName());
					errorMessage.append("]");

					throw new BusinessException(FrameworkErrorCodes.TranslateUnknownException, errorMessage.toString(), e);
				}
			}
		}
		return object;
	}

	/**
	 * 翻译对象中需要被翻译的字段
	 */
	public <T extends Translatable> List<T> translate(List<T> lists, String orgId) {
		for (T t : lists) {
			translate(t, orgId);
		}
		return lists;
	}

	/**
	 * 添加翻译器
	 */
	public void addTranslator(Translator translator) {
		Assert.notNull(translator);

		if (!s_translators.containsKey(translator.getTranslatorName())) {
			logger.trace("add translator:" + translator.getTranslatorName());
			s_translators.put(translator.getTranslatorName(), translator);
		}
	}

	/**
	 * 移除翻译器
	 */
	public void removeTranslator(Translator translator) {
		Assert.notNull(translator);
		if (s_translators.containsKey(translator.getTranslatorName())) {
			logger.trace("remove translator.[" + translator.getTranslatorName() + "]");
			s_translators.remove(translator.getTranslatorName());
		}
	}
}
