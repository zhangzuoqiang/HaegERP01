package org.haegerp.controller;

import java.util.List;

import org.haegerp.entity.ArticleCategory;
import org.haegerp.entity.repository.article.ArticleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCategoryController {
	
	@Autowired
	private ArticleCategoryRepository articleCategoryRepository;
	
	public ArticleCategoryController() {
		// TODO Auto-generated constructor stub
	}

	public List<ArticleCategory> getAllCategories() {
		return articleCategoryRepository.findAll();
	}
}
