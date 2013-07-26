package org.haegerp.gui.articledetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.entity.Article;
import org.haegerp.gui.ArticleDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ArticleNewView implements ArticleDetailsInterface {

	public void applyView(ArticleDetails articleDetailsMenu) {
		articleDetailsMenu.btnCancel.setEnabled(true);
		articleDetailsMenu.btnCancel.setText("Cancel");
		articleDetailsMenu.btnSaveEdit.setEnabled(true);
		articleDetailsMenu.btnSaveEdit.setText("Save");
		
		articleDetailsMenu.cbbCategory.setEnabled(true);
		articleDetailsMenu.cbbCategory.setSelectedIndex(0);
		
		articleDetailsMenu.txtColor.setEditable(true);
		articleDetailsMenu.txtColor.setText("");
		
		articleDetailsMenu.txtDescription.setEditable(true);
		articleDetailsMenu.txtDescription.setText("");
		
		articleDetailsMenu.txtEan.setEditable(true);
		articleDetailsMenu.txtEan.setValue(null);
		
		articleDetailsMenu.txtName.setEditable(true);
		articleDetailsMenu.txtName.setText("");
		
		articleDetailsMenu.txtPriceGross.setEditable(true);
		articleDetailsMenu.txtPriceGross.setValue(null);
		
		articleDetailsMenu.txtPriceSupplier.setEditable(true);
		articleDetailsMenu.txtPriceSupplier.setValue(null);
		
		articleDetailsMenu.txtPriceVat.setEditable(true);
		articleDetailsMenu.txtPriceVat.setValue(null);
		
		articleDetailsMenu.txtSizeH.setEditable(true);
		articleDetailsMenu.txtSizeH.setValue(null);
		
		articleDetailsMenu.txtSizeL.setEditable(true);
		articleDetailsMenu.txtSizeL.setValue(null);
		
		articleDetailsMenu.txtSizeW.setEditable(true);
		articleDetailsMenu.txtSizeW.setValue(null);
		
		articleDetailsMenu.txtStock.setEditable(true);
		articleDetailsMenu.txtStock.setValue(null);
		
		articleDetailsMenu.setArticle(new Article());
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ArticleDetails articleDetailsMenu) {
		try {
			articleDetailsMenu.getArticle().setEan((Long) articleDetailsMenu.txtEan.getValue());
			articleDetailsMenu.getArticle().setName(articleDetailsMenu.txtName.getText());
			
			articleDetailsMenu.getArticle().setArticleCategory(articleDetailsMenu.getCategories().get(articleDetailsMenu.cbbCategory.getSelectedIndex()-1));
			
			articleDetailsMenu.getArticle().setColor(articleDetailsMenu.txtColor.getText());
			
			articleDetailsMenu.getArticle().setPriceVat((Float) articleDetailsMenu.txtPriceVat.getValue());
			articleDetailsMenu.getArticle().setPriceGross(Float.valueOf(articleDetailsMenu.txtPriceGross.getValue().toString()));
			articleDetailsMenu.getArticle().setPriceSupplier(Float.valueOf(articleDetailsMenu.txtPriceSupplier.getValue().toString()));
			
			articleDetailsMenu.getArticle().setStock((Long) articleDetailsMenu.txtStock.getValue());
			
			articleDetailsMenu.getArticle().setSizeH((Float) articleDetailsMenu.txtSizeH.getValue());
			articleDetailsMenu.getArticle().setSizeL((Float) articleDetailsMenu.txtSizeL.getValue());
			articleDetailsMenu.getArticle().setSizeW((Float) articleDetailsMenu.txtSizeW.getValue());
			
			articleDetailsMenu.getArticle().setDescription(articleDetailsMenu.txtDescription.getText());
			
			articleDetailsMenu.getArticle().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			articleDetailsMenu.getArticle().setLastModifiedDate(new Date());
			
			articleDetailsMenu.setArticle(articleDetailsMenu.getArticleController().save(articleDetailsMenu.getArticle()));
			
			articleDetailsMenu.setShowMode();
			
			articleDetailsMenu.getArticleCategoryManagement().loadTable();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(articleDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(ArticleDetails articleDetailsMenu) {
		articleDetailsMenu.setVisible(false);
	}

}
