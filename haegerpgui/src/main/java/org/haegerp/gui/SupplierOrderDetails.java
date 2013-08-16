package org.haegerp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.haegerp.controller.ArticleController;
import org.haegerp.controller.ArticleHistoryController;
import org.haegerp.controller.SupplierController;
import org.haegerp.controller.SupplierOrderController;
import org.haegerp.controller.SupplierOrderDetailController;
import org.haegerp.entity.Supplier;
import org.haegerp.entity.SupplierBill;
import org.haegerp.entity.SupplierOrder;
import org.haegerp.gui.supplierorderdetails.SupplierOrderDetailsInterface;
import org.haegerp.gui.supplierorderdetails.SupplierOrderEditView;
import org.haegerp.gui.supplierorderdetails.SupplierOrderNewView;
import org.haegerp.gui.supplierorderdetails.SupplierOrderShowView;
import org.haegerp.session.EmployeeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * 
 * @author Wolf
 */
public class SupplierOrderDetails extends javax.swing.JFrame {

	private static final long serialVersionUID = 2949647041784163844L;

	@Autowired
	private SupplierOrderController supplierOrderController;

	public SupplierOrderController getSupplierOrderController() {
		return supplierOrderController;
	}

	@Autowired
	private SupplierOrderDetailController supplierOrderDetailController;

	public SupplierOrderDetailController getSupplierOrderDetailController() {
		return supplierOrderDetailController;
	}

	@Autowired
	private ArticleController articleController;

	public ArticleController getArticleController() {
		return articleController;
	}

	@Autowired
	private ArticleHistoryController articleHistoryController;

	public ArticleHistoryController getArticleHistoryController() {
		return articleHistoryController;
	}

	@Autowired
	private SupplierController supplierController;

	public SupplierController getSupplierController() {
		return supplierController;
	}

	@Autowired
	private SupplierOrderManagement supplierOrderManagement;

	public SupplierOrderManagement getSupplierOrderManagement() {
		return supplierOrderManagement;
	}

	@Autowired
	private ChooserArticleSupplier chooserArticleSupplier;

	@Autowired
	private ChooserSupplier chooserSupplier;

	public DefaultTableModel model;

	private SupplierOrderDetailsInterface supplierOrderDetailsView;

	private Supplier supplier = null;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	private SupplierOrder supplierOrder;

	public SupplierOrder getSupplierOrder() {
		return supplierOrder;
	}

	public void setSupplierOrder(SupplierOrder supplierOrder) {
		this.supplierOrder = supplierOrder;
	}

	protected void btnAddArticle_ActionPerformed(ActionEvent e) {
		chooserArticleSupplier.setVisible(true);
	}

	public SupplierOrderDetails() {
	}

	public void setNewMode() {
		supplierOrderDetailsView = new SupplierOrderNewView();
		supplierOrderDetailsView.applyView(this);
	}

	public void setEditMode() {
		supplierOrderDetailsView = new SupplierOrderEditView();
		supplierOrderDetailsView.applyView(this);
	}

	public void setShowMode() {
		supplierOrderDetailsView = new SupplierOrderShowView();
		supplierOrderDetailsView.applyView(this);
	}

