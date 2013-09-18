package org.haegerp.gui.clientofferdetails;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.haegerp.entity.ClientOffer;
import org.haegerp.entity.ClientOfferDetail;
import org.haegerp.gui.ClientOfferDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ClientOfferNewView implements ClientOfferDetailsInterface {

	public void applyView(ClientOfferDetails clientOfferDetailsMenu) {
		clientOfferDetailsMenu.btnCancel.setEnabled(true);
		clientOfferDetailsMenu.btnCancel.setText("Cancel");
		clientOfferDetailsMenu.btnSaveEdit.setEnabled(true);
		clientOfferDetailsMenu.btnSaveEdit.setText("Save");
		clientOfferDetailsMenu.btnAddArticle.setEnabled(true);
		clientOfferDetailsMenu.btnDeleteArticle.setEnabled(true);
		
		clientOfferDetailsMenu.txtClient.setText("<Click here to select>");
		clientOfferDetailsMenu.txtOfferDate.setText("");
		clientOfferDetailsMenu.txtSendDate.setText("");
		clientOfferDetailsMenu.txtBilled.setText("");
		clientOfferDetailsMenu.txtBillPaid.setText("");
		clientOfferDetailsMenu.txtTotal.setText("");
		clientOfferDetailsMenu.txtDescription.setEditable(true);
		clientOfferDetailsMenu.txtDescription.setText("");
		
		clientOfferDetailsMenu.model = new DefaultTableModel(
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
		clientOfferDetailsMenu.tblArticles.setModel(clientOfferDetailsMenu.model);
		clientOfferDetailsMenu.tblArticles.removeColumn(clientOfferDetailsMenu.tblArticles.getColumn("ID"));
		
		clientOfferDetailsMenu.setClientOffer(new ClientOffer());
		clientOfferDetailsMenu.setClient(null);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void btnSaveEdit(ClientOfferDetails clientOfferDetailsMenu) {
		try {
			clientOfferDetailsMenu.getClientOffer().setClient(clientOfferDetailsMenu.getClient());
			clientOfferDetailsMenu.getClientOffer().setEmployee(EmployeeSession.getEmployee());
			
			clientOfferDetailsMenu.getClientOffer().setOfferDate(new Date());
			
			clientOfferDetailsMenu.getClientOffer().setDescription(clientOfferDetailsMenu.txtDescription.getText());
			
			clientOfferDetailsMenu.getClientOffer().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
			clientOfferDetailsMenu.getClientOffer().setLastModifiedDate(new Date());
			
			clientOfferDetailsMenu.setClientOffer(clientOfferDetailsMenu.getClientOfferController().newClientOffer(clientOfferDetailsMenu.getClientOffer()));
			
			Set<ClientOfferDetail> clientOfferArticles = clientOfferDetailsMenu.getClientOfferDetailController().doUpdateOfferArticle(extractObject(clientOfferDetailsMenu.tblArticles), clientOfferDetailsMenu.getClientOffer().getIdClientOffer());
			
			clientOfferDetailsMenu.getClientOffer().setClientOfferDetail(new HashSet<ClientOfferDetail>(clientOfferArticles));
			clientOfferDetailsMenu.getClientOffer().calculateTotal();
			clientOfferDetailsMenu.setClientOffer(clientOfferDetailsMenu.getClientOfferController().save(clientOfferDetailsMenu.getClientOffer()));
			
			clientOfferDetailsMenu.getClientOfferManagement().loadTable();
			clientOfferDetailsMenu.setShowMode();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(clientOfferDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void btnCancel(ClientOfferDetails clientOfferDetailsMenu) {
		clientOfferDetailsMenu.setVisible(false);
	}

    private Object[][] extractObject(JTable table) {
        Object[][] values = new Object[table.getRowCount()][3];
        for (int x = 0; x < table.getRowCount(); x++) {
            values[x][0] = table.getModel().getValueAt(x, 0);
            values[x][1] = table.getValueAt(x, 3);
            values[x][2] = table.getValueAt(x, 4);
        }
        
        return values;
    }

}
