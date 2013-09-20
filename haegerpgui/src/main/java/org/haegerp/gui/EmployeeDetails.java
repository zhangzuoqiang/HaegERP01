package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

import org.haegerp.service.DivisionService;
import org.haegerp.service.EmployeeService;
import org.haegerp.service.SalaryCategoryService;
import org.haegerp.service.UserGroupService;
import org.haegerp.entity.Division;
import org.haegerp.entity.Employee;
import org.haegerp.entity.SalaryCategory;
import org.haegerp.entity.UserGroup;
import org.haegerp.gui.employeedetails.EmployeeDetailsInterface;
import org.haegerp.gui.employeedetails.EmployeeEditView;
import org.haegerp.gui.employeedetails.EmployeeNewView;
import org.haegerp.gui.employeedetails.EmployeeShowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * Dieses Formular wird die Details eines Mitarbeiters kontrollieren
 *
 * @author Fabio Codinha
 */
public class EmployeeDetails extends javax.swing.JFrame {

    private static final long serialVersionUID = 2949647041784163844L;
    private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEX_ZIPCODE = "^[A-Za-z]-[0-9]{5}|[0-9]{5}$";
    private static final String REGEX_PHONE = "^\\+[0-9]*|[0-9]*$";
    //Controller
    @Autowired
    private EmployeeService employeeController;

    public EmployeeService getEmployeeController() {
        return employeeController;
    }
    @Autowired
    private SalaryCategoryService salaryCategoryController;

    public SalaryCategoryService getSalaryCategoryController() {
        return salaryCategoryController;
    }
    @Autowired
    private DivisionService divisionController;

    public DivisionService getDivisionController() {
        return divisionController;
    }
    @Autowired
    private UserGroupService userGroupController;

    public UserGroupService getUserGroupController() {
        return userGroupController;
    }
    //Formulare
    @Autowired
    private SalaryCategoryManagement salaryCategoryManagement;

    public SalaryCategoryManagement getSalaryCategoryManagement() {
        return salaryCategoryManagement;
    }
    @Autowired
    private DivisionManagement divisionManagement;

    public DivisionManagement getDivisionManagement() {
        return divisionManagement;
    }
    @Autowired
    private UserGroupManagement userGroupManagement;

    public UserGroupManagement getUserGroupManagement() {
        return userGroupManagement;
    }
    @Autowired
    private EmployeeManagement employeeManagement;

    public EmployeeManagement getEmployeeManagement() {
        return employeeManagement;
    }
    //Verschiednen Stände von der Oberfläche
    private EmployeeDetailsInterface employeeDetailsView;
    //Gehaltkategorien
    private List<SalaryCategory> salaryCategories;

    public List<SalaryCategory> getSalaryCategories() {
        return salaryCategories;
    }
    //Divisionen
    private List<Division> divisions;

