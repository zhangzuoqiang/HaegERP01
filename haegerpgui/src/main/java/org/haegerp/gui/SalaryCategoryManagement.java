package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.haegerp.controller.EmployeeController;
import org.haegerp.controller.SalaryCategoryController;
import org.haegerp.entity.Employee;
import org.haegerp.entity.SalaryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * Dieses Formular wird Gehaltkategorien verwalten
 *
 * @author Fabio Codinha
 */
public class SalaryCategoryManagement extends JFrame {

    //Controller
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private SalaryCategoryController salaryCategoryController;
    //Formulare
    @Autowired
    private SalaryCategoryDetails salaryCategoryDetails;
    @Autowired
    private EmployeeManagement employeeManagement;

    public SalaryCategoryManagement() {
    }

    /**
     * Die Tabelle wird ausgefüllt
     */
    public void loadTable() {
        tblSalaryCategory.setModel(
                new DefaultTableModel(
                salaryCategoryController.loadTableRows(sldNumberResults.getValue()),
                new String[]{
            "ID", "Salary From", "Salary To", "N. Employees", "Description", "Last Modified", "Modified By"
        }) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblSalaryCategory.removeColumn(tblSalaryCategory.getColumn("ID"));
        lblPage.setText("Page " + (salaryCategoryController.getPage().getNumber() + 1) + "/" + salaryCategoryController.getPage().getTotalPages());
    }

    //Listeners
    /**
     * Die nächste Seite wird gezeigt
     *
     * @param e ActionEvent Werte
     */
    protected void btnNext_ActionPerformed(ActionEvent e) {
        salaryCategoryController.getNextPage(sldNumberResults.getValue());
        loadTable();
    }

    /**
     * Die vorherige Seite wird gezeigt
     *
     * @param e ActionEvent Werte
     */
    protected void btnPrevious_ActionPerformed(ActionEvent e) {
        salaryCategoryController.getPreviousPage(sldNumberResults.getValue());
        loadTable();
    }

    /**
     * Dieses Listener kontrolliert die Nummer der Artikeln, die eine Seite hat
     *
     * @param e MouseEvent Werte
     */
    protected void sldNumberResults_MouseReleased(MouseEvent e) {
        salaryCategoryController.getFirstPage(sldNumberResults.getValue());
        loadTable();
    }

    /**
     * Diese Methode sucht in der Datenbank, was der Benutzer eingefügt hat
     *
     * @param e KeyEvent Werte
     */
    protected void txtSearch_KeyReleased(KeyEvent e) {
        salaryCategoryController.setSearch(txtSearch.getText(), sldNumberResults.getValue());
        loadTable();
    }

    /**
     * Ein neuer Artikel wird erstellt
     *
     * @param e ActionEvent Werte
     */
    protected void btnNew_ActionListener(ActionEvent e) {
        salaryCategoryDetails.setNewMode();
        salaryCategoryDetails.setVisible(true);
    }

    /**
     * Ein Artikel, der in der Datenbank ist, wird beim Benutzer geändert
     *
     * @param e ActionEvent Werte
     */
    protected void btnEdit_ActionPerformed(ActionEvent e) {
        int row = tblSalaryCategory.getSelectedRow();
        if (row != -1) {
            SalaryCategory salaryCategory = salaryCategoryController.getPage().getContent().get(row);
            salaryCategoryDetails.setSalaryCategory(salaryCategory);
            salaryCategoryDetails.setEditMode();
            salaryCategoryDetails.setVisible(true);
        }
    }

    /**
     * Ein Artikel wird gelöscht
     *
     * @param e ActionEvent Werte
     */
    protected void btnDelete_ActionListener(ActionEvent e) {
        int row = tblSalaryCategory.getSelectedRow();
        int option = 1;
        SalaryCategory salaryCategory = salaryCategoryController.getPage().getContent().get(row);
        String employeeNames = "";

        if (row != -1) {
            for (Employee employee : salaryCategory.getEmployees()) {
                employeeNames += "- " + employee.getName() + "\n";
            }

            if (employeeNames.length() == 0) {
                option = JOptionPane.showConfirmDialog(this, "Delete salary category:\n" + salaryCategory.getSalaryFrom() + " - " + salaryCategory.getSalaryTo() + "\nAre you sure?", "", JOptionPane.YES_NO_OPTION);
            } else {
                JOptionPane.showMessageDialog(this, "This category still have employees associated:\n" + employeeNames, "", JOptionPane.WARNING_MESSAGE);
            }

            if (option == JOptionPane.YES_OPTION) {
                salaryCategoryController.delete(salaryCategory);
                loadTable();

                employeeManagement.loadTable();
                employeeManagement.loadCbbSalaryCategory();
                employeeManagement.getEmployeeDetails().loadCbbSalaryCategory();
            }
        }
    }

    /**
     * Eine Gehaltkategorie wird gezeigt
     *
     * @param e MouseEvent Werte
     */
    protected void tblSalaryCategory_MouseDoubleClick(MouseEvent e) {
        int row = tblSalaryCategory.getSelectedRow();
        if (row != -1) {
            SalaryCategory salaryCategory = salaryCategoryController.getPage().getContent().get(row);
            salaryCategoryDetails.setSalaryCategory(salaryCategory);
            salaryCategoryDetails.setShowMode();
            salaryCategoryDetails.setVisible(true);
        }
    }

    /**
     * Das Formular wird vorbereitet
     */
    @PostConstruct
    public void setUp() {
        pnlTblSalaryCategory = new JScrollPane();
        tblSalaryCategory = new JTable();
        btnPrevious = new JButton();
        btnNext = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();
        btnNew = new JButton();
        pnlCenterNewR = new JPanel();
        pnlCenterNewL = new JPanel();
        lblSlider = new JLabel();

        setTitle("Salary Category Management");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 415));

        tblSalaryCategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblSalaryCategory.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() > 1) {
                    tblSalaryCategory_MouseDoubleClick(e);
                }
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }
        });

        pnlTblSalaryCategory.setViewportView(tblSalaryCategory);

        btnPrevious.setText("Previous");
        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnPrevious_ActionPerformed(e);
            }
        });

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNext_ActionPerformed(e);
            }
        });
        btnNext.setText("Next");

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnEdit_ActionPerformed(e);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDelete_ActionListener(e);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNew_ActionListener(e);
            }
        });

        javax.swing.GroupLayout gl_pnlCenterNewR = new javax.swing.GroupLayout(pnlCenterNewR);
        pnlCenterNewR.setLayout(gl_pnlCenterNewR);
        gl_pnlCenterNewR.setHorizontalGroup(
                gl_pnlCenterNewR.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE));
        gl_pnlCenterNewR.setVerticalGroup(
                gl_pnlCenterNewR.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE));

        javax.swing.GroupLayout gl_pnlCenterNewL = new javax.swing.GroupLayout(pnlCenterNewL);
        pnlCenterNewL.setLayout(gl_pnlCenterNewL);
        gl_pnlCenterNewL.setHorizontalGroup(
                gl_pnlCenterNewL.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE));
        gl_pnlCenterNewL.setVerticalGroup(
                gl_pnlCenterNewL.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE));

        pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(pnlTblSalaryCategory, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                .addComponent(pnlSearch, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                .addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrevious, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(pnlCenterNewL, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(pnlCenterNewR, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                .addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSearch, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(pnlTblSalaryCategory, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addGap(18)
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(btnPrevious)
                .addComponent(btnNext))
                .addGap(18)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(btnEdit)
                .addComponent(btnDelete)))
                .addComponent(pnlCenterNewR, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(pnlCenterNewL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(btnNew))
                .addContainerGap()));
        pnlSearch.setLayout(null);

        txtSearch = new JTextField();
        txtSearch.setBounds(65, 19, 177, 20);
        pnlSearch.add(txtSearch);
        txtSearch.setColumns(10);
        txtSearch.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                txtSearch_KeyReleased(e);
            }

            public void keyPressed(KeyEvent e) {
            }
        });

        lblSearch = new JLabel();
        lblSearch.setText("Search");
        lblSearch.setBounds(10, 22, 45, 14);
        pnlSearch.add(lblSearch);

        lblSlider.setBounds(485, 22, 26, 14);
        pnlSearch.add(lblSlider);
        getContentPane().setLayout(layout);

        sldNumberResults = new JSlider();
        sldNumberResults.setPaintTicks(true);
        sldNumberResults.setSnapToTicks(true);
        sldNumberResults.setMinorTickSpacing(5);
        sldNumberResults.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                lblSlider.setText(String.valueOf(sldNumberResults.getValue()));
            }
        });

        sldNumberResults.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                sldNumberResults_MouseReleased(e);
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

        sldNumberResults.setValue(10);
        sldNumberResults.setMajorTickSpacing(20);
        sldNumberResults.setMaximum(100);
        sldNumberResults.setMinimum(10);
        sldNumberResults.setBounds(275, 19, 200, 27);
        pnlSearch.add(sldNumberResults);

        lblPage = new JLabel("");
        lblPage.setHorizontalAlignment(SwingConstants.LEFT);
        lblPage.setBounds(523, 21, 110, 16);
        pnlSearch.add(lblPage);

        loadTable();

        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - getWidth()) / 2, (dim.height - getHeight()) / 2);
    }
    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnNew;
    private JButton btnNext;
    private JButton btnPrevious;
    private JPanel pnlCenterNewR;
    private JPanel pnlCenterNewL;
    private JScrollPane pnlTblSalaryCategory;
    private JTable tblSalaryCategory;
    private JPanel pnlSearch;
    private JTextField txtSearch;
    private JLabel lblSearch;
    private JLabel lblSlider;
    private JSlider sldNumberResults;
    private JLabel lblPage;
}
