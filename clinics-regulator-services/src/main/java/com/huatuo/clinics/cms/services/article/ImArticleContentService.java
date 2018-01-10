package com.huatuo.clinics.cms.services.article;

import java.util.List;

import com.huatuo.db.bean.ImArticleContent;

/**
 * 咨询服务
 * @author Administrator
 *
 */
public interface ImArticleContentService {

	public int insert(ImArticleContent content);

	public int update(ImArticleContent content);

	public List<ImArticleContent> selectByColumnId(String columnId);
	/**
	 * 根据id获得咨询内容
	 * @param contentId
	 * @return
	 */
	public ImArticleContent selectById(String contentId);

}
