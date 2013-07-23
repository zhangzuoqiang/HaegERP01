package org.haegerp.gui.articlecategorydetails;

import org.haegerp.gui.ArticleCategoryDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleCategoryDetailsInterface {
	
	/**
	 * Das Interface wird auf dem ArtikelkategorieDetailsmenü angewendet
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	public void applyView(ArticleCategoryDetails articleCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ArticleCategoryDetails articleCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	public void btnCancel(ArticleCategoryDetails articleCategoryDetailsMenu);
}