	// Listeners
	protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
		String errors = "";
		if (!(supplierOrderDetailsView instanceof SupplierOrderShowView)) {
			errors = checkFields();
		}
		if (errors.equals(""))
			supplierOrderDetailsView.btnSaveEdit(this);
		else
			JOptionPane.showMessageDialog(this, errors, "",
					JOptionPane.ERROR_MESSAGE);
	}

	private String checkFields() {
		if (supplier == null)
			return "Please select a supplier to which the order will be sent.";
		return "";
	}

	protected void btnCancel_ActionPerformed(ActionEvent e) {
		supplierOrderDetailsView.btnCancel(this);
	}

	protected void btnDeleteArticle_ActionPerformed(ActionEvent e) {
		if (tblArticles.getSelectedRow() >= 0) {
			model.removeRow(tblArticles.getSelectedRow());
		}
	}

	protected void txtSupplier_MouseClicked(MouseEvent e) {
		if (!(supplierOrderDetailsView instanceof SupplierOrderShowView)
				&& supplierOrder.getSendDate() == null) {
			chooserSupplier.showChooserSupplier();
		}
	}

	protected void txtSendDate_MouseClicked(MouseEvent e) {
		if (supplierOrderDetailsView instanceof SupplierOrderEditView
				&& supplierOrder.getSendDate() == null) {
			int option = JOptionPane
					.showConfirmDialog(
							this,
							"Are you sure of marking the order as Sent?\nAfter the Order is marked as \"Sent\", the supplier cannot be changed anymore.",
							"Send Order", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				Date date = new Date();
				txtSendDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm")
						.format(date));
				supplierOrder.setSendDate(date);
				supplierOrderDetailsView.btnSaveEdit(this);
			}
		}
	}
	
	protected void txtBillReceived_MouseClicked(MouseEvent e) {
		if (supplierOrderDetailsView instanceof SupplierOrderEditView
				&& supplierOrder.getSupplierBill() == null
				&& supplierOrder.getSendDate() != null) {
			int option = JOptionPane
					.showConfirmDialog(
							this,
							"Are you sure of marking the order as Billed?\nAfter the Order is marked as \"Billed\", the order cannot be changed anymore.",
							"Close Order", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				Date date = new Date();
				txtBillReceived.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm")
						.format(date));
				SupplierBill supplierBill = new SupplierBill();
				supplierBill.setReceivedDate(date);
				supplierBill.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
				supplierBill.setLastModifiedDate(new Date());
				supplierOrder.setSupplierBill(supplierOrderController.saveBill(supplierBill));
				supplierOrderDetailsView.btnSaveEdit(this);
			}
		}
	}

	protected void txtBillPaid_MouseClicked(MouseEvent e) {
		if (supplierOrderDetailsView instanceof SupplierOrderEditView
				&& supplierOrder.getSupplierBill() != null
				&& supplierOrder.getSupplierBill().getPaidDate() == null) {
			int option = JOptionPane
					.showConfirmDialog(
							this,
							"Are you sure of marking the order as Billed?\nAfter the Order is marked as \"Billed\", the order cannot be changed anymore.",
							"Close Order", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				Date date = new Date();
				txtBillPaid.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm")
						.format(date));
				SupplierBill supplierBill = supplierOrderController.getSupplierBillById(supplierOrder.getSupplierBill().getIdSupplierBill());
				supplierBill.setPaidDate(date);
				supplierBill.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
				supplierBill.setLastModifiedDate(new Date());
				supplierOrder.setSupplierBill(supplierOrderController.saveBill(supplierBill));
				supplierOrderDetailsView.btnSaveEdit(this);
			}
		}
	}

	@PostConstruct
	private void setUp() {
		pnlInfo = new javax.swing.JPanel();
		lblSupplier = new javax.swing.JLabel();
		lblOrderDate = new javax.swing.JLabel();
		txtOrderDate = new javax.swing.JTextField();
		txtSupplier = new javax.swing.JTextField();
		lblSendDate = new javax.swing.JLabel();
		txtSendDate = new javax.swing.JTextField();
		lblDescription = new javax.swing.JLabel();
		pnlTxtDescription = new javax.swing.JScrollPane();
		txtDescription = new javax.swing.JTextArea();
		txtDescription.setDocument(new JTextFieldLimit(256));
		lblBillPaid = new javax.swing.JLabel();
		lblBillReceived = new javax.swing.JLabel();
		txtBillReceived = new javax.swing.JTextField();
		lblTotal = new javax.swing.JLabel();
		txtBillPaid = new javax.swing.JTextField();
		txtTotal = new javax.swing.JTextField();
		pnlTblArticles = new javax.swing.JScrollPane();
		tblArticles = new javax.swing.JTable();
		btnSaveEdit = new javax.swing.JButton();
		btnCancel = new javax.swing.JButton();
		btnAddArticle = new javax.swing.JButton();
		btnDeleteArticle = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Supplier Order Detail");

		pnlInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		lblSupplier.setText("Supplier Name");

		lblOrderDate.setText("Order Date");

		txtOrderDate.setEditable(false);

		txtSupplier.setEditable(false);
		txtSupplier.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				txtSupplier_MouseClicked(e);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});

		lblSendDate.setText("Send Date");

		txtSendDate.setEditable(false);
		txtSendDate.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				txtSendDate_MouseClicked(e);
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});

		lblDescription.setText("Description");

		txtDescription.setColumns(20);
		txtDescription.setLineWrap(true);
		txtDescription.setRows(2);
		pnlTxtDescription.setViewportView(txtDescription);

		lblBillPaid.setText("Bill Paid");

		lblBillReceived.setText("Bill Received");

		txtBillReceived.setEditable(false);
		txtBillReceived.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				txtBillReceived_MouseClicked(e);
			}
			
			public void mousePressed(MouseEvent e) { }
			
			public void mouseExited(MouseEvent e) { }
			
			public void mouseEntered(MouseEvent e) { }
			
			public void mouseClicked(MouseEvent e) { }
		});
		
		lblTotal.setText("Total");

		txtBillPaid.setEditable(false);
		txtBillPaid.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				txtBillPaid_MouseClicked(e);
			}
			
			public void mousePressed(MouseEvent e) { }
			
			public void mouseExited(MouseEvent e) { }
			
			public void mouseEntered(MouseEvent e) { }
			
			public void mouseClicked(MouseEvent e) { }
		});

		txtTotal.setEditable(false);

		pnlTblArticles.setViewportView(tblArticles);

		btnSaveEdit.setText("Edit");
		btnSaveEdit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnSaveEdit_ActionPerformed(e);
			}
		});

		btnCancel.setText("Exit");
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnCancel_ActionPerformed(e);
			}
		});

		btnAddArticle.setText("Add");
		btnAddArticle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnAddArticle_ActionPerformed(e);
			}
		});

		btnDeleteArticle.setText("Delete");
		btnDeleteArticle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnDeleteArticle_ActionPerformed(e);
			}
		});

		javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(
				pnlInfo);
		pnlInfoLayout.setHorizontalGroup(pnlInfoLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				pnlInfoLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								pnlInfoLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSupplier)
										.addComponent(lblOrderDate)
										.addComponent(lblSendDate))
						.addGap(8)
						.addGroup(
								pnlInfoLayout
										.createParallelGroup(Alignment.LEADING,
												false)
										.addComponent(txtOrderDate,
												GroupLayout.DEFAULT_SIZE, 180,
												Short.MAX_VALUE)
										.addComponent(txtSupplier,
												Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 180,
												Short.MAX_VALUE)
										.addComponent(txtSendDate,
												Alignment.TRAILING))
						.addGap(18)
						.addGroup(
								pnlInfoLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lblBillReceived)
										.addComponent(lblBillPaid)
										.addComponent(lblTotal))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								pnlInfoLayout
										.createParallelGroup(Alignment.LEADING,
												false)
										.addComponent(txtBillReceived,
												GroupLayout.DEFAULT_SIZE, 180,
												Short.MAX_VALUE)
										.addComponent(txtBillPaid)
										.addComponent(txtTotal))
						.addGap(18)
						.addGroup(
								pnlInfoLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDescription)
										.addComponent(pnlTxtDescription,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		pnlInfoLayout
				.setVerticalGroup(pnlInfoLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								pnlInfoLayout
										.createSequentialGroup()
										.addGroup(
												pnlInfoLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																pnlInfoLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblDescription)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				pnlTxtDescription,
																				GroupLayout.PREFERRED_SIZE,
																				54,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																pnlInfoLayout
																		.createSequentialGroup()
																		.addGap(13)
																		.addGroup(
																				pnlInfoLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblSupplier)
																						.addComponent(
																								txtSupplier,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblBillReceived)
																						.addComponent(
																								txtBillReceived,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				pnlInfoLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblOrderDate)
																						.addComponent(
																								txtOrderDate,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblBillPaid)
																						.addComponent(
																								txtBillPaid,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				pnlInfoLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								txtSendDate,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblSendDate)
																						.addComponent(
																								lblTotal)
																						.addComponent(
																								txtTotal,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		pnlInfo.setLayout(pnlInfoLayout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(pnlTblArticles)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		btnSaveEdit,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		75,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		btnCancel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		75,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														pnlInfo,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		btnAddArticle,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		75,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		btnDeleteArticle,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		75,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0,
																		0,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(pnlInfo,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(btnDeleteArticle)
												.addComponent(btnAddArticle))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(pnlTblArticles,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										360, Short.MAX_VALUE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnSaveEdit)
												.addComponent(btnCancel))
								.addContainerGap()));

		pack();
		setLocationRelativeTo(null);
	}
	
	public javax.swing.JButton btnAddArticle;
	public javax.swing.JButton btnCancel;
	public javax.swing.JButton btnDeleteArticle;
	public javax.swing.JButton btnSaveEdit;
	public javax.swing.JLabel lblBillPaid;
	public javax.swing.JLabel lblBillReceived;
	public javax.swing.JLabel lblDescription;
	public javax.swing.JLabel lblOrderDate;
	public javax.swing.JLabel lblSendDate;
	public javax.swing.JLabel lblSupplier;
	public javax.swing.JLabel lblTotal;
	public javax.swing.JPanel pnlInfo;
	public javax.swing.JScrollPane pnlTblArticles;
	public javax.swing.JScrollPane pnlTxtDescription;
	public javax.swing.JTable tblArticles;
	public javax.swing.JTextField txtBillPaid;
	public javax.swing.JTextField txtBillReceived;
	public javax.swing.JTextArea txtDescription;
	public javax.swing.JTextField txtOrderDate;
	public javax.swing.JTextField txtSendDate;
	public javax.swing.JTextField txtSupplier;
	public javax.swing.JTextField txtTotal;
	
	@Override
	public void dispose() {
		chooserArticleSupplier.setVisible(false);
		chooserSupplier.setVisible(false);
		super.dispose();
	}
}
