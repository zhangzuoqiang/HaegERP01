package org.haegerp.gui.clientofferdetails;

import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

import org.haegerp.entity.ClientBill;
import org.haegerp.gui.ClientOfferDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ClientOfferShowView implements ClientOfferDetailsInterface {

	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(ClientOfferDetails clientOfferDetailsMenu) {
		clientOfferDetailsMenu.btnCancel.setEnabled(true);
		clientOfferDetailsMenu.btnCancel.setText("Exit");
		clientOfferDetailsMenu.btnSaveEdit.setEnabled(true);
		clientOfferDetailsMenu.btnSaveEdit.setText("Edit");
		clientOfferDetailsMenu.btnAddArticle.setEnabled(false);
		clientOfferDetailsMenu.btnDeleteArticle.setEnabled(false);
		
		clientOfferDetailsMenu.txtClient.setText(clientOfferDetailsMenu.getClientController().getClientById(clientOfferDetailsMenu.getClient().getIdBusinessPartner()).getName());
		clientOfferDetailsMenu.txtOfferDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOfferDetailsMenu.getClientOffer().getOfferDate()));
		if (clientOfferDetailsMenu.getClientOffer().getSendDate() == null)
			clientOfferDetailsMenu.txtSendDate.setText("Not sent yet");
		else 
			clientOfferDetailsMenu.txtSendDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientOfferDetailsMenu.getClientOffer().getSendDate()));
		
		if (clientOfferDetailsMenu.getClientOffer().getClientBill() != null)
		{
			ClientBill clientBill = clientOfferDetailsMenu.getClientOfferController().getClientBillById(clientOfferDetailsMenu.getClientOffer().getClientBill().getIdClientBill());

			clientOfferDetailsMenu.txtBilled.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientBill.getBilledDate()));
			
			if (clientBill.getPaidDate() != null)
				clientOfferDetailsMenu.txtBillPaid.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(clientBill.getPaidDate()));
			else
				clientOfferDetailsMenu.txtBillPaid.setText("Not yet paid");

		} else {
			if (clientOfferDetailsMenu.getClientOffer().getSendDate() == null)
			{
				clientOfferDetailsMenu.txtBillPaid.setText("");
				clientOfferDetailsMenu.txtBilled.setText("");
			} else {
				clientOfferDetailsMenu.txtBillPaid.setText("");
				clientOfferDetailsMenu.txtBillPaid.setText("Bill not received yet");
			}
		}
		
		clientOfferDetailsMenu.txtTotal.setText(String.valueOf(clientOfferDetailsMenu.getClientOffer().getTotal()) + " €");
		clientOfferDetailsMenu.txtDescription.setText(clientOfferDetailsMenu.getClientOffer().getDescription());
		clientOfferDetailsMenu.txtDescription.setEditable(false);
		
		loadArticlesTable(clientOfferDetailsMenu);
	}

	private void loadArticlesTable(ClientOfferDetails clientOfferDetailsMenu) {
		clientOfferDetailsMenu.model = new DefaultTableModel(
    			clientOfferDetailsMenu.getClientOfferDetailController().loadTableRows(clientOfferDetailsMenu.getClientOffer().getIdClientOffer()) ,
    			new String [] {
    				"ID",
    				"EAN",
    				"Name",
    				"Price",
    				"VAT",
    				"Quantity",
    				"Discount",
    				"Total"
    			})
        {
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable(int row, int column) {
				return false;
			};
        };
		clientOfferDetailsMenu.tblArticles.setModel(clientOfferDetailsMenu.model);
	        	
		clientOfferDetailsMenu.tblArticles.removeColumn(clientOfferDetailsMenu.tblArticles.getColumn("ID"));
	}
	
	public void btnSaveEdit(ClientOfferDetails clientOfferDetailsMenu) {
		clientOfferDetailsMenu.setEditMode();
	}

	public void btnCancel(ClientOfferDetails clientOfferDetailsMenu) {
		clientOfferDetailsMenu.setVisible(false);
	}
}
