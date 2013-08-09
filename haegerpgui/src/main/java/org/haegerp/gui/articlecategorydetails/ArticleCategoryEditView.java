package org.haegerp.gui.articlecategorydetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.gui.ArticleCategoryDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public class ArticleCategoryEditView implements ArticleCategoryDetailsInterface {

	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(ArticleCategoryDetails articleCategoryDetailsMenu) {
		articleCategoryDetailsMenu.btnCancel.setEnabled(true);
		articleCategoryDetailsMenu.btnCancel.setText("Cancel");
		articleCategoryDetailsMenu.btnSaveEdit.setEnabled(true);
		articleCategoryDetailsMenu.btnSaveEdit.setText("Save");
		
		articleCategoryDetailsMenu.txtDescription.setEditable(true);
		articleCategoryDetailsMenu.txtCountArticles.setEditable(false);
		articleCategoryDetailsMenu.txtName.setEditable(true);
		
		articleCategoryDetailsMenu.txtCountArticles.setValue(articleCategoryDetailsMenu.getArticleCategory().getArticles().size());
		articleCategoryDetailsMenu.txtName.setText(articleCategoryDetailsMenu.getArticleCategory().getName());
		articleCategoryDetailsMenu.txtDescription.setText(articleCategoryDetailsMenu.getArticleCategory().getDescription());
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
			
			articleCategoryDetailsMenu.getArticleCategoryManagement().loadTable();
			articleCategoryDetailsMenu.getArticleManagement().loadCbbCategory();
			articleCategoryDetailsMenu.getArticleManagement().loadTable();
			articleCategoryDetailsMenu.getArticleDetails().loadCbbCategory();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(articleCategoryDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(ArticleCategoryDetails articleCategoryDetailsMenu) {
		articleCategoryDetailsMenu.setShowMode();
	}

}
