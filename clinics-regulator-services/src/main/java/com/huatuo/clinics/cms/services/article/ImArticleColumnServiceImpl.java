package com.huatuo.clinics.cms.services.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatuo.db.bean.ImArticleColumn;
import com.huatuo.db.repository.DbImArticleColumeRepository;

@Service
public class ImArticleColumnServiceImpl implements ImArticleColumnService {

	@Autowired
	private DbImArticleColumeRepository repository;
	@Override
	public List<ImArticleColumn> getColumnsByType(int type) {
		return repository.getColumnsByType(type);
	}

}
