package org.haegerp.entity.repository;

import org.haegerp.entity.Article;
import org.haegerp.exception.LengthOverflowException;

public interface ArticleRepositoryCustom {
	
	public void createArticleHistory(Article article) throws LengthOverflowException;
}
