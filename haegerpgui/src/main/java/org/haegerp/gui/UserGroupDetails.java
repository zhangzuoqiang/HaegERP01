package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.haegerp.service.PermissionService;
import org.haegerp.service.UserGroupService;
import org.haegerp.entity.Permission;
import org.haegerp.entity.UserGroup;
import org.haegerp.gui.usergroupdetails.UserGroupDetailsInterface;
import org.haegerp.gui.usergroupdetails.UserGroupEditView;
import org.haegerp.gui.usergroupdetails.UserGroupNewView;
import org.haegerp.gui.usergroupdetails.UserGroupShowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * Dieses Formular wird die Details einer Benutzergruppen kontrollieren
 *
 * @author Fabio Codinha
 */
public class UserGroupDetails extends javax.swing.JFrame {

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
    private UserGroupManagement userGroupManagement;

    public UserGroupManagement getUserGroupManagement() {
        return userGroupManagement;
    }
    //Controller
    @Autowired
    private UserGroupService userGroupController;

    public UserGroupService getUserGroupController() {
        return userGroupController;
    }
    @Autowired
    private PermissionService permissionController;
    //Verschiednen Stände von der Oberfläche
    private UserGroupDetailsInterface userGroupDetailsView;
    //Benutzer, der gezeigt wird
    private UserGroup userGroup;

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
    //Erlaubnise
    private List<Permission> permissions = new LinkedList<Permission>();

    public List<Permission> getPermissions() {
        return permissions;
    }

    public UserGroupDetails() {
    }

    //Wenn der Benutzer eine neue Benutzergruppe erstellen möchte
    public void setNewMode() {
        userGroupDetailsView = new UserGroupNewView();
        userGroupDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Benutzergruppe ändern möchte
    public void setEditMode() {
        userGroupDetailsView = new UserGroupEditView();
        userGroupDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Benutzergruppe ansehen möchte
    public void setShowMode() {
        userGroupDetailsView = new UserGroupShowView();
        userGroupDetailsView.applyView(this);
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
     * Wenn der Benutzer die Felder ändern kann, wird die Benutzergruppe
     * gespeichert.<br/>
     * Diese Betrieb wird bei der Variable 'userGroupDetailsView' kontrolliert.
     *
     * @param e ActionEvent Werte
     */
    protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
        String errors = "";
        if (!(userGroupDetailsView instanceof UserGroupShowView)) {
            errors = checkFields();
        }
        if (errors.equals("")) {
            userGroupDetailsView.btnSaveEdit(this);
        } else {
            JOptionPane.showMessageDialog(this, "The following fields have not been filled:\n" + errors + "\nThose fields are required.", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Wenn der Benutzer nicht die Änderungen speichern möchte, wird der Artikel
     * wieder zum Formular geladen
     *
     * @param e ActionEvent Werte
     */
    protected void btnCancel_ActionPerformed(ActionEvent e) {
        userGroupDetailsView.btnCancel(this);
    }

    /**
     * Die ausgewählte Erlaubnis wird zur Benutzergruppe gegeben
     *
     * @param e ActionEvent Werte
     */
    protected void btnGrant_ActionPerformed(ActionEvent e) {
        int index = lstRevokedPermissions.getSelectedIndex();
        if (index > -1) {
            lstGrantedPermissionsModel.addElement(lstRevokedPermissionsModel.remove(index));
        }
    }

    /**
     * Die ausgewählte Erlaubnis wird von der Benutzergruppe gelöscht
     *
     * @param e ActionEvent Werte
     */
    protected void btnRevoke_ActionPerformed(ActionEvent e) {
        int index = lstGrantedPermissions.getSelectedIndex();
        if (index > -1) {
            lstRevokedPermissionsModel.addElement(lstGrantedPermissionsModel.remove(index));
        }
    }

    /**
     * Alle Erlaubnise werden zur Benutzergruppe gegeben
     *
     * @param e ActionEvent Werte
     */
    protected void btnGrantAll_ActionPerformed(ActionEvent e) {
        for (int i = lstRevokedPermissionsModel.size() - 1; i >= 0; i--) {
            lstGrantedPermissionsModel.addElement(lstRevokedPermissionsModel.remove(i));
        }
    }

    /**
     * Alle Erlaubnise werden von der Benutzergruppe gelöscht
     *
     * @param e ActionEvent Werte
     */
    protected void btnRevokeAll_ActionPerformed(ActionEvent e) {
        for (int i = lstGrantedPermissionsModel.size() - 1; i >= 0; i--) {
            lstRevokedPermissionsModel.addElement(lstGrantedPermissionsModel.remove(i));
        }
    }

    /**
     * Das Formular wird vorbereitet
     */
    @PostConstruct
    private void setUp() {
        lblCountEmployees = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();

        txtCountEmployees = new javax.swing.JFormattedTextField();
        txtName = new javax.swing.JTextField();
        txtName.setDocument(new JTextFieldLimit(50));

        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtDescription.setDocument(new JTextFieldLimit(256));

        lblGrantedPermissions = new javax.swing.JLabel();
        lblRevokedPermissions = new javax.swing.JLabel();

        pnlLstRevokedPermissions = new javax.swing.JScrollPane();
        lstRevokedPermissionsModel = new DefaultListModel<String>();
        lstRevokedPermissions = new javax.swing.JList<String>(lstRevokedPermissionsModel);

        pnlLstGrantedPermissions = new javax.swing.JScrollPane();
        lstGrantedPermissionsModel = new DefaultListModel<String>();
        lstGrantedPermissions = new javax.swing.JList<String>(lstGrantedPermissionsModel);

        btnGrant = new javax.swing.JButton();
        btnRevoke = new javax.swing.JButton();
        btnRevokeAll = new javax.swing.JButton();
        btnGrantAll = new javax.swing.JButton();

        btnSaveEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        pnlLeft = new javax.swing.JPanel();
        pnlCenter = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("User Group Details");
        setMinimumSize(new java.awt.Dimension(450, 470));
        setPreferredSize(new Dimension(450, 470));

        lblCountEmployees.setText("N. Employees");

        lblName.setText("Name");

        lblDescription.setText("Description");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        pnlLstRevokedPermissions.setViewportView(lstRevokedPermissions);

        pnlLstGrantedPermissions.setViewportView(lstGrantedPermissions);

        btnGrant.setText("<");
        btnGrant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnGrant_ActionPerformed(e);
            }
        });

        btnRevoke.setText(">");
        btnRevoke.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRevoke_ActionPerformed(e);
            }
        });

