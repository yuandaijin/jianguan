package com.huatuo.clinics.cms.services.article;

import java.util.List;

import com.huatuo.db.bean.ImArticleColumn;

/**
 * 咨询栏目
 * @author Administrator
 *
 */
public interface ImArticleColumnService {

	/**
	 * 根据栏目维护类型获得栏目列表
	 * @param type 0:华佗在线，1：个体诊所
	 * @return
	 */
	List<ImArticleColumn> getColumnsByType(int type);

}
