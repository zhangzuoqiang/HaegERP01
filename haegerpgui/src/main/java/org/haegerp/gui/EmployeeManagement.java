package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.haegerp.controller.DivisionController;
import org.haegerp.controller.EmployeeController;
import org.haegerp.controller.SalaryCategoryController;
import org.haegerp.controller.UserGroupController;
import org.haegerp.entity.Division;
import org.haegerp.entity.Employee;
import org.haegerp.entity.SalaryCategory;
import org.haegerp.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeManagement extends JFrame {
	
	private static final long serialVersionUID = 2464190735195227843L;
	
	@Autowired
	private EmployeeController employeeController;
	
	@Autowired
	private SalaryCategoryController salaryCategoryController;
	
	@Autowired
	private UserGroupController userGroupController;
	
	@Autowired
	private DivisionController divisionController;
	
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

	private List<SalaryCategory> salaryCategories;
	
	@Autowired
	private UserGroupManagement userGroupManagement;
	
	public UserGroupManagement getUserGroupManagement() {
		return userGroupManagement;
	}

	private List<UserGroup> userGroups;
	
	@Autowired
	private DivisionManagement divisionManagement;
	
	public DivisionManagement getDivisionManagement() {
		return divisionManagement;
	}

	private List<Division> divisions;
	
	public EmployeeManagement() {
    }
	
	//Listeners
	protected void btnNext_ActionPerformed(ActionEvent e) {
		employeeController.getNextPage(sldNumberResults.getValue());
		loadTable();
	}
	
	protected void btnPrevious_ActionPerformed(ActionEvent e) {
		employeeController.getPreviousPage(sldNumberResults.getValue());
		loadTable();
	}
	
	protected void sldNumberResults_MouseReleased(MouseEvent e) {
		employeeController.getFirstPage(sldNumberResults.getValue());
		loadTable();
	}
	
	protected void cbbSalaryCategory_ActionPerformed(ActionEvent e) {
		int cbbIndex = cbbSalaryCategory.getSelectedIndex() -1;
		if (cbbIndex < 0)
			employeeController.setSalaryCategory((long)cbbIndex, sldNumberResults.getValue());
		else
			employeeController.setSalaryCategory((salaryCategories.get(cbbIndex)).getIdSalaryCategory(), sldNumberResults.getValue());
		loadTable();
	}
	
	protected void cbbUserGroup_ActionListener(ActionEvent e) {
		int cbbIndex = cbbUserGroup.getSelectedIndex() -1;
		if (cbbIndex < 0)
			employeeController.setUserGroup((long)cbbIndex, sldNumberResults.getValue());
		else
			employeeController.setUserGroup((userGroups.get(cbbIndex)).getIdUserGroup(), sldNumberResults.getValue());
		loadTable();
	}
	
	protected void cbbDivision_ActionPerformed(ActionEvent e) {
		int cbbIndex = cbbDivision.getSelectedIndex() -1;
		if (cbbIndex < 0)
			employeeController.setDivision((long)cbbIndex, sldNumberResults.getValue());
		else
			employeeController.setDivision((divisions.get(cbbIndex)).getIdDivision(), sldNumberResults.getValue());
		loadTable();
	}
	
	protected void txtSearch_KeyReleased(KeyEvent e) {
		employeeController.setSearch(txtSearch.getText(), sldNumberResults.getValue());
		loadTable();
	}
	
	protected void btnNew_ActionListener(ActionEvent e) {
		employeeDetails.setNewMode();
		employeeDetails.setVisible(true);
	}
	
	protected void btnEdit_ActionPerformed(ActionEvent e) {
		int row = tblEmployees.getSelectedRow();
		if (row != -1) {
			Employee employee = employeeController.getPage().getContent().get(row);
			employeeDetails.setEmployee(employee);
			employeeDetails.setEditMode();
			employeeDetails.setVisible(true);
		}
	}
	
	protected void btnDelete_ActionListener(ActionEvent e) {
		int row = tblEmployees.getSelectedRow();
		if (row != -1) {
			Employee employee = employeeController.getPage().getContent().get(row);
			int option = JOptionPane.showConfirmDialog(this, "Delete: " + employee.getName() + "\nAre you sure?", "", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				employeeController.delete(employee);
				loadTable();
				salaryCategoryManagement.loadTable();
				userGroupManagement.loadTable();
				divisionManagement.loadTable();
			}
		}
	}
	
	protected void tblEmployees_MouseDoubleClick(MouseEvent e) {
		int row = tblEmployees.getSelectedRow();
		if (row != -1) {
			Employee employee = employeeController.getPage().getContent().get(row);
			employeeDetails.setEmployee(employee);
			employeeDetails.setShowMode();
			employeeDetails.setVisible(true);
		}
	}
	
	@PostConstruct
	public void setUp(){
		pnlTblEmployees = new JScrollPane();
        tblEmployees = new JTable();
        btnPrevious = new JButton();
        btnNext = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();
        btnNew = new JButton();
        pnlCenterNewR = new JPanel();
        pnlCenterNewL = new JPanel();
        lblSlider = new JLabel();
        
        setTitle("Employee Management");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(845, 415));
        
        tblEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEmployees.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) { }
			
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() > 1)
					tblEmployees_MouseDoubleClick(e);
			}
			
			public void mouseExited(MouseEvent e) { }
			
			public void mouseEntered(MouseEvent e) { }
			
			public void mouseClicked(MouseEvent e) { }
		});
        
        pnlTblEmployees.setViewportView(tblEmployees);

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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gl_pnlCenterNewR.setVerticalGroup(
            gl_pnlCenterNewR.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gl_pnlCenterNewL = new javax.swing.GroupLayout(pnlCenterNewL);
        pnlCenterNewL.setLayout(gl_pnlCenterNewL);
        gl_pnlCenterNewL.setHorizontalGroup(
            gl_pnlCenterNewL.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gl_pnlCenterNewL.setVerticalGroup(
            gl_pnlCenterNewL.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        
        pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(pnlTblEmployees, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        				.addComponent(pnlSearch, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(btnPrevious, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pnlCenterNewL, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pnlCenterNewR, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(pnlSearch, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(pnlTblEmployees, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
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
        			.addContainerGap())
        );
        pnlSearch.setLayout(null);
        
        lblDivision = new JLabel();
        lblDivision.setText("Division");
        lblDivision.setBounds(10, 28, 45, 14);
        pnlSearch.add(lblDivision);
        
        cbbDivision = new JComboBox<String>();
        cbbDivision.setBounds(65, 24, 177, 22);
        pnlSearch.add(cbbDivision);
        loadCbbDivision();
        cbbDivision.setSelectedIndex(0);
        cbbDivision.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cbbDivision_ActionPerformed(e);
			}
		});
        
        txtSearch = new JTextField();
        txtSearch.setBounds(65, 58, 177, 20);
        pnlSearch.add(txtSearch);
        txtSearch.setColumns(10);
        txtSearch.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) { }
			
			public void keyReleased(KeyEvent e) {
				txtSearch_KeyReleased(e);
			}
			
			public void keyPressed(KeyEvent e) { }
		});
        
        lblSearch = new JLabel();
        lblSearch.setText("Search");
        lblSearch.setBounds(10, 61, 45, 14);
        pnlSearch.add(lblSearch);
        
        cbbUserGroup = new JComboBox<String>();
        cbbUserGroup.setBounds(326, 24, 177, 22);
        pnlSearch.add(cbbUserGroup);
        loadCbbUserGroup();
        cbbUserGroup.setSelectedIndex(0);
        cbbUserGroup.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cbbUserGroup_ActionListener(e);
			}
		});
        
        lblSlider.setBounds(487, 28, 26, 14);
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
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
        
        sldNumberResults.setValue(10);
        sldNumberResults.setMajorTickSpacing(20);
        sldNumberResults.setMaximum(100);
        sldNumberResults.setMinimum(10);
        sldNumberResults.setBounds(252, 51, 200, 27);
        pnlSearch.add(sldNumberResults);
        
        lblPage = new JLabel("");
        lblPage.setHorizontalAlignment(SwingConstants.LEFT);
        lblPage.setBounds(462, 60, 110, 16);
        pnlSearch.add(lblPage);
        
        lblUserGroup = new JLabel("User Group");
        lblUserGroup.setBounds(252, 28, 64, 14);
        pnlSearch.add(lblUserGroup);
        
        lblSalaryCategory = new JLabel("Salary Category");
        lblSalaryCategory.setBounds(513, 28, 101, 14);
        pnlSearch.add(lblSalaryCategory);
        
        cbbSalaryCategory = new JComboBox<String>();
        cbbSalaryCategory.setBounds(624, 24, 177, 22);
        pnlSearch.add(cbbSalaryCategory);
        loadCbbSalaryCategory();
        cbbSalaryCategory.setSelectedIndex(0);
        cbbSalaryCategory.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cbbSalaryCategory_ActionPerformed(e);
			}
		});
        
        loadTable();
        
        pack();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width-getWidth())/2, (dim.height-getHeight())/2);
	}
	
	public void loadCbbDivision(){
		Object idx = null;
		if (cbbDivision.getItemCount() > 0)
			idx = cbbDivision.getSelectedItem();
		
		cbbDivision.removeAllItems();
		divisions = divisionController.getAllDivisions();
		
		cbbDivision.addItem("All");
		for (Division division : divisions) {
			cbbDivision.addItem(division.getName());
		}
		
		cbbDivision.setSelectedItem(idx);
	}
	
	public void loadCbbSalaryCategory(){
		Object idx = null;
		if (cbbSalaryCategory.getItemCount() > 0)
			idx = cbbSalaryCategory.getSelectedItem();
		
		cbbSalaryCategory.removeAllItems();
		salaryCategories = salaryCategoryController.getAllCategories();
		
		cbbSalaryCategory.addItem("All");
		for (SalaryCategory salaryCategory : salaryCategories) {
			cbbSalaryCategory.addItem(salaryCategory.getSalaryFrom() + " - " + salaryCategory.getSalaryTo());
		}
		
		cbbSalaryCategory.setSelectedItem(idx);
	}
	
	public void loadCbbUserGroup(){
		Object idx = null;
		if (cbbUserGroup.getItemCount() > 0)
			idx = cbbUserGroup.getSelectedItem();
		
		cbbUserGroup.removeAllItems();
		userGroups = userGroupController.getAllGroups();
		
		cbbUserGroup.addItem("All");
		for (UserGroup userGroup : userGroups) {
			cbbUserGroup.addItem(userGroup.getName());
		}
		
		cbbUserGroup.setSelectedItem(idx);
	}
	
	public void loadTable(){
		tblEmployees.setModel(
        	new DefaultTableModel(
        			employeeController.loadTableRows(sldNumberResults.getValue()) ,
        			new String [] {
        				"ID Card", "Name", "Division", "UserGroup", "Salary Category", "Last Modified", "Modified By"
        			})
	        {
				private static final long serialVersionUID = 1L;
	
				@Override
		        public boolean isCellEditable(int row, int column) {
		        	return false;
		        }
	        }
    	);
		lblPage.setText("Page " + (employeeController.getPage().getNumber() +1) + "/" + employeeController.getPage().getTotalPages());
	}
	
	private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnNew;
    private JButton btnNext;
    private JButton btnPrevious;
    private JPanel pnlCenterNewR;
    private JPanel pnlCenterNewL;
    
    private JScrollPane pnlTblEmployees;
    private JTable tblEmployees;
    
    private JPanel pnlSearch;
    private JTextField txtSearch;
    private JComboBox<String> cbbUserGroup;
	private JLabel lblDivision;
	private JComboBox<String> cbbDivision;
	private JLabel lblSearch;
	
	private JLabel lblSlider;
	private JSlider sldNumberResults;
	private JLabel lblPage;
	
	public JLabel lblUserGroup;
	public JLabel lblSalaryCategory;
	public JComboBox<String> cbbSalaryCategory;
}
