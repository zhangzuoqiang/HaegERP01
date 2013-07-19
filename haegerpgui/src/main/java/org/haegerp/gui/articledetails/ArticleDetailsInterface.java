package org.haegerp.gui.articledetails;

import org.haegerp.gui.ArticleDetails;

public interface ArticleDetailsInterface {
	
	/**
	 * Das Interface wird auf dem ArtikelDetailsmenü angewendet
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	public void applyView(ArticleDetails articleDetailsMenu);
	
	/**
	 * Einsatz, dass die Taste tut
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	public void btnSaveEdit(ArticleDetails articleDetailsMenu);
	
	/**
	 * Einsatz, dass die Taste tut
	 * 
	 * @param articleDetailsMenu Das ArtikelDetailsmenü
	 */
	public void btnCancel(ArticleDetails articleDetailsMenu);
}
