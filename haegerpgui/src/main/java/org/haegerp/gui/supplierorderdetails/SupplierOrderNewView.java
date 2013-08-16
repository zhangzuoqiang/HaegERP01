package org.haegerp.gui.supplierorderdetails;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.haegerp.entity.SupplierOrder;
import org.haegerp.entity.SupplierOrderDetail;
import org.haegerp.gui.SupplierOrderDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SupplierOrderNewView implements SupplierOrderDetailsInterface {

	public void applyView(SupplierOrderDetails supplierOrderDetailsMenu) {
		supplierOrderDetailsMenu.btnCancel.setEnabled(true);
		supplierOrderDetailsMenu.btnCancel.setText("Cancel");
		supplierOrderDetailsMenu.btnSaveEdit.setEnabled(true);
		supplierOrderDetailsMenu.btnSaveEdit.setText("Save");
		supplierOrderDetailsMenu.btnAddArticle.setEnabled(true);
		supplierOrderDetailsMenu.btnDeleteArticle.setEnabled(true);
		
		supplierOrderDetailsMenu.txtSupplier.setText("<Click here to select>");
		supplierOrderDetailsMenu.txtOrderDate.setText("");
		supplierOrderDetailsMenu.txtSendDate.setText("");
		supplierOrderDetailsMenu.txtBillReceived.setText("");
		supplierOrderDetailsMenu.txtBillPaid.setText("");
		supplierOrderDetailsMenu.txtTotal.setText("");
		supplierOrderDetailsMenu.txtDescription.setEditable(true);
		supplierOrderDetailsMenu.txtDescription.setText("");
		
		supplierOrderDetailsMenu.model = new DefaultTableModel(
				new Object[][]{},
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
			
			private static final String REGEX_INTEGER_NUMBER = "^[0-9]*$";
			private static final String REGEX_FLOAT_NUMBER = "^[0-9]*|[0-9]*.[0-9]{1,2}|[0-9]*,[0-9]{1,2}$";
			
			public boolean isCellEditable(int row, int column) {
				if (column > 3 && column < 6)
					return true;
				return false;
			};
			
			public void setValueAt(Object aValue, int row, int column) {
				String errors = "";
				if (column == 4)
					errors += checkField(String.valueOf(aValue), REGEX_INTEGER_NUMBER);
				if (column == 5)
					errors += checkField(String.valueOf(aValue), REGEX_FLOAT_NUMBER);
				
				if (errors.equals(""))
					super.setValueAt(aValue, row, column);
				else
					JOptionPane.showMessageDialog(null, errors);
			}

			private String checkField(String value, String regex) {
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(value);
				if (!matcher.matches())
					return "Value inserted with errors!\nPlease enter a valid value.";
				return "";
			}
        };
		supplierOrderDetailsMenu.tblArticles.setModel(supplierOrderDetailsMenu.model);
		supplierOrderDetailsMenu.tblArticles.removeColumn(supplierOrderDetailsMenu.tblArticles.getColumn("ID"));
		
		supplierOrderDetailsMenu.setSupplierOrder(new SupplierOrder());
		supplierOrderDetailsMenu.setSupplier(null);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(SupplierOrderDetails supplierOrderDetailsMenu) {
		try {
			supplierOrderDetailsMenu.getSupplierOrder().setSupplier(supplierOrderDetailsMenu.getSupplier());
			supplierOrderDetailsMenu.getSupplierOrder().setEmployee(EmployeeSession.getEmployee());
			
			supplierOrderDetailsMenu.getSupplierOrder().setOrderDate(new Date());
			
			supplierOrderDetailsMenu.getSupplierOrder().setDescription(supplierOrderDetailsMenu.txtDescription.getText());
			
			supplierOrderDetailsMenu.getSupplierOrder().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			supplierOrderDetailsMenu.getSupplierOrder().setLastModifiedDate(new Date());
			
			supplierOrderDetailsMenu.setSupplierOrder(supplierOrderDetailsMenu.getSupplierOrderController().newSupplierOrder(supplierOrderDetailsMenu.getSupplierOrder()));
			
			Set<SupplierOrderDetail> supplierOrderArticles = supplierOrderDetailsMenu.getSupplierOrderDetailController().updateOrderArticle(supplierOrderDetailsMenu.tblArticles, supplierOrderDetailsMenu.getSupplierOrder().getIdSupplierOrder());
			
			supplierOrderDetailsMenu.getSupplierOrder().setSupplierOrderDetail(new HashSet<SupplierOrderDetail>(supplierOrderArticles));
			supplierOrderDetailsMenu.getSupplierOrder().calculateTotal();
			supplierOrderDetailsMenu.setSupplierOrder(supplierOrderDetailsMenu.getSupplierOrderController().save(supplierOrderDetailsMenu.getSupplierOrder()));
			
			supplierOrderDetailsMenu.getSupplierOrderManagement().loadTable();
			supplierOrderDetailsMenu.setShowMode();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(supplierOrderDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnCancel(SupplierOrderDetails supplierOrderDetailsMenu) {
		supplierOrderDetailsMenu.setVisible(false);
	}

}
