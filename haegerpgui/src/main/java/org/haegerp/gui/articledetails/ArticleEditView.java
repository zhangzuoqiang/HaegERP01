package org.haegerp.gui.articledetails;

import java.util.Date;

import javax.swing.JOptionPane;

import org.haegerp.gui.ArticleDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public class ArticleEditView implements ArticleDetailsInterface {

	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(ArticleDetails articleDetailsMenu) {
		articleDetailsMenu.btnCancel.setEnabled(true);
		articleDetailsMenu.btnCancel.setText("Cancel");
		articleDetailsMenu.btnSaveEdit.setEnabled(true);
		articleDetailsMenu.btnSaveEdit.setText("Save");
		
		articleDetailsMenu.cbbCategory.setEnabled(true);
		
		articleDetailsMenu.txtColor.setEditable(true);
		articleDetailsMenu.txtDescription.setEditable(true);
		articleDetailsMenu.txtEan.setEditable(true);
		articleDetailsMenu.txtName.setEditable(true);
		articleDetailsMenu.txtPriceGross.setEditable(true);
		articleDetailsMenu.txtPriceSupplier.setEditable(true);
		articleDetailsMenu.txtPriceVat.setEditable(true);
		articleDetailsMenu.txtSizeH.setEditable(true);
		articleDetailsMenu.txtSizeL.setEditable(true);
		articleDetailsMenu.txtSizeW.setEditable(true);
		articleDetailsMenu.txtStock.setEditable(true);
		
		articleDetailsMenu.txtEan.setValue(articleDetailsMenu.getArticle().getEan());
		articleDetailsMenu.txtName.setText(articleDetailsMenu.getArticle().getName());
		articleDetailsMenu.cbbCategory.setSelectedItem(articleDetailsMenu.getArticle().getArticleCategory().getName());
		articleDetailsMenu.txtColor.setText(articleDetailsMenu.getArticle().getColor());
		articleDetailsMenu.txtPriceVat.setValue(articleDetailsMenu.getArticle().getPriceVat());
		articleDetailsMenu.txtPriceGross.setValue(articleDetailsMenu.getArticle().getPriceGross());
		articleDetailsMenu.txtPriceSupplier.setValue(articleDetailsMenu.getArticle().getPriceSupplier());
		articleDetailsMenu.txtStock.setValue(articleDetailsMenu.getArticle().getStock());
		articleDetailsMenu.txtSizeH.setValue(articleDetailsMenu.getArticle().getSizeH());
		articleDetailsMenu.txtSizeL.setValue(articleDetailsMenu.getArticle().getSizeL());
		articleDetailsMenu.txtSizeW.setValue(articleDetailsMenu.getArticle().getSizeW());
		articleDetailsMenu.txtDescription.setText(articleDetailsMenu.getArticle().getDescription());
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
			
			articleDetailsMenu.getArticleManagement().loadTable();
			articleDetailsMenu.getArticleCategoryManagement().loadTable();
			
			articleDetailsMenu.setShowMode();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(articleDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(ArticleDetails articleDetailsMenu) {
		articleDetailsMenu.setShowMode();
	}

}
