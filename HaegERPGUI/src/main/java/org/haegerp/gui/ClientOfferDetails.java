package org.haegerp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import org.haegerp.service.ArticleService;
import org.haegerp.service.ArticleHistoryService;
import org.haegerp.service.ClientService;
import org.haegerp.service.ClientOfferService;
import org.haegerp.service.ClientOfferDetailService;
import org.haegerp.entity.Client;
import org.haegerp.entity.ClientBill;
import org.haegerp.entity.ClientOffer;
import org.haegerp.gui.clientofferdetails.ClientOfferDetailsInterface;
import org.haegerp.gui.clientofferdetails.ClientOfferEditView;
import org.haegerp.gui.clientofferdetails.ClientOfferNewView;
import org.haegerp.gui.clientofferdetails.ClientOfferShowView;
import org.haegerp.gui.supplierorderdetails.SupplierOrderEditView;
import org.haegerp.gui.supplierorderdetails.SupplierOrderShowView;
import org.haegerp.session.EmployeeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * Dieses Formular wird die Details einer Kundenbestellung kontrollieren
 *
 * @author Fabio Codinha
 */
public class ClientOfferDetails extends javax.swing.JFrame {

    private static final long serialVersionUID = 2949647041784163844L;
    //Controller
    @Autowired
    private ClientOfferService clientOfferController;

    public ClientOfferService getClientOfferController() {
        return clientOfferController;
    }
    @Autowired
    private ClientOfferDetailService clientOfferDetailController;

    public ClientOfferDetailService getClientOfferDetailController() {
        return clientOfferDetailController;
    }
    @Autowired
    private ArticleService articleController;

    public ArticleService getArticleController() {
        return articleController;
    }
    @Autowired
    private ArticleHistoryService articleHistoryController;

    public ArticleHistoryService getArticleHistoryController() {
        return articleHistoryController;
    }
    @Autowired
    private ClientService clientController;

    public ClientService getClientController() {
        return clientController;
    }
    //Formulare
    @Autowired
    private ClientOfferManagement clientOfferManagement;

    public ClientOfferManagement getClientOfferManagement() {
        return clientOfferManagement;
    }
    //Das ist, wo der Benutzer den Artikel auswählen kann
    @Autowired
    private ChooserArticleClient chooserArticleClient;
    //Das ist, wo der Benutzer den Kunden auswählen kann
    @Autowired
    private ChooserClient chooserClient;
    //Inhalt der Tabelle
    public DefaultTableModel model;
    //Verschiednen Stände von der Oberfläche
    private ClientOfferDetailsInterface clientOfferDetailsView;
    //Ausgewählt Kunde
    private Client client = null;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    //Kundenbestellung, die gezeigt wird
    private ClientOffer clientOffer;

    public ClientOffer getClientOffer() {
        return clientOffer;
    }

    public void setClientOffer(ClientOffer clientOffer) {
        this.clientOffer = clientOffer;
    }

    public ClientOfferDetails() {
    }

