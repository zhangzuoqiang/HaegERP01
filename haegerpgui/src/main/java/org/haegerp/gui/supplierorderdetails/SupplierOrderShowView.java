package org.haegerp.gui.supplierorderdetails;

import java.text.SimpleDateFormat;

import javax.swing.table.DefaultTableModel;

import org.haegerp.entity.SupplierBill;
import org.haegerp.gui.SupplierOrderDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SupplierOrderShowView implements SupplierOrderDetailsInterface {

	@Transactional(propagation=Propagation.REQUIRED)
	public void applyView(SupplierOrderDetails supplierOrderDetailsMenu) {
		supplierOrderDetailsMenu.btnCancel.setEnabled(true);
		supplierOrderDetailsMenu.btnCancel.setText("Exit");
		supplierOrderDetailsMenu.btnSaveEdit.setEnabled(true);
		supplierOrderDetailsMenu.btnSaveEdit.setText("Edit");
		supplierOrderDetailsMenu.btnAddArticle.setEnabled(false);
		supplierOrderDetailsMenu.btnDeleteArticle.setEnabled(false);
		
		supplierOrderDetailsMenu.txtSupplier.setText(supplierOrderDetailsMenu.getSupplierController().getSupplierById(supplierOrderDetailsMenu.getSupplier().getIdBusinessPartner()).getName());
		supplierOrderDetailsMenu.txtOrderDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrderDetailsMenu.getSupplierOrder().getOrderDate()));
		if (supplierOrderDetailsMenu.getSupplierOrder().getSendDate() == null)
			supplierOrderDetailsMenu.txtSendDate.setText("Not sent yet");
		else 
			supplierOrderDetailsMenu.txtSendDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrderDetailsMenu.getSupplierOrder().getSendDate()));
		
		if (supplierOrderDetailsMenu.getSupplierOrder().getSupplierBill() != null)
		{
			SupplierBill supplierBill = supplierOrderDetailsMenu.getSupplierOrderController().getSupplierBillById(supplierOrderDetailsMenu.getSupplierOrder().getSupplierBill().getIdSupplierBill());

			supplierOrderDetailsMenu.txtBillReceived.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierBill.getReceivedDate()));
			
			if (supplierBill.getPaidDate() != null)
				supplierOrderDetailsMenu.txtBillPaid.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierBill.getPaidDate()));
			else
				supplierOrderDetailsMenu.txtBillPaid.setText("Not yet paid");

		} else {
			if (supplierOrderDetailsMenu.getSupplierOrder().getSendDate() == null)
			{
				supplierOrderDetailsMenu.txtBillPaid.setText("");
				supplierOrderDetailsMenu.txtBillReceived.setText("");
			} else {
				supplierOrderDetailsMenu.txtBillPaid.setText("");
				supplierOrderDetailsMenu.txtBillReceived.setText("Bill not received yet");
			}
		}
		
		supplierOrderDetailsMenu.txtTotal.setText(String.valueOf(supplierOrderDetailsMenu.getSupplierOrder().getTotal()) + " €");
		supplierOrderDetailsMenu.txtDescription.setText(supplierOrderDetailsMenu.getSupplierOrder().getDescription());
		supplierOrderDetailsMenu.txtDescription.setEditable(false);
		
		loadArticlesTable(supplierOrderDetailsMenu);
	}

	private void loadArticlesTable(SupplierOrderDetails supplierOrderDetailsMenu) {
		supplierOrderDetailsMenu.model = new DefaultTableModel(
    			supplierOrderDetailsMenu.getSupplierOrderDetailController().loadTableRows(supplierOrderDetailsMenu.getSupplierOrder().getIdSupplierOrder()) ,
    			new String [] {
    				"ID",
    				"EAN",
    				"Name",
    				"Supplier Price",
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
		supplierOrderDetailsMenu.tblArticles.setModel(supplierOrderDetailsMenu.model);
	        	
		supplierOrderDetailsMenu.tblArticles.removeColumn(supplierOrderDetailsMenu.tblArticles.getColumn("ID"));
	}
	
	public void btnSaveEdit(SupplierOrderDetails supplierOrderDetailsMenu) {
		supplierOrderDetailsMenu.setEditMode();
	}

	public void btnCancel(SupplierOrderDetails supplierOrderDetailsMenu) {
		supplierOrderDetailsMenu.setVisible(false);
	}

}
