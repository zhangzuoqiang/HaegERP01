package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

import org.haegerp.controller.ClientCategoryController;
import org.haegerp.entity.ClientCategory;
import org.haegerp.gui.clientcategorydetails.ClientCategoryDetailsInterface;
import org.haegerp.gui.clientcategorydetails.ClientCategoryEditView;
import org.haegerp.gui.clientcategorydetails.ClientCategoryNewView;
import org.haegerp.gui.clientcategorydetails.ClientCategoryShowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * Dieses Formular wird die Details einer Kundenkategorie kontrollieren
 *
 * @author Fabio Codinha
 */
public class ClientCategoryDetails extends javax.swing.JFrame {

    private static final long serialVersionUID = 2949647041784163844L;
    //Formular
    @Autowired
    private ClientCategoryManagement clientCategoryManagement;

    public ClientCategoryManagement getClientCategoryManagement() {
        return clientCategoryManagement;
    }
    @Autowired
    private ClientManagement clientManagement;

    public ClientManagement getClientManagement() {
        return clientManagement;
    }
    @Autowired
    private ClientDetails clientDetails;

    public ClientDetails getClientDetails() {
        return clientDetails;
    }
    //Controller
    @Autowired
    private ClientCategoryController clientCategoryController;

    public ClientCategoryController getClientCategoryController() {
        return clientCategoryController;
    }
    //Verschiednen Stände von der Oberfläche
    private ClientCategoryDetailsInterface clientCategoryDetailsView;
    //Kundenkategorie, die gezeigt wird
    private ClientCategory clientCategory;

    public ClientCategory getClientCategory() {
        return clientCategory;
    }

    public void setClientCategory(ClientCategory clientCategory) {
        this.clientCategory = clientCategory;
    }

    public ClientCategoryDetails() {
    }

    //Wenn der Benutzer eine neue Kundenkategorie erstellen möchte
    public void setNewMode() {
        clientCategoryDetailsView = new ClientCategoryNewView();
        clientCategoryDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Kundenkategorie ändern möchte
    public void setEditMode() {
        clientCategoryDetailsView = new ClientCategoryEditView();
        clientCategoryDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Kundenkategorie ansehen möchte
    public void setShowMode() {
        clientCategoryDetailsView = new ClientCategoryShowView();
        clientCategoryDetailsView.applyView(this);
    }

    /**
     * Die Felder werden validiert
     *
     * @return Wenn das String leer ist, würde kein Fehler gefunden
     */
    private String checkFields() {
        String errors = "";
        if (txtName.getText().equals("")) {
            errors += "Name\n";
        }

        return errors;
    }

    //Listeners
    /**
     * Wenn der Benutzer nur ansehen kann, wechselt der Knopf zur
     * Änderungsmodus.<br/>
     * Wenn der Benutzer die Felder ändern kann, wird die Kundenkategorie
     * gespeichert.<br/>
     * Diese Betrieb wird bei der Variable 'clientCategoryDetailsView'
     * kontrolliert.
     *
     * @param e ActionEvent Werte
     */
    protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
        String errors = "";
        if (!(clientCategoryDetailsView instanceof ClientCategoryShowView)) {
            errors = checkFields();
        }
        if (errors.equals("")) {
            clientCategoryDetailsView.btnSaveEdit(this);
        } else {
            JOptionPane.showMessageDialog(this, "The following fields have not been filled:\n" + errors + "\nThose fields are required.", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Wenn der Benutzer nicht die Änderungen speichern möchte, wird die
     * Kundenkategorie wieder zum Formular geladen
     *
     * @param e ActionEvent Werte
     */
    protected void btnCancel_ActionPerformed(ActionEvent e) {
        clientCategoryDetailsView.btnCancel(this);
    }

    /**
     * Das Formular wird vorbereitet
     */
    @PostConstruct
    private void setUp() {
        lblCountClients = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();

        txtCountClients = new javax.swing.JFormattedTextField();
        txtName = new javax.swing.JTextField();
        txtName.setDocument(new JTextFieldLimit(50));

        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtDescription.setDocument(new JTextFieldLimit(256));

        btnSaveEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        pnlLeft = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Article Category Details");
        setMinimumSize(new java.awt.Dimension(300, 320));
        setPreferredSize(new Dimension(300, 320));

        lblCountClients.setText("N. Clients");

        lblName.setText("Name");

        lblDescription.setText("Description");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        btnSaveEdit.setText("Save");
        btnSaveEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSaveEdit_ActionPerformed(e);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancel_ActionPerformed(e);
            }
        });

        txtCountClients.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
                pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 30, Short.MAX_VALUE));
        pnlLeftLayout.setVerticalGroup(
                pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 510, Short.MAX_VALUE));

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
                pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 30, Short.MAX_VALUE));
        pnlRightLayout.setVerticalGroup(
                pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 510, Short.MAX_VALUE));

        pnlCenter.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
                pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2)
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(lblDescription)
                .addComponent(lblName)
                .addComponent(lblCountClients))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(txtCountClients, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(btnSaveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));
        pnlCenterLayout.setVerticalGroup(
                pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblName)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCountClients)
                .addComponent(txtCountClients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnCancel)
                .addComponent(btnSaveEdit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCenter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
                .addComponent(pnlRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - getWidth()) / 2, (dim.height - getHeight()) / 2);
    }
    public javax.swing.JButton btnCancel;
    public javax.swing.JButton btnSaveEdit;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblCountClients;
    private javax.swing.JLabel lblName;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea txtDescription;
    public javax.swing.JFormattedTextField txtCountClients;
    public javax.swing.JTextField txtName;
    public javax.swing.JPanel pnlLeft;
    public javax.swing.JPanel pnlCenter;
    public javax.swing.JPanel pnlRight;
}
