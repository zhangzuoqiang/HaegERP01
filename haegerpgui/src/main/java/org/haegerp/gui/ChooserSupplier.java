package org.haegerp.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.haegerp.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChooserSupplier extends JFrame {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SupplierOrderDetails supplierOrderDetails;
	
	private DefaultTableModel model;
	
	public ChooserSupplier() {
	}
	
	public void showChooserSupplier(){
		loadTable();
		this.setVisible(true);
	}
	
	protected void tblObjects_MouseClick(MouseEvent e) {
		
		int idxRow = tblObjects.getSelectedRow();
		if (idxRow >= 0)
		{
			long idSupplier = (Long)model.getValueAt(idxRow, 0);
			Supplier supplier = supplierOrderDetails.getSupplierController().getSupplierById(idSupplier);
			supplierOrderDetails.setSupplier(supplier);
			
			supplierOrderDetails.txtSupplier.setText(supplier.getName());
			
			this.setVisible(false);
		}
	}
	
	@PostConstruct
	public void setUp(){
		lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblObjects = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblSearch.setText("Search");
        
        tblObjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        loadTable();
        
        tblObjects.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() > 1)
					tblObjects_MouseClick(e);
			}
			
			public void mouseExited(MouseEvent e) { }
			
			public void mouseEntered(MouseEvent e) { }
			
			public void mouseClicked(MouseEvent e) { }
		});
        
        jScrollPane1.setViewportView(tblObjects);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 143, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        
	}
	
	private void loadTable() {
		model = new javax.swing.table.DefaultTableModel(
	            supplierOrderDetails.getSupplierController().loadAllTableRows(),
	            new String [] {
	            	"ID",
	                "TaxID",
	                "Name",
	                "E-Mail",
	                "City"
	            }
        ) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        
        tblObjects.setModel(model);
        
        tblObjects.removeColumn(tblObjects.getColumn("ID"));
	}

	private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tblObjects;
    private javax.swing.JTextField txtSearch;
}
