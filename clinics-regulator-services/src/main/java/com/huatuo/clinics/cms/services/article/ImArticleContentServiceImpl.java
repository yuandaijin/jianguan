package com.huatuo.clinics.cms.services.article;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.db.bean.ImArticleContent;
import com.huatuo.db.repository.DbImArticleContentRepository;

@Service
public class ImArticleContentServiceImpl implements ImArticleContentService {
	@Autowired
	private DbImArticleContentRepository repository;

	@Override
	public int insert(ImArticleContent content) {
		content.setContentId(UUID.randomUUID().toString());
		return repository.insert(content);
	}

	@Override
	public int update(ImArticleContent content) {
		return repository.update(content);
	}

	@Override
	public List<ImArticleContent> selectByColumnId(String columnId) {
		return repository.selectByColumnId(columnId);
	}
	
	@Override
	public ImArticleContent selectById(String contentId) {
		return repository.getById(contentId);
	}
}