    public List<Division> getDivisions() {
        return divisions;
    }
    //Benutzergruppe
    private List<UserGroup> userGroups;

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }
    //Mitarbeiter, der gezeigt wird
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeDetails() {
    }

    //Wenn der Benutzer einen neuen Mitarbeiter erstellen möchte
    public void setNewMode() {
        employeeDetailsView = new EmployeeNewView();
        employeeDetailsView.applyView(this);
    }

    //Wenn der Benutzer einen Mitarbeiter ändern möchte
    public void setEditMode() {
        employeeDetailsView = new EmployeeEditView();
        employeeDetailsView.applyView(this);
    }

    //Wenn der Benutzer einen Mitarbeiter ansehen möchte
    public void setShowMode() {
        employeeDetailsView = new EmployeeShowView();
        employeeDetailsView.applyView(this);
    }

    /**
     * Die Felder werden validiert
     *
     * @return Wenn das String leer ist, würde kein Fehler gefunden
     */
    private String checkFields() {
        String errors = "";

        //Required Fields
        if (txtIdCard.getText().equals("")) {
            errors += "ID-Card\n";
        }
        if (txtName.getText().equals("")) {
            errors += "Name\n";
        }
        if (cbbDivision.getSelectedIndex() < 1) {
            errors += "Division\n";
        }
        if (cbbUserGroup.getSelectedIndex() < 1) {
            errors += "User Group\n";
        }
        if (cbbSalaryCategory.getSelectedIndex() < 1) {
            errors += "Salary Category\n";
        }
        if (txtUsername.getText().equals("")) {
            errors += "Username\n";
        }
        if (employeeDetailsView instanceof EmployeeNewView && txtPassword.getPassword().equals("")) {
            errors += "Password\n";
        }
        if (txtAddress.getText().equals("")) {
            errors += "Address\n";
        }
        if (txtCity.getText().equals("")) {
            errors += "City\n";
        }
        if (txtZipCode.getText().equals("")) {
            errors += "ZIP Code\n";
        }
        if (txtCountry.getText().equals("")) {
            errors += "Country\n";
        }

        if (!errors.equals("")) {
            errors = "The following fields have not been filled and are required:\n" + errors + "\nThose fields are required.";
        } else {
            String password = String.valueOf(txtPassword.getPassword());
            String passwordConfirmation = String.valueOf(txtPasswordConfirm.getPassword());
            if (password.compareTo(passwordConfirmation) != 0) {
                errors += "Password and the Password Confirmation are different\n";
            }
            Pattern pattern = Pattern.compile(REGEX_EMAIL);
            Matcher matcher = pattern.matcher(txtEmail.getText());
            if (!matcher.matches() && !txtEmail.getText().equals("")) {
                errors += "E-Mail: client@domain.com\n";
            }
            pattern = Pattern.compile(REGEX_ZIPCODE);
            matcher = pattern.matcher(txtZipCode.getText());
            if (!matcher.matches()) {
                errors += "Zip-Code: X-##### or #####\n";
            }
            pattern = Pattern.compile(REGEX_PHONE);
            matcher = pattern.matcher(txtPhoneNumber.getText());
            if (!txtPhoneNumber.getText().equals("") && !matcher.matches()) {
                errors += "Phone Number: +49888123456 or 0888123456\n";
            }
            pattern = Pattern.compile(REGEX_PHONE);
            matcher = pattern.matcher(txtMobileNumber.getText());
            if (!txtMobileNumber.getText().equals("") && !matcher.matches()) {
                errors += "Mobile Number: +49888123456 or 0888123456\n";
            }

            if (!errors.equals("")) {
                errors = "The following fields contain errors:\n" + errors;
            }
        }

        return errors;
    }

    //Listeners
    /**
     * Wenn der Benutzer nur ansehen kann, wechselt der Knopf zur
     * Änderungsmodus.<br/>
     * Wenn der Benutzer die Felder ändern kann, wird der Mitarbeiter
     * gespeichert.<br/>
     * Diese Betrieb wird bei der Variable 'employeeDetailsView' kontrolliert.
     *
     * @param e ActionEvent Werte
     */
    protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
        String errors = "";
        if (!(employeeDetailsView instanceof EmployeeShowView)) {
            errors = checkFields();
        }
        if (errors.equals("")) {
            employeeDetailsView.btnSaveEdit(this);
        } else {
            JOptionPane.showMessageDialog(this, errors, "", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Wenn der Benutzer nicht die Änderungen speichern möchte, wird der
     * Mitarbeiter wieder zum Formular geladen
     *
     * @param e ActionEvent Werte
     */
    protected void btnCancel_ActionPerformed(ActionEvent e) {
        employeeDetailsView.btnCancel(this);
    }

    /**
     * Das Formular wird vorbereitet
     */
    @PostConstruct
    private void setUp() {
        lblIdCard = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblDivision = new javax.swing.JLabel();
        lblUserGroup = new javax.swing.JLabel();
        lblSalaryCategory = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblPasswordConfirm = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblCity = new javax.swing.JLabel();
        lblZipCode = new javax.swing.JLabel();
        lblRegion = new javax.swing.JLabel();
        lblCountry = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblPhoneNumber = new javax.swing.JLabel();
        lblMobileNumber = new javax.swing.JLabel();

        txtIdCard = new javax.swing.JFormattedTextField();
        txtIdCard.setDocument(new JTextFieldLimit(15));
        txtName = new javax.swing.JTextField();
        txtName.setDocument(new JTextFieldLimit(100));
        cbbDivision = new javax.swing.JComboBox<String>();
        cbbUserGroup = new javax.swing.JComboBox<String>();
        cbbSalaryCategory = new javax.swing.JComboBox<String>();
        txtUsername = new javax.swing.JTextField();
        txtUsername.setDocument(new JTextFieldLimit(50));
        txtPassword = new javax.swing.JPasswordField();
        txtPasswordConfirm = new javax.swing.JPasswordField();
        txtAddress = new javax.swing.JTextField();
        txtAddress.setDocument(new JTextFieldLimit(100));
        txtZipCode = new javax.swing.JTextField();
        txtZipCode.setDocument(new JTextFieldLimit(15));
        txtCity = new javax.swing.JTextField();
        txtCity.setDocument(new JTextFieldLimit(30));
        txtRegion = new javax.swing.JTextField();
        txtRegion.setDocument(new JTextFieldLimit(30));
        txtCountry = new javax.swing.JTextField();
        txtCountry.setDocument(new JTextFieldLimit(30));
        txtEmail = new javax.swing.JTextField();
        txtEmail.setDocument(new JTextFieldLimit(50));
        txtPhoneNumber = new javax.swing.JTextField();
        txtPhoneNumber.setDocument(new JTextFieldLimit(20));
        txtMobileNumber = new javax.swing.JTextField();
        txtMobileNumber.setDocument(new JTextFieldLimit(20));

        btnSaveEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        pnlLeft = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Employee Details");
        setMinimumSize(new java.awt.Dimension(405, 565));

        lblIdCard.setText("ID-Card");

        lblName.setText("Name");

        lblDivision.setText("Division");

        lblUserGroup.setText("User Group");

        lblSalaryCategory.setText("Salary Category");

        lblUsername.setText("Username");

        lblPassword.setText("Password");

        lblAddress.setText("Address");

        lblCity.setText("City");

        lblRegion.setText("Region");

        lblZipCode.setText("Zip-Code");

        lblCountry.setText("Country");

        lblEmail.setText("E-Mail");

        lblPhoneNumber.setText("Phone Number");

        lblPasswordConfirm.setText("Confirm Password");

        lblMobileNumber.setText("Mobile Number");

        loadCbbDivision();
        loadCbbSalaryCategory();
        loadCbbUserGroup();

        btnSaveEdit.setText("Save");
        btnSaveEdit.setMaximumSize(new java.awt.Dimension(75, 23));
        btnSaveEdit.setMinimumSize(new java.awt.Dimension(75, 23));
        btnSaveEdit.setPreferredSize(new java.awt.Dimension(75, 23));
        btnSaveEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSaveEdit_ActionPerformed(e);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.setMaximumSize(new java.awt.Dimension(75, 23));
        btnCancel.setMinimumSize(new java.awt.Dimension(75, 23));
        btnCancel.setPreferredSize(new java.awt.Dimension(75, 23));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancel_ActionPerformed(e);
            }
        });

        txtIdCard.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

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
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblUserGroup)
                .addComponent(lblDivision)
                .addComponent(lblSalaryCategory)
                .addComponent(lblIdCard)
                .addComponent(lblName))
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblUsername)
                .addComponent(lblPassword))
                .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(cbbUserGroup, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbbSalaryCategory, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtIdCard, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(txtUsername)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblPasswordConfirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(lblPhoneNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCity, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblRegion, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblZipCode, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblCountry, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblAddress, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblMobileNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSaveEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(txtMobileNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addComponent(txtAddress)
                .addComponent(txtZipCode)
                .addComponent(txtCity)
                .addComponent(txtRegion)
                .addComponent(txtCountry)
                .addComponent(txtEmail)
                .addComponent(txtPhoneNumber))
                .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap()));
        pnlCenterLayout.setVerticalGroup(
                pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblIdCard)
                .addComponent(txtIdCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblName))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cbbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDivision))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblUserGroup)
                .addComponent(cbbUserGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cbbSalaryCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSalaryCategory))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPassword)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPasswordConfirm)
                .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblAddress)
                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblZipCode)
                .addComponent(txtZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCity)
                .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblRegion)
                .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCountry)
                .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblEmail)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPhoneNumber)
                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblMobileNumber)
                .addComponent(txtMobileNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSaveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    public void loadCbbDivision() {
        Object idx = null;
        if (cbbDivision.getItemCount() > 0) {
            idx = cbbDivision.getSelectedItem();
        }

        cbbDivision.removeAllItems();
        divisions = divisionController.getAllDivisions();

        cbbDivision.addItem("Select One...");
        for (Division division : divisions) {
            cbbDivision.addItem(division.getName());
        }

        cbbDivision.setSelectedItem(idx);
    }

    public void loadCbbSalaryCategory() {
        Object idx = null;
        if (cbbSalaryCategory.getItemCount() > 0) {
            idx = cbbSalaryCategory.getSelectedItem();
        }

        cbbSalaryCategory.removeAllItems();
        salaryCategories = salaryCategoryController.getAllCategories();

        cbbSalaryCategory.addItem("Select One...");
        for (SalaryCategory salaryCategory : salaryCategories) {
            cbbSalaryCategory.addItem(salaryCategory.getSalaryFrom() + " - " + salaryCategory.getSalaryTo());
        }

        cbbSalaryCategory.setSelectedItem(idx);
    }

    public void loadCbbUserGroup() {
        Object idx = null;
        if (cbbUserGroup.getItemCount() > 0) {
            idx = cbbUserGroup.getSelectedItem();
        }

        cbbUserGroup.removeAllItems();
        userGroups = userGroupController.getAllGroups();

        cbbUserGroup.addItem("Select One...");
        for (UserGroup userGroup : userGroups) {
            cbbUserGroup.addItem(userGroup.getName());
        }

        cbbUserGroup.setSelectedItem(idx);
    }
    public javax.swing.JButton btnCancel;
    public javax.swing.JButton btnSaveEdit;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblCountry;
    private javax.swing.JLabel lblDivision;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblIdCard;
    private javax.swing.JLabel lblMobileNumber;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordConfirm;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblRegion;
    private javax.swing.JLabel lblSalaryCategory;
    private javax.swing.JLabel lblUserGroup;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblZipCode;
    public javax.swing.JComboBox<String> cbbDivision;
    public javax.swing.JComboBox<String> cbbSalaryCategory;
    public javax.swing.JComboBox<String> cbbUserGroup;
    public javax.swing.JTextField txtAddress;
    public javax.swing.JTextField txtCity;
    public javax.swing.JTextField txtCountry;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JFormattedTextField txtIdCard;
    public javax.swing.JTextField txtMobileNumber;
    public javax.swing.JTextField txtName;
    public javax.swing.JPasswordField txtPassword;
    public javax.swing.JPasswordField txtPasswordConfirm;
    public javax.swing.JTextField txtPhoneNumber;
    public javax.swing.JTextField txtRegion;
    public javax.swing.JTextField txtUsername;
    public javax.swing.JTextField txtZipCode;
    public javax.swing.JPanel pnlLeft;
    public javax.swing.JPanel pnlCenter;
    public javax.swing.JPanel pnlRight;
}
