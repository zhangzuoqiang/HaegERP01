package org.haegerp.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.haegerp.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * In diesem Formular kann den Benutzer den Lieferanten für eine Lieferantsbestellung auswählen
 * 
 * @author Fabio Codinha
 */
@Component
public class ChooserSupplier extends JFrame {

    private static final long serialVersionUID = 1L;
    //Formular
    @Autowired
    private SupplierOrderDetails supplierOrderDetails;
    
    //Inhalt von der Tabelle
    private DefaultTableModel model;

    public ChooserSupplier() {
    }

    /**
     * Das Formular wird vorbereitet und gezeigt
     */
    public void showChooserSupplier() {
        loadTable();
        this.setVisible(true);
    }

    /**
     * Lieferant, den der Benutzer ausgewählt hat
     * 
     * @param e MouseEvent Werte
     */
    protected void tblObjects_MouseClick(MouseEvent e) {

        int idxRow = tblObjects.getSelectedRow();
        if (idxRow >= 0) {
            long idSupplier = (Long) model.getValueAt(idxRow, 0);
            Supplier supplier = supplierOrderDetails.getSupplierController().getSupplierById(idSupplier);
            supplierOrderDetails.setSupplier(supplier);

            supplierOrderDetails.txtSupplier.setText(supplier.getName());

            this.setVisible(false);
        }
    }
    
    /**
     * Die Tabelle wird ausgefüllt
     */
    private void loadTable() {
        Object[][] content;
        if (txtSearch.getText().equals("")) {
            content = supplierOrderDetails.getSupplierController().loadAllTableRows(0, "", 1);
        } else {
            content = supplierOrderDetails.getSupplierController().loadAllTableRows(1, txtSearch.getText(), 0);
        }
        
        model = new javax.swing.table.DefaultTableModel(
                    content,
                    new String[]{
                "ID",
                "TaxID",
                "Name",
                "E-Mail",
                "City"
            }) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
        
        tblObjects.setModel(model);

        tblObjects.removeColumn(tblObjects.getColumn("ID"));
    }

    /**
     * Das Formular wird vorbereitet
     */
    @PostConstruct
    public void setUp() {
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblObjects = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Supplier - Chooser");

        lblSearch.setText("Search");

        tblObjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        loadTable();
        
        txtSearch.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                loadTable();
            }
        });

        tblObjects.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() > 1) {
                    tblObjects_MouseClick(e);
                }
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }
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
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSearch)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();

    }
    
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tblObjects;
    private javax.swing.JTextField txtSearch;
}