    //Wenn der Benutzer eine neue Kundenbestellung erstellen möchte
    public void setNewMode() {
        clientOfferDetailsView = new ClientOfferNewView();
        clientOfferDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Kundenbestellung ändern möchte
    public void setEditMode() {
        clientOfferDetailsView = new ClientOfferEditView();
        clientOfferDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Kundenbestellung ansehen möchte
    public void setShowMode() {
        clientOfferDetailsView = new ClientOfferShowView();
        clientOfferDetailsView.applyView(this);
    }

    /**
     * Die Felder werden validiert
     *
     * @return Wenn das String leer ist, würde kein Fehler gefunden
     */
    private String checkFields() {
        if (client == null) {
            return "Please select a supplier to which the order will be sent.";
        }
        return "";
    }

    // Listeners
    /**
     * Wenn der Benutzer nur ansehen kann, wechselt der Knopf zur
     * Änderungsmodus.<br/>
     * Wenn der Benutzer die Felder ändern kann, wird der Artikel
     * gespeichert.<br/>
     * Diese Betrieb wird bei der Variable 'clientOfferDetailsView'
     * kontrolliert.
     *
     * @param e ActionEvent Werte
     */
    protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
        String errors = "";
        if (!(clientOfferDetailsView instanceof SupplierOrderShowView)) {
            errors = checkFields();
        }
        if (errors.equals("")) {
            clientOfferDetailsView.btnSaveEdit(this);
        } else {
            JOptionPane.showMessageDialog(this, errors, "",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Wenn der Benutzer nicht die Änderungen speichern möchte, wird die
     * Kundenbestellung wieder zum Formular geladen
     *
     * @param e ActionEvent Werte
     */
    protected void btnCancel_ActionPerformed(ActionEvent e) {
        clientOfferDetailsView.btnCancel(this);
    }

    /**
     * Wenn der Benutzer einen Artikel zur Kundenbestellung einfügen möchte.
     *
     * @param e ActionEvent Werte
     */
    protected void btnAddArticle_ActionPerformed(ActionEvent e) {
        chooserArticleClient.showChooserArticleClient();
    }

    /**
     * Wenn der Benutzer einen Artikel von der Kundenbestellung löschten möchte
     *
     * @param e ActionEvent Werte
     */
    protected void btnDeleteArticle_ActionPerformed(ActionEvent e) {
        if (tblArticles.getSelectedRow() >= 0) {
            model.removeRow(tblArticles.getSelectedRow());
        }
    }

    /**
     * Ein Kunde wird beim Benutzer ausgewählt
     *
     * @param e MouseEvent Werte
     */
    protected void txtClient_MouseClicked(MouseEvent e) {
        if (!(clientOfferDetailsView instanceof SupplierOrderShowView)
                && clientOffer.getSendDate() == null) {
            chooserClient.showChooserClient();
        }
    }

    /**
     * Die Kundenbestellung wird als 'geschickt' markiert
     *
     * @param e MouseEvent Werte
     */
    protected void txtSendDate_MouseClicked(MouseEvent e) {
        if (clientOfferDetailsView instanceof SupplierOrderEditView
                && clientOffer.getSendDate() == null) {
            int option = JOptionPane
                    .showConfirmDialog(
                    this,
                    "Are you sure of marking the offer as Sent?\nAfter the offer is marked as \"Sent\", the client cannot be changed anymore.",
                    "Send Order", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Date date = new Date();
                txtSendDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm")
                        .format(date));
                clientOffer.setSendDate(date);
                clientOfferDetailsView.btnSaveEdit(this);
            }
        }
    }

    /**
     * Die Rechnung wird gemacht
     *
     * @param e MouseEvent Werte
     */
    protected void txtBilled_MouseClicked(MouseEvent e) {
        if (clientOfferDetailsView instanceof SupplierOrderEditView
                && clientOffer.getClientBill() == null
                && clientOffer.getSendDate() != null) {
            int option = JOptionPane
                    .showConfirmDialog(
                    this,
                    "Are you sure of marking the order as Billed?\nAfter the Order is marked as \"Billed\", the order cannot be changed anymore.",
                    "Close Order", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Date date = new Date();
                txtBilled.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm")
                        .format(date));
                ClientBill clientBill = new ClientBill();
                clientBill.setBilledDate(date);
                clientBill.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
                clientBill.setLastModifiedDate(new Date());
                clientOffer.setClientBill(clientOfferController.saveBill(clientBill));
                clientOfferDetailsView.btnSaveEdit(this);
            }
        }
    }

    /**
     * Die Rechnung wurde beim Kunden bezahlt
     *
     * @param e MouseEvent Werte
     */
    protected void txtBillPaid_MouseClicked(MouseEvent e) {
        if (clientOfferDetailsView instanceof SupplierOrderEditView
                && clientOffer.getClientBill() != null
                && clientOffer.getClientBill().getPaidDate() == null) {
            int option = JOptionPane
                    .showConfirmDialog(
                    this,
                    "Are you sure of marking the offer as Billed?\nAfter the offer is marked as \"Billed\", the order cannot be changed anymore.",
                    "Close Order", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Date date = new Date();
                txtBillPaid.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm")
                        .format(date));
                ClientBill clientBill = clientOfferController.getClientBillById(clientOffer.getClientBill().getIdClientBill());
                clientBill.setPaidDate(date);
                clientBill.setIdEmployeeModify(EmployeeSession.getEmployee().getIdEmployee());
                clientBill.setLastModifiedDate(new Date());
                clientOffer.setClientBill(clientOfferController.saveBill(clientBill));
                clientOfferDetailsView.btnSaveEdit(this);
            }
        }
    }

    /**
     * Das Formular wird vorbereitet
     */
    @PostConstruct
    private void setUp() {
        pnlInfo = new javax.swing.JPanel();
        lblClient = new javax.swing.JLabel();
        lblOfferDate = new javax.swing.JLabel();
        txtOfferDate = new javax.swing.JTextField();
        txtClient = new javax.swing.JTextField();
        lblSendDate = new javax.swing.JLabel();
        txtSendDate = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        pnlTxtDescription = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtDescription.setDocument(new JTextFieldLimit(256));
        lblBillPaid = new javax.swing.JLabel();
        lblBilled = new javax.swing.JLabel();
        txtBilled = new javax.swing.JTextField();
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
        setTitle("Client Offer Detail");

        pnlInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblClient.setText("Supplier Name");

        lblOfferDate.setText("Order Date");

        txtOfferDate.setEditable(false);

        txtClient.setEditable(false);
        txtClient.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                txtClient_MouseClicked(e);
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

        lblBilled.setText("Bill Received");

        txtBilled.setEditable(false);
        txtBilled.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                txtBilled_MouseClicked(e);
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

        lblTotal.setText("Total");

        txtBillPaid.setEditable(false);
        txtBillPaid.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                txtBillPaid_MouseClicked(e);
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
                .addComponent(lblClient)
                .addComponent(lblOfferDate)
                .addComponent(lblSendDate))
                .addGap(8)
                .addGroup(
                pnlInfoLayout
                .createParallelGroup(Alignment.LEADING,
                false)
                .addComponent(txtOfferDate,
                GroupLayout.DEFAULT_SIZE, 180,
                Short.MAX_VALUE)
                .addComponent(txtClient,
                Alignment.TRAILING,
                GroupLayout.DEFAULT_SIZE, 180,
                Short.MAX_VALUE)
                .addComponent(txtSendDate,
                Alignment.TRAILING))
                .addGap(18)
                .addGroup(
                pnlInfoLayout
                .createParallelGroup(Alignment.LEADING)
                .addComponent(lblBilled)
                .addComponent(lblBillPaid)
                .addComponent(lblTotal))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(
                pnlInfoLayout
                .createParallelGroup(Alignment.LEADING,
                false)
                .addComponent(txtBilled,
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
                lblClient)
                .addComponent(
                txtClient,
                GroupLayout.PREFERRED_SIZE,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE)
                .addComponent(
                lblBilled)
                .addComponent(
                txtBilled,
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
                lblOfferDate)
                .addComponent(
                txtOfferDate,
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
    public javax.swing.JLabel lblBilled;
    public javax.swing.JLabel lblDescription;
    public javax.swing.JLabel lblOfferDate;
    public javax.swing.JLabel lblSendDate;
    public javax.swing.JLabel lblClient;
    public javax.swing.JLabel lblTotal;
    public javax.swing.JPanel pnlInfo;
    public javax.swing.JScrollPane pnlTblArticles;
    public javax.swing.JScrollPane pnlTxtDescription;
    public javax.swing.JTable tblArticles;
    public javax.swing.JTextField txtBillPaid;
    public javax.swing.JTextField txtBilled;
    public javax.swing.JTextArea txtDescription;
    public javax.swing.JTextField txtOfferDate;
    public javax.swing.JTextField txtSendDate;
    public javax.swing.JTextField txtClient;
    public javax.swing.JTextField txtTotal;

    @Override
    public void dispose() {
        chooserArticleClient.setVisible(false);
        chooserClient.setVisible(false);
        super.dispose();
    }
}
