package org.haegerp.gui.articlecategorydetails;

import org.haegerp.gui.ArticleCategoryDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public interface ArticleCategoryDetailsInterface {
	
	/**
	 * Das Interface wird auf dem Artikelkategoriedetailsmenü angewendet
	 * 
	 * @param articleCategoryDetailsMenu Das Artikelkategoriedetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(ArticleCategoryDetails articleCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param articleCategoryDetailsMenu Das Artikelkategoriedetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ArticleCategoryDetails articleCategoryDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param articleCategoryDetailsMenu Das Artikelkategoriedetailsmenü
	 */
	public void btnCancel(ArticleCategoryDetails articleCategoryDetailsMenu);
}
