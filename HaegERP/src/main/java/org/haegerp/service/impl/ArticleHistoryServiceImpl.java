package org.haegerp.service.impl;

import org.haegerp.service.ArticleHistoryService;
import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.haegerp.entity.repository.article.ArticleHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleHistoryServiceImpl implements ArticleHistoryService {

	@Autowired
	private ArticleHistoryRepository articleHistoryRepository;
	
	@Transactional(propagation=Propagation.REQUIRED)
        @Override
	public Long getLastVersion(long idArticle) {
		return articleHistoryRepository.findByIdArticle(idArticle);
	}

	@Transactional(propagation = Propagation.REQUIRED)
        @Override
	public ArticleHistory getArticleHistoryById(ArticleHistoryPK articleHistoryPK) {
		return articleHistoryRepository.findOne(articleHistoryPK);
	}

}
