package com.huatuo.clinics.cms.services.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.db.bean.ImArticleChannel;
import com.huatuo.db.repository.DbImarticleChannelRepository;

@Service
public class ImArticleChannelServiceImpl implements ImArticleChannelService {
	
	@Autowired
	private DbImarticleChannelRepository repository;

	@Override
	public List<ImArticleChannel> seleteByColumnId(String columnId) {
		return repository.seleteByColumnId(columnId);
	}

}
