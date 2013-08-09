package org.haegerp.gui.articledetails;

import org.haegerp.gui.ArticleDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public class ArticleShowView implements ArticleDetailsInterface {
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(ArticleDetails articleDetailsMenu) {
		articleDetailsMenu.btnCancel.setEnabled(true);
		articleDetailsMenu.btnCancel.setText("Exit");
		articleDetailsMenu.btnSaveEdit.setEnabled(true);
		articleDetailsMenu.btnSaveEdit.setText("Edit");
		
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
		
		articleDetailsMenu.cbbCategory.setEnabled(false);
		articleDetailsMenu.txtColor.setEditable(false);
		articleDetailsMenu.txtDescription.setEditable(false);
		articleDetailsMenu.txtEan.setEditable(false);
		articleDetailsMenu.txtName.setEditable(false);
		articleDetailsMenu.txtPriceGross.setEditable(false);
		articleDetailsMenu.txtPriceSupplier.setEditable(false);
		articleDetailsMenu.txtPriceVat.setEditable(false);
		articleDetailsMenu.txtSizeH.setEditable(false);
		articleDetailsMenu.txtSizeL.setEditable(false);
		articleDetailsMenu.txtSizeW.setEditable(false);
		articleDetailsMenu.txtStock.setEditable(false);
	}

	public void btnSaveEdit(ArticleDetails articleDetailsMenu) {
		articleDetailsMenu.setEditMode();
	}

	public void btnCancel(ArticleDetails articleDetailsMenu) {
		articleDetailsMenu.setVisible(false);
	}

}
