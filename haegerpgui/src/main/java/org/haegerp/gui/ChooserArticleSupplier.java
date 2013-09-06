package org.haegerp.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.haegerp.entity.SupplierOrder;
import org.haegerp.entity.SupplierOrderDetailPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChooserArticleSupplier extends JFrame {

    private static final long serialVersionUID = 1L;
    @Autowired
    private SupplierOrderDetails supplierOrderDetails;
    private DefaultTableModel model;

    public ChooserArticleSupplier() {
    }
    
    public void showChooserArticleSupplier() {
        loadTable();
        this.setVisible(true);
    }

    protected void tblObjects_MouseClick(MouseEvent e) {

        int idxRow = tblObjects.getSelectedRow();
        if (idxRow >= 0) {
            long idArticle = (Long) model.getValueAt(idxRow, 0);

            SupplierOrder supplierOrder = supplierOrderDetails.getSupplierOrder();
            long idArticleHistory = supplierOrderDetails.getArticleHistoryController().getLastVersion(idArticle);

            Article article = supplierOrderDetails.getArticleController().getArticleById(idArticle);
            ArticleHistoryPK articleHistoryPK = new ArticleHistoryPK(idArticleHistory, article);
            ArticleHistory articleHistory = supplierOrderDetails.getArticleHistoryController().getArticleHistoryById(articleHistoryPK);

            SupplierOrderDetailPK detailPK = new SupplierOrderDetailPK(supplierOrder, articleHistory);

            this.setVisible(false);

            for (int i = 0; i < supplierOrderDetails.model.getRowCount(); i++) {
                SupplierOrderDetailPK tblDetailPK = (SupplierOrderDetailPK) supplierOrderDetails.model.getValueAt(i, 0);

                if (tblDetailPK.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == detailPK.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle()
                        && tblDetailPK.getArticleHistory().getArticleHistoryPK().getIdArticleHistory() == detailPK.getArticleHistory().getArticleHistoryPK().getIdArticleHistory()) {
                    long quantity = Long.parseLong(String.valueOf(supplierOrderDetails.model.getValueAt(i, 4)));
                    supplierOrderDetails.model.setValueAt(quantity + 1, i, 4);
                    return;
                }
            }

            Object[] row = {
                detailPK,
                articleHistory.getEan(),
                articleHistory.getName(),
                articleHistory.getPriceSupplier() + " €",
                "1",
                "0",
                articleHistory.getPriceSupplier() + " €"
            };

            supplierOrderDetails.model.addRow(row);

        }
        
        tblObjects.setModel(model);

        tblObjects.removeColumn(tblObjects.getColumn("ID"));
    }
    
    public void loadTable() {
        model = new javax.swing.table.DefaultTableModel(
                supplierOrderDetails.getArticleController().loadAllTableRows(),
                new String[]{
            "ID",
            "EAN",
            "Name",
            "Category"
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

    @PostConstruct
    public void setUp() {
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblObjects = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setTitle("Article - Chooser");

        lblSearch.setText("Search");

        tblObjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        loadTable();

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
