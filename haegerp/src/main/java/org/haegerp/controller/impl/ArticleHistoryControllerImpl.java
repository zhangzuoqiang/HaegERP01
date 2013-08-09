package org.haegerp.controller.impl;

import org.haegerp.controller.ArticleHistoryController;
import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.haegerp.entity.repository.article.ArticleHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleHistoryControllerImpl implements ArticleHistoryController {

	@Autowired
	private ArticleHistoryRepository articleHistoryRepository;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Long getLastVersion(long idArticle) {
		return articleHistoryRepository.findByIdArticle(idArticle);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ArticleHistory getArticleHistoryById(ArticleHistoryPK articleHistoryPK) {
		return articleHistoryRepository.findOne(articleHistoryPK);
	}

}