        btnRevokeAll.setText(">>");
        btnRevokeAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRevokeAll_ActionPerformed(e);
            }
        });

        btnGrantAll.setText("<<");
        btnGrantAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnGrantAll_ActionPerformed(e);
            }
        });

        lblGrantedPermissions.setText("Granted Permissions");

        lblRevokedPermissions.setText("Revoked Permissions");

        loadLstPermission();

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

        txtCountEmployees.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

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
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtName))
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(lblCountEmployees)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCountEmployees))
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(lblDescription)
                .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(pnlLstGrantedPermissions, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(btnGrant, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnRevoke, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnRevokeAll, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnGrantAll, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(lblGrantedPermissions))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblRevokedPermissions)
                .addComponent(pnlLstRevokedPermissions, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addComponent(btnSaveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));
        pnlCenterLayout.setVerticalGroup(
                pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblName)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblCountEmployees)
                .addComponent(txtCountEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblGrantedPermissions)
                .addComponent(lblRevokedPermissions))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(btnGrant)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRevoke)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGrantAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRevokeAll))
                .addComponent(pnlLstRevokedPermissions, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(pnlLstGrantedPermissions, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnSaveEdit)
                .addComponent(btnCancel))
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

    public void loadLstPermission() {
        int x = 0;
        List<Permission> allPermissions = permissionController.getAllPermissions();

        permissions.clear();
        lstRevokedPermissionsModel.clear();
        lstGrantedPermissionsModel.clear();

        for (Permission permission : allPermissions) {
            permissions.add(permission);
            lstRevokedPermissionsModel.add(x++, permission.getModuleName());
        }
    }
    public javax.swing.JButton btnCancel;
    public javax.swing.JButton btnSaveEdit;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblCountEmployees;
    private javax.swing.JLabel lblName;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea txtDescription;
    public javax.swing.JFormattedTextField txtCountEmployees;
    public javax.swing.JTextField txtName;
    public javax.swing.JButton btnGrant;
    public javax.swing.JButton btnGrantAll;
    public javax.swing.JButton btnRevoke;
    public javax.swing.JButton btnRevokeAll;
    public javax.swing.JLabel lblGrantedPermissions;
    public javax.swing.JLabel lblRevokedPermissions;
    public javax.swing.JList<String> lstGrantedPermissions;
    public DefaultListModel<String> lstGrantedPermissionsModel;
    public javax.swing.JList<String> lstRevokedPermissions;
    public DefaultListModel<String> lstRevokedPermissionsModel;
    public javax.swing.JScrollPane pnlLstGrantedPermissions;
    public javax.swing.JScrollPane pnlLstRevokedPermissions;
    public javax.swing.JPanel pnlLeft;
    public javax.swing.JPanel pnlCenter;
    public javax.swing.JPanel pnlRight;
}
