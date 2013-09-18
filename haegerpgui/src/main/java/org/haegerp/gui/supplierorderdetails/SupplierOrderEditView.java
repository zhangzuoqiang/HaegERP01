package org.haegerp.gui.supplierorderdetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.haegerp.entity.SupplierBill;
import org.haegerp.entity.SupplierOrderDetail;
import org.haegerp.gui.SupplierOrderDetails;
import org.haegerp.session.EmployeeSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class SupplierOrderEditView implements SupplierOrderDetailsInterface {

    public void applyView(SupplierOrderDetails supplierOrderDetailsMenu) {
        if (supplierOrderDetailsMenu.getSupplierOrder().getSupplierBill() != null) {
            supplierOrderDetailsMenu.tblArticles.setEnabled(false);
            supplierOrderDetailsMenu.btnAddArticle.setEnabled(false);
            supplierOrderDetailsMenu.btnDeleteArticle.setEnabled(false);
        } else {
            supplierOrderDetailsMenu.btnAddArticle.setEnabled(true);
            supplierOrderDetailsMenu.btnDeleteArticle.setEnabled(true);
        }
        supplierOrderDetailsMenu.btnCancel.setEnabled(true);
        supplierOrderDetailsMenu.btnCancel.setText("Cancel");
        supplierOrderDetailsMenu.btnSaveEdit.setEnabled(true);
        supplierOrderDetailsMenu.btnSaveEdit.setText("Save");


        supplierOrderDetailsMenu.txtDescription.setEditable(true);

        supplierOrderDetailsMenu.txtSupplier.setText(supplierOrderDetailsMenu.getSupplierController().getSupplierById(supplierOrderDetailsMenu.getSupplier().getIdBusinessPartner()).getName());
        supplierOrderDetailsMenu.txtOrderDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrderDetailsMenu.getSupplierOrder().getOrderDate()));
        if (supplierOrderDetailsMenu.getSupplierOrder().getSendDate() == null) {
            supplierOrderDetailsMenu.txtSendDate.setText("<Click here to mark as sent>");
        } else {
            supplierOrderDetailsMenu.txtSendDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrderDetailsMenu.getSupplierOrder().getSendDate()));
        }

        if (supplierOrderDetailsMenu.getSupplierOrder().getSupplierBill() != null) {
            SupplierBill supplierBill = supplierOrderDetailsMenu.getSupplierOrderController().getSupplierBillById(supplierOrderDetailsMenu.getSupplierOrder().getSupplierBill().getIdSupplierBill());

            supplierOrderDetailsMenu.txtBillReceived.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierBill.getReceivedDate()));

            if (supplierBill.getPaidDate() != null) {
                supplierOrderDetailsMenu.txtBillPaid.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierBill.getPaidDate()));
            } else {
                supplierOrderDetailsMenu.txtBillPaid.setText("<Click here to mark Bill as Paid>");
            }

        } else {
            if (supplierOrderDetailsMenu.getSupplierOrder().getSendDate() == null) {
                supplierOrderDetailsMenu.txtBillPaid.setText("");
                supplierOrderDetailsMenu.txtBillReceived.setText("");
            } else {
                supplierOrderDetailsMenu.txtBillPaid.setText("");
                supplierOrderDetailsMenu.txtBillReceived.setText("<Click here when Billed>");
            }
        }

        supplierOrderDetailsMenu.txtTotal.setText(String.valueOf(supplierOrderDetailsMenu.getSupplierOrder().getTotal()) + " €");
        supplierOrderDetailsMenu.txtDescription.setText(supplierOrderDetailsMenu.getSupplierOrder().getDescription());

        loadArticlesTable(supplierOrderDetailsMenu);
    }

    private void loadArticlesTable(SupplierOrderDetails supplierOrderDetailsMenu) {
        supplierOrderDetailsMenu.model = new DefaultTableModel(
                supplierOrderDetailsMenu.getSupplierOrderDetailController().loadTableRows(supplierOrderDetailsMenu.getSupplierOrder().getIdSupplierOrder()),
                new String[]{
            "ID",
            "EAN",
            "Name",
            "Price",
            "Quantity",
            "Discount",
            "Total"
        }) {
            private static final long serialVersionUID = 1L;
            private static final String REGEX_INTEGER_NUMBER = "^[0-9]*$";
            private static final String REGEX_FLOAT_NUMBER = "^[0-9]*|[0-9]*.[0-9]{1,2}|[0-9]*,[0-9]{1,2}$";

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column > 3 && column < 6) {
                    return true;
                }
                return false;
            }

            ;
					
                                        @Override
            public void setValueAt(Object aValue, int row, int column) {
                String errors = "";
                if (column == 4) {
                    errors += checkField(String.valueOf(aValue), REGEX_INTEGER_NUMBER);
                }
                if (column == 5) {
                    errors += checkField(String.valueOf(aValue), REGEX_FLOAT_NUMBER);
                }

                if (errors.equals("")) {
                    super.setValueAt(aValue, row, column);
                } else {
                    JOptionPane.showMessageDialog(null, errors);
                }
            }

            private String checkField(String value, String regex) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);
                if (!matcher.matches()) {
                    return "Value inserted with errors!\nPlease enter a valid value.";
                }
                return "";
            }
        };

        supplierOrderDetailsMenu.tblArticles.setModel(supplierOrderDetailsMenu.model);
        supplierOrderDetailsMenu.tblArticles.removeColumn(supplierOrderDetailsMenu.tblArticles.getColumn("ID"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void btnSaveEdit(SupplierOrderDetails supplierOrderDetailsMenu) {
        try {
            supplierOrderDetailsMenu.getSupplierOrder().setSupplier(supplierOrderDetailsMenu.getSupplier());
            supplierOrderDetailsMenu.getSupplierOrder().setEmployee(EmployeeSession.getEmployee());

            supplierOrderDetailsMenu.getSupplierOrder().setDescription(supplierOrderDetailsMenu.txtDescription.getText());

            supplierOrderDetailsMenu.getSupplierOrder().setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
            supplierOrderDetailsMenu.getSupplierOrder().setLastModifiedDate(new Date());

            supplierOrderDetailsMenu.getSupplierOrderDetailController().deleteAllArticles(supplierOrderDetailsMenu.getSupplierOrder().getIdSupplierOrder());

            Set<SupplierOrderDetail> supplierOrderArticles = supplierOrderDetailsMenu.getSupplierOrderDetailController().doUpdateOrderArticle(extractObject(supplierOrderDetailsMenu.tblArticles), supplierOrderDetailsMenu.getSupplierOrder().getIdSupplierOrder());

            supplierOrderDetailsMenu.getSupplierOrder().setSupplierOrderDetail(supplierOrderArticles);

            if (supplierOrderDetailsMenu.getSupplierOrder().getSupplierBill() != null) {
                SupplierBill supplierBill = supplierOrderDetailsMenu.getSupplierOrderController().getSupplierBillById(supplierOrderDetailsMenu.getSupplierOrder().getSupplierBill().getIdSupplierBill());
                supplierOrderDetailsMenu.getSupplierOrder().setSupplierBill(supplierBill);
            }
            supplierOrderDetailsMenu.setSupplierOrder(supplierOrderDetailsMenu.getSupplierOrderController().updateSupplierOrder(supplierOrderDetailsMenu.getSupplierOrder()));

            supplierOrderDetailsMenu.getSupplierOrderManagement().loadTable();

            supplierOrderDetailsMenu.setShowMode();

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(supplierOrderDetailsMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void btnCancel(SupplierOrderDetails supplierOrderDetailsMenu) {
        supplierOrderDetailsMenu.setShowMode();
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
