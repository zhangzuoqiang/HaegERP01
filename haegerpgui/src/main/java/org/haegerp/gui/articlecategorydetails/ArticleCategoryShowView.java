package org.haegerp.gui.articlecategorydetails;

import org.haegerp.gui.ArticleCategoryDetails;

public class ArticleCategoryShowView implements ArticleCategoryDetailsInterface {

	public void applyView(ArticleCategoryDetails articleCategoryDetailsMenu) {
		articleCategoryDetailsMenu.btnCancel.setEnabled(true);
		articleCategoryDetailsMenu.btnCancel.setText("Exit");
		articleCategoryDetailsMenu.btnSaveEdit.setEnabled(true);
		articleCategoryDetailsMenu.btnSaveEdit.setText("Edit");
		
		articleCategoryDetailsMenu.txtCountArticles.setValue(articleCategoryDetailsMenu.getArticleCategory().getArticles().size());
		articleCategoryDetailsMenu.txtName.setText(articleCategoryDetailsMenu.getArticleCategory().getName());
		articleCategoryDetailsMenu.txtDescription.setText(articleCategoryDetailsMenu.getArticleCategory().getDescription());
		
		articleCategoryDetailsMenu.txtDescription.setEditable(false);
		articleCategoryDetailsMenu.txtCountArticles.setEditable(false);
		articleCategoryDetailsMenu.txtName.setEditable(false);
	}

	public void btnSaveEdit(ArticleCategoryDetails articleCategoryDetailsMenu) {
		articleCategoryDetailsMenu.setEditMode();
	}

	public void btnCancel(ArticleCategoryDetails articleCategoryDetailsMenu) {
		articleCategoryDetailsMenu.setVisible(false);
	}

}
