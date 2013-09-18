package org.haegerp.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

import org.haegerp.controller.ArticleCategoryController;
import org.haegerp.entity.ArticleCategory;
import org.haegerp.gui.articlecategorydetails.ArticleCategoryDetailsInterface;
import org.haegerp.gui.articlecategorydetails.ArticleCategoryEditView;
import org.haegerp.gui.articlecategorydetails.ArticleCategoryNewView;
import org.haegerp.gui.articlecategorydetails.ArticleCategoryShowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * Dieses Formular wird die Details einer Artikelkategorie kontrollieren
 *
 * @author Fabio Codinha
 */
public class ArticleCategoryDetails extends javax.swing.JFrame {

    private static final long serialVersionUID = 2949647041784163844L;
    //Formulare
    @Autowired
    private ArticleManagement articleManagement;

    public ArticleManagement getArticleManagement() {
        return articleManagement;
    }
    @Autowired
    private ArticleDetails articleDetails;

    public ArticleDetails getArticleDetails() {
        return articleDetails;
    }
    @Autowired
    private ArticleCategoryManagement articleCategoryManagement;

    public ArticleCategoryManagement getArticleCategoryManagement() {
        return articleCategoryManagement;
    }
    //Controller
    @Autowired
    private ArticleCategoryController articleCategoryController;

    public ArticleCategoryController getArticleCategoryController() {
        return articleCategoryController;
    }
    //Verschiednen Stände von der Oberfläche
    private ArticleCategoryDetailsInterface articleCategoryDetailsView;
    //Artikelkateogrie, die gezeigt wird
    private ArticleCategory articleCategory;

    public ArticleCategory getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(ArticleCategory articleCategory) {
        this.articleCategory = articleCategory;
    }

    public ArticleCategoryDetails() {
    }

    //Wenn der Benutzer eine neue Kategorie erstellen möchte
    public void setNewMode() {
        articleCategoryDetailsView = new ArticleCategoryNewView();
        articleCategoryDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Kategorie ändern möchte
    public void setEditMode() {
        articleCategoryDetailsView = new ArticleCategoryEditView();
        articleCategoryDetailsView.applyView(this);
    }

    //Wenn der Benutzer eine Kategorie ansehen möchte
    public void setShowMode() {
        articleCategoryDetailsView = new ArticleCategoryShowView();
        articleCategoryDetailsView.applyView(this);
    }

    //Listeners
    /**
     * Wenn der Benutzer nur ansehen kann, wechselt der Knopf zur
     * Änderungsmodus.<br/>
     * Wenn der Benutzer die Felder ändern kann, wird die Artikelkategorie
     * gespeichert.<br/>
     * Diese Betrieb wird bei der Variable 'articleCategoryDetailsView'
     * kontrolliert.
     *
     * @param e ActionEvent Werte
     */
    protected void btnSaveEdit_ActionPerformed(ActionEvent e) {
        String errors = "";
        if (!(articleCategoryDetailsView instanceof ArticleCategoryShowView)) {
            errors = checkFields();
        }
        if (errors.equals("")) {
            articleCategoryDetailsView.btnSaveEdit(this);
        } else {
            JOptionPane.showMessageDialog(this, "The following fields have not been filled:\n" + errors + "\nThose fields are required.", "", JOptionPane.ERROR_MESSAGE);
        }
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

    /**
     * Wenn der Benutzer nicht die Änderungen speichern möchte, wird die
     * Kategorie wieder zum Formular geladen
     *
     * @param e ActionEvent Werte
     */
    protected void btnCancel_ActionPerformed(ActionEvent e) {
        articleCategoryDetailsView.btnCancel(this);
    }

    /**
     * Das Formular wird vorbereitet
     */
    @PostConstruct
    private void setUp() {
        lblCountArticles = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();

        txtCountArticles = new javax.swing.JFormattedTextField();
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

        lblCountArticles.setText("N. Articles");

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

        txtCountArticles.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

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
                .addComponent(lblCountArticles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addComponent(lblName)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel lblName;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea txtDescription;
    public javax.swing.JFormattedTextField txtCountArticles;
    public javax.swing.JTextField txtName;
    public javax.swing.JPanel pnlLeft;
    public javax.swing.JPanel pnlCenter;
    public javax.swing.JPanel pnlRight;
}
