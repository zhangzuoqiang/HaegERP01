package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

import org.haegerp.controller.SalaryCategoryController;
import org.haegerp.entity.SalaryCategory;
import org.haegerp.gui.salarycategorydetails.SalaryCategoryDetailsInterface;
import org.haegerp.gui.salarycategorydetails.SalaryCategoryEditView;
import org.haegerp.gui.salarycategorydetails.SalaryCategoryNewView;
import org.haegerp.gui.salarycategorydetails.SalaryCategoryShowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * Dieses Formular wird die Details einer Gehaltkategorie kontrollieren
 *
 * @author Fabio Codinha
 */
public class SalaryCategoryDetails extends javax.swing.JFrame {

    //Formulare
    @Autowired
    private EmployeeManagement employeeManagement;

    public EmployeeManagement getEmployeeManagement() {
        return employeeManagement;
    }
    @Autowired
    private EmployeeDetails employeeDetails;

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }
    @Autowired
    private SalaryCategoryManagement salaryCategoryManagement;

    public SalaryCategoryManagement getSalaryCategoryManagement() {
        return salaryCategoryManagement;
    }
    //Controller
    @Autowired
    private SalaryCategoryController salaryCategoryController;

    public SalaryCategoryController getSalaryCategoryController() {
        return salaryCategoryController;
    }
    //Verschiednen Stände von der Oberfläche
    private SalaryCategoryDetailsInterface salaryCategoryDetailsView;
    //Gehaltkategorie, die gezeigt wird
    private SalaryCategory salaryCategory;

    public SalaryCategory getSalaryCategory() {
        return salaryCategory;
    }

    public void setSalaryCategory(SalaryCategory salaryCategory) {
        this.salaryCategory = salaryCategory;
    }

    public SalaryCategoryDetails() {
    }

    //Wenn der Benutzer eine neue Gehaltkategorie erstellen möchte
    public void setNewMode() {
        salaryCategoryDetailsView = new SalaryCategoryNewView();
        salaryCategoryDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Gehaltkategorie ändern möchte
    public void setEditMode() {
        salaryCategoryDetailsView = new SalaryCategoryEditView();
        salaryCategoryDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Gehaltkategorie ansehen möchte
    public void setShowMode() {
        salaryCategoryDetailsView = new SalaryCategoryShowView();
        salaryCategoryDetailsView.applyView(this);
    }
    
    /**
     * Die Felder werden validiert
     *
     * @return Wenn das String leer ist, würde kein Fehler gefunden
     */
    private String checkFields() {
        String errors = "";
        if (txtSalaryFrom.getText().equals("")) {
            errors += "Salary From\n";
        }
        if (txtSalaryTo.getText().equals("")) {
            errors += "Salary To\n";
        }

        if (!errors.equals("")) {
            errors = "The following fields have not been filled:\n" + errors + "\nThose fields are required.";
        } else {
            if (((Float) txtSalaryTo.getValue()) < ((Float) txtSalaryFrom.getValue())) {
                errors += "\"Salary To\" must be higher than \"Salary From\"\n";
            }

            if (!errors.equals("")) {
                errors = "There errors in the fields:\n" + errors;
            }
        }

        return errors;
    }

    //Listeners
    /**
     * Wenn der Benutzer nur ansehen kann, wechselt der Knopf zur
     * Änderungsmodus.<br/>
     * Wenn der Benutzer die Felder ändern kann, wird die Gehaltkategorie
     * gespeichert.<br/>
     * Diese Betrieb wird bei der Variable 'salaryCategoryDetailsView' kontrolliert.
     *
     * @param e ActionEvent Werte
     */
    protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
        String errors = "";
        if (!(salaryCategoryDetailsView instanceof SalaryCategoryShowView)) {
            errors = checkFields();
        }
        if (errors.equals("")) {
            salaryCategoryDetailsView.btnSaveEdit(this);
        } else {
            JOptionPane.showMessageDialog(this, errors, "", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Wenn der Benutzer nicht die Änderungen speichern möchte, wird der
     * Artikel wieder zum Formular geladen
     *
     * @param e ActionEvent Werte
     */
    protected void btnCancel_ActionPerformed(ActionEvent e) {
        salaryCategoryDetailsView.btnCancel(this);
    }

    /**
     * Das Formular wird vorbereitet
     */
    @PostConstruct
    private void setUp() {
        lblCountArticles = new javax.swing.JLabel();
        lblSalaryFrom = new javax.swing.JLabel();
        lblSalaryTo = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();

        txtCountArticles = new javax.swing.JFormattedTextField();
        txtCountArticles.setDocument(new JTextFieldLimit(13));
        txtSalaryFrom = new javax.swing.JFormattedTextField();
        txtSalaryTo = new javax.swing.JFormattedTextField();

        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtDescription.setDocument(new JTextFieldLimit(256));

        btnSaveEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        pnlLeft = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Salary Category Details");
        setMinimumSize(new java.awt.Dimension(300, 350));
        setPreferredSize(new Dimension(300, 350));

        lblCountArticles.setText("N. Articles");

        lblSalaryFrom.setText("Salary From");

        lblSalaryTo.setText("Salary To");

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

        txtCountArticles.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new NumberFormatter(new java.text.DecimalFormat("#0"))));

        NumberFormat amountDisplayFormat;
        amountDisplayFormat = NumberFormat.getCurrencyInstance();
        amountDisplayFormat.setMinimumFractionDigits(2);

        NumberFormat amountEditFormat;
        amountEditFormat = NumberFormat.getNumberInstance();

        txtSalaryFrom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                new NumberFormatter(amountDisplayFormat),
                new NumberFormatter(amountDisplayFormat),
                new NumberFormatter(amountEditFormat)));

        txtSalaryTo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                new NumberFormatter(amountDisplayFormat),
                new NumberFormatter(amountDisplayFormat),
                new NumberFormatter(amountEditFormat)));

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
                .addComponent(lblSalaryFrom)
                .addComponent(lblSalaryTo)
                .addComponent(lblCountArticles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtSalaryFrom, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(txtSalaryTo, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(txtCountArticles, javax.swing.GroupLayout.Alignment.TRAILING)))
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
                .addComponent(lblSalaryFrom)
                .addComponent(txtSalaryFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSalaryTo)
                .addComponent(txtSalaryTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCountArticles)
                .addComponent(txtCountArticles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel lblCountArticles;
    private javax.swing.JLabel lblSalaryFrom;
    private javax.swing.JLabel lblSalaryTo;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea txtDescription;
    public javax.swing.JFormattedTextField txtCountArticles;
    public javax.swing.JFormattedTextField txtSalaryFrom;
    public javax.swing.JFormattedTextField txtSalaryTo;
    public javax.swing.JPanel pnlLeft;
    public javax.swing.JPanel pnlCenter;
    public javax.swing.JPanel pnlRight;
}
