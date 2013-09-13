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
import org.haegerp.entity.ClientOffer;
import org.haegerp.entity.ClientOfferDetailPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChooserArticleClient extends JFrame {

    private static final long serialVersionUID = 1L;
    @Autowired
    private ClientOfferDetails clientOfferDetails;
    private DefaultTableModel model;

    public ChooserArticleClient() {
    }

    public void showChooserArticleClient() {
        loadTable();
        this.setVisible(true);
    }

    protected void tblObjects_MouseClick(MouseEvent e) {

        int idxRow = tblObjects.getSelectedRow();
        if (idxRow >= 0) {
            long idArticle = (Long) model.getValueAt(idxRow, 0);

            ClientOffer clientOffer = clientOfferDetails.getClientOffer();
            long idArticleHistory = clientOfferDetails.getArticleHistoryController().getLastVersion(idArticle);

            Article article = clientOfferDetails.getArticleController().getArticleById(idArticle);
            ArticleHistoryPK articleHistoryPK = new ArticleHistoryPK(idArticleHistory, article);
            ArticleHistory articleHistory = clientOfferDetails.getArticleHistoryController().getArticleHistoryById(articleHistoryPK);

            ClientOfferDetailPK detailPK = new ClientOfferDetailPK(clientOffer, articleHistory);

            this.setVisible(false);

            for (int i = 0; i < clientOfferDetails.model.getRowCount(); i++) {
                ClientOfferDetailPK tblDetailPK = (ClientOfferDetailPK) clientOfferDetails.model.getValueAt(i, 0);

                if (tblDetailPK.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == detailPK.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle()
                        && tblDetailPK.getArticleHistory().getArticleHistoryPK().getIdArticleHistory() == detailPK.getArticleHistory().getArticleHistoryPK().getIdArticleHistory()) {
                    long quantity = Long.parseLong(String.valueOf(clientOfferDetails.model.getValueAt(i, 5)));
                    clientOfferDetails.model.setValueAt(quantity + 1, i, 5);
                    return;
                }
            }

            Object[] row = {
                detailPK,
                articleHistory.getEan(),
                articleHistory.getName(),
                articleHistory.getPriceGross() + " €",
                (Math.floor(articleHistory.getPriceVat() * 10000) / 100) + " %",
                "1",
                "0",
                (articleHistory.getPriceGross() * (1 + articleHistory.getPriceVat())) + " €"
            };

            clientOfferDetails.model.addRow(row);

        }
    }

    public void loadTable() {
        Object[][] content;
        if (txtSearch.getText().equals("")) {
            content = clientOfferDetails.getArticleController().loadAllTableRows(0, "", 1);
        } else {
            content = clientOfferDetails.getArticleController().loadAllTableRows(1, txtSearch.getText(), 0);
        }

        model = new javax.swing.table.DefaultTableModel(
                content,
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
