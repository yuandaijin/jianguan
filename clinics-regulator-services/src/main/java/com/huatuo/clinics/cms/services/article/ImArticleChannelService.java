package com.huatuo.clinics.cms.services.article;

import java.util.List;

import com.huatuo.db.bean.ImArticleChannel;

/**
 * 咨询频道
 * @author Administrator
 *
 */
public interface ImArticleChannelService {

	/**
	 * 根据栏目id获得下属有效频道列表
	 * @param columnId
	 * @return
	 */
	List<ImArticleChannel> seleteByColumnId(String columnId);

}
