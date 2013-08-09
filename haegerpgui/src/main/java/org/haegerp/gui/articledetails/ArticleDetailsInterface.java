package org.haegerp.gui.articledetails;

import org.haegerp.gui.ArticleDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public interface ArticleDetailsInterface {
	
	/**
	 * Das Interface wird auf dem ArtikelDetailsmenü angewendet
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(ArticleDetails articleDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut.
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ArticleDetails articleDetailsMenu);
	
	/**
	 * Betrieb, dass die Taste tut
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	public void btnCancel(ArticleDetails articleDetailsMenu);
}
