package org.haegerp.gui.articlecategorydetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.entity.ArticleCategory;
import org.haegerp.gui.ArticleCategoryDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ArticleCategoryNewView implements ArticleCategoryDetailsInterface {

	public void applyView(ArticleCategoryDetails articleCategoryDetailsMenu) {
		articleCategoryDetailsMenu.btnCancel.setEnabled(true);
		articleCategoryDetailsMenu.btnCancel.setText("Cancel");
		articleCategoryDetailsMenu.btnSaveEdit.setEnabled(true);
		articleCategoryDetailsMenu.btnSaveEdit.setText("Save");
		
		articleCategoryDetailsMenu.txtDescription.setEditable(true);
		articleCategoryDetailsMenu.txtDescription.setText("");
		
		articleCategoryDetailsMenu.txtCountArticles.setEditable(false);
		articleCategoryDetailsMenu.txtCountArticles.setValue(null);
		
		articleCategoryDetailsMenu.txtName.setEditable(true);
		articleCategoryDetailsMenu.txtName.setText("");
		
		articleCategoryDetailsMenu.setArticleCategory(new ArticleCategory());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ArticleCategoryDetails articleCategoryDetailsMenu) {
		try {
			articleCategoryDetailsMenu.getArticleCategory().setName(articleCategoryDetailsMenu.txtName.getText());
			
			articleCategoryDetailsMenu.getArticleCategory().setDescription(articleCategoryDetailsMenu.txtDescription.getText());
			
			articleCategoryDetailsMenu.getArticleCategory().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			articleCategoryDetailsMenu.getArticleCategory().setLastModifiedDate(new Date());
			
			articleCategoryDetailsMenu.setArticleCategory(articleCategoryDetailsMenu.getArticleCategoryController().save(articleCategoryDetailsMenu.getArticleCategory()));
			
			articleCategoryDetailsMenu.setShowMode();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(articleCategoryDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(ArticleCategoryDetails articleCategoryDetailsMenu) {
		articleCategoryDetailsMenu.setVisible(false);
	}

}
