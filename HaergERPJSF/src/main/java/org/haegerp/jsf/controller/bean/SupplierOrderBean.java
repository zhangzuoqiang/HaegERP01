package org.haegerp.jsf.controller.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.haegerp.controller.ArticleController;
import org.haegerp.controller.ArticleHistoryController;
import org.haegerp.controller.EmployeeController;
import org.haegerp.controller.SupplierController;
import org.haegerp.controller.SupplierOrderController;
import org.haegerp.controller.SupplierOrderDetailController;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.haegerp.entity.Employee;
import org.haegerp.entity.Supplier;
import org.haegerp.entity.SupplierBill;
import org.haegerp.entity.SupplierOrder;
import org.haegerp.entity.SupplierOrderDetailPK;
import org.haegerp.jsf.controller.form.FormSupplierOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean für die Seiten "SupplierOrderManagement" und "Details"
 *
 * @author Fábio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class SupplierOrderBean implements Serializable {

    @Autowired
    private SupplierOrderController supplierOrderController;
    @Autowired
    private SupplierOrderDetailController supplierOrderDetailController;
    @Autowired
    private ArticleController articleController;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private SupplierController supplierController;
    @Autowired
    private ArticleHistoryController articleHistoryController;
    //Lieferantenbestellung, der in der Seite der Details zeigen wird
    private SupplierOrder supplierOrder;
    //Lieferant der Bestellung
    private Supplier supplier;
    //Rechnung der Bestellung
    private SupplierBill supplierBill;
    //Mitarbeiter, der die Rechnung erstellt hat
    private Employee employee;
    //Hilfsvariable für die Methode, die eine Lieferantenbestellung löschen.
    private long supplierOrderId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormSupplierOrder formSupplierOrder;
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] supplierOrderObjects;
    //Wie viel Lieferantenbestellung wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingefügt hat
    private String search;

    /**
     * Defaultwert
     */
    public SupplierOrderBean() {
        pageSize = 10;
    }

    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp() {
        supplierOrderObjects = supplierOrderController.loadTableRows(pageSize);
        supplierOrder = new SupplierOrder();
        formSupplierOrder = new FormSupplierOrder(false);
        formSupplierOrder.setTblChooserSuppliers(supplierController.loadAllTableRows(0, "", 1));
        formSupplierOrder.setTblChooserArticles(articleController.loadAllTableRows(0, "", 1));
    }

    /**
     * Wenn der Benutzer die Suche benutzen möchte, wird diese Methode gerufen
     */
    public void setUpSearch() {
        supplierOrderController.setSearch(search, pageSize);
        supplierOrderObjects = supplierOrderController.loadTableRows(pageSize);
    }

    /**
     * Die Seite der Details wird vorbereitet.
     *
     * @param id ID der Lieferantenbestellung
     * @param disabled True - Die Lieferantenbestellung wird nur gezeigt; False
     * - Die Lieferantenbestellung kann geändert werden.
     * @return Wenn die ID gültig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        supplierOrder = supplierOrderController.getSupplierOrderById(id);
        if (supplierOrder != null) {
            //Lazy Initialization
            //Lieferant
            supplier = supplierController.getSupplierById(supplierOrder.getSupplier().getIdBusinessPartner());
            supplierOrder.setSupplier(supplier);
            //Mitarbeiter
            employee = employeeController.getEmployeeById(supplierOrder.getEmployee().getIdEmployee());
            supplierOrder.setEmployee(employee);
            //Rechnung
            if (supplierOrder.getSupplierBill() != null) {
                supplierBill = supplierOrderController.getSupplierBillById(supplierOrder.getSupplierBill().getIdSupplierBill());
                supplierOrder.setSupplierBill(supplierBill);
            }
            formSupplierOrder = new FormSupplierOrder(supplierOrder, disabled);
            formSupplierOrder.setTblArticles(supplierOrderDetailController.loadTableRows(id));
            return "supplierOrderDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Supplier Order was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }

    /**
     * Die Seite der Details wird eine neue Lieferantenbestellung zu erstellen
     * vorbereitet
     *
     * @return Seite Details
     */
    public String prepareNew() {
        supplierOrder = new SupplierOrder();
        formSupplierOrder = new FormSupplierOrder(false);
        supplier = null;
        supplierBill = null;
        employee = null;
        return "supplierOrderDetails?faces-redirect=true";
    }

    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt
     * hat
     *
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed() {
        if (formSupplierOrder.isDisabled() || supplierOrder.getIdSupplierOrder() == 0) {
            return "supplierOrderManagement?faces-redirect=true";
        } else {
            formSupplierOrder = new FormSupplierOrder(supplierOrder, true);
            formSupplierOrder.setTblArticles(supplierOrderDetailController.loadTableRows(supplierOrder.getIdSupplierOrder()));
            return "supplierOrderDetails?faces-redirect=true";
        }
    }

    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert.
     * Sonst versucht das System die Lieferantenbestellung in der Datenbank zu
     * speichern
     */
    public void btnEditSave_ActionPerformed() {
        if (formSupplierOrder.isDisabled()) {
            formSupplierOrder = new FormSupplierOrder(supplierOrder, false);
            formSupplierOrder.setTblArticles(supplierOrderDetailController.loadTableRows(supplierOrder.getIdSupplierOrder()));
        } else {
            long id = 0;
            try {
                id = doSave();
                setUpSearch();
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
            if (id != 0) {
                prepareView(id, true);
            }
        }
    }

    /**
     * Eine Lieferantenbestellung wird in der Datanbank gespeichern
     *
     * @return ID der Lieferantenbestellung
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme
     * geworfen.
     */
    public long doSave() throws Exception {
        if (validateSupplier()) {
            supplierOrder.setSupplier(supplier);
            supplierOrder.setDescription(formSupplierOrder.getTxtDescription());
            supplierOrder.setSupplierBill(supplierBill);

            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            Long idEmployee = (Long) session.getAttribute("idemployee");

            if (supplierOrder.getIdSupplierOrder() == 0) {
                supplierOrder.setOrderDate(new Date());
                supplierOrder.setEmployee(employeeController.getEmployeeById(idEmployee));
                supplierOrder.setIdEmployeeModify(idEmployee);
                supplierOrder.setLastModifiedDate(new Date());
                supplierOrder = supplierOrderController.newSupplierOrder(supplierOrder);
            }

            if (formSupplierOrder.getTblArticles() != null) {
                Object[][] values = new Object[formSupplierOrder.getTblArticles().length][3];

                for (int i = 0; i < formSupplierOrder.getTblArticles().length; i++) {
                    values[i][0] = formSupplierOrder.getTblArticles()[i][0];
                    values[i][1] = formSupplierOrder.getTblArticles()[i][4];
                    values[i][2] = formSupplierOrder.getTblArticles()[i][5];
                }
                supplierOrderDetailController.deleteAllArticles(supplierOrder.getIdSupplierOrder());

                supplierOrder.setSupplierOrderDetail(supplierOrderDetailController.doUpdateOrderArticle(values, supplierOrder.getIdSupplierOrder()));
            }

            supplierOrder.setIdEmployeeModify(idEmployee);
            supplierOrder.setLastModifiedDate(new Date());

            SupplierOrder newSupplierOrder = supplierOrderController.updateSupplierOrder(supplierOrder);

            return newSupplierOrder.getIdSupplierOrder();
        } else {
            return 0;
        }
    }

    /**
     * Das System versucht eine Lieferantenbestellung zu löschen.
     */
    public void delete() {
        SupplierOrder deleteSupplierOrder = supplierOrderController.getSupplierOrderById(supplierOrderId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteSupplierOrder != null) {
            try {
                if (deleteSupplierOrder.getSendDate() == null) {
                    supplierOrderDetailController.deleteAllArticles(deleteSupplierOrder.getIdSupplierOrder());
                    supplierOrderController.delete(deleteSupplierOrder);
                    severity = FacesMessage.SEVERITY_INFO;
                    msg = "Selected Supplier Order was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "The selected Supplier Order was already sent.";
                }
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Supplier Order was not found in the Database.";
        }

        fMessage = new FacesMessage(severity, msg, null);

        FacesContext.getCurrentInstance().addMessage(null, fMessage);
        setUp();
        setUpSearch();
    }

    /**
     * Die vorige Seite wird geladen
     */
    public void previousPage() {
        if (supplierOrderController.getPreviousPage(pageSize)) {
            supplierOrderObjects = supplierOrderController.loadTableRows(pageSize);
        }
    }

    /**
     * Die nächste Seite wird geladen
     */
    public void nextPage() {
        if (supplierOrderController.getNextPage(pageSize)) {
            supplierOrderObjects = supplierOrderController.loadTableRows(pageSize);
        }
    }

    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     *
     * @return Der Wert zu zeigen
     */
    public String getPageNumber() {
        return (supplierOrderController.getPage().getNumber() + 1) + " / " + supplierOrderController.getPage().getTotalPages();
    }

    /**
     * Wenn der Benutzer den 'Suchen' Knopf druckt
     */
    public void supplierChooserSearch() {
        if (formSupplierOrder.getTxtSearchSupplier().equals("")) {
            formSupplierOrder.setTblChooserSuppliers(supplierController.loadAllTableRows(0, "", 1));
        } else {
            formSupplierOrder.setTblChooserSuppliers(supplierController.loadAllTableRows(1, formSupplierOrder.getTxtSearchSupplier(), 0));
        }
    }

    /**
     * Wenn der Benutzer einen Lieferanten auswählt
     *
     * @param idSupplier ID des Lieferanten
     */
    public void supplierChooserSelect(long idSupplier) {
        supplier = supplierController.getSupplierById(idSupplier);
        formSupplierOrder.setTxtSupplierName(supplier.getName());
    }

    /**
     * Wenn der Benutzer den 'Suchen' Knopf druckt
     */
    public void articleChooserSearch() {
        if (formSupplierOrder.getTxtSearchArticle().equals("")) {
            formSupplierOrder.setTblChooserArticles(articleController.loadAllTableRows(0, "", 1));
        } else {
            formSupplierOrder.setTblChooserArticles(articleController.loadAllTableRows(1, formSupplierOrder.getTxtSearchArticle(), 0));
        }
    }

    /**
     * Wenn der Benutzer einen Artikel auswählt
     *
     * @param idArticle ID des Artikels
     */
    public void articleChooserSelect(long idArticle) {
        Article article = articleController.getArticleById(idArticle);

        long idArticleHistory = articleHistoryController.getLastVersion(idArticle);

        ArticleHistoryPK articleHistoryPK = new ArticleHistoryPK(idArticleHistory, article);
        ArticleHistory articleHistory = articleHistoryController.getArticleHistoryById(articleHistoryPK);

        SupplierOrderDetailPK detailPK = new SupplierOrderDetailPK(supplierOrder, articleHistory);
        int x = 1;
        int i = 0;
        Object[][] newModel;

        if (formSupplierOrder.getTblArticles() != null) {
            for (int j = 0; j < formSupplierOrder.getTblArticles().length; j++) {
                SupplierOrderDetailPK supplierOrderDetailPK = (SupplierOrderDetailPK) formSupplierOrder.getTblArticles()[j][0];

                if (supplierOrderDetailPK.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == idArticle
                        && supplierOrderDetailPK.getArticleHistory().getArticleHistoryPK().getIdArticleHistory() == idArticleHistory) {
                    long quantity = Long.parseLong(String.valueOf(formSupplierOrder.getTblArticles()[j][4]));
                    formSupplierOrder.getTblArticles()[j][4] = quantity + 1;
                    return;
                }
            }
            x = formSupplierOrder.getTblArticles().length + 1;
            newModel = new Object[x][7];
            for (i = 0; i < formSupplierOrder.getTblArticles().length; i++) {
                newModel[i] = formSupplierOrder.getTblArticles()[i];
            }
        } else {
            newModel = new Object[x][7];
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

        newModel[i] = row;

        formSupplierOrder.setTblArticles(newModel);
    }

    /**
     * Lieferant wurde ausgewählt
     *
     * @return True - Wenn der Lieferant ausgewählt wurde; False - Sonst.
     */
    public boolean validateSupplier() {
        if (supplier == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "A Supplier must be picked.",
                    "A Supplier must be picked.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        return supplier != null;
    }

    /**
     * Als 'geschickt' markieren
     */
    public void markAsSended() {
        if (!formSupplierOrder.isDisabledSendDate()) {
            Date date = new Date();
            formSupplierOrder.setTxtSendDate(new SimpleDateFormat("dd-MM-yyyy HH:mm")
                    .format(date));
            supplierOrder.setSendDate(date);
            btnEditSave_ActionPerformed();
        }
    }

    /**
     * Die Rechnung wird bekommen
     */
    public void markAsBillReceived() {
        Date date = new Date();
        formSupplierOrder.setTxtBillReceived(new SimpleDateFormat("dd-MM-yyyy HH:mm")
                .format(date));
        SupplierBill newSupplierBill = new SupplierBill();
        newSupplierBill.setReceivedDate(date);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        newSupplierBill.setIdEmployeeModify(idEmployee);
        newSupplierBill.setLastModifiedDate(new Date());
        supplierBill = supplierOrderController.saveBill(newSupplierBill);
        supplierOrder.setSupplierBill(supplierBill);
        btnEditSave_ActionPerformed();
    }

    /**
     * Die Rechnung wurde bezahlt
     */
    public void markAsBillPaid() {
        Date date = new Date();
        formSupplierOrder.setTxtBillPaid(new SimpleDateFormat("dd-MM-yyyy HH:mm")
                .format(date));
        SupplierBill editSupplierBill = supplierOrderController.getSupplierBillById(supplierOrder.getSupplierBill().getIdSupplierBill());
        editSupplierBill.setPaidDate(date);

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        editSupplierBill.setIdEmployeeModify(idEmployee);
        editSupplierBill.setLastModifiedDate(new Date());
        supplierBill = supplierOrderController.saveBill(editSupplierBill);
        supplierOrder.setSupplierBill(supplierBill);
        btnEditSave_ActionPerformed();
    }

    /**
     * Ein Artikel der bestellung wird gelöscht
     *
     * @param supplierOrderDetail ID des Artikels
     */
    public void deleteArticle(SupplierOrderDetailPK supplierOrderDetail) {
        long deleteArticleId = supplierOrderDetail.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle();

        Object[][] oldTblArticles = formSupplierOrder.getTblArticles();
        Object[][] newTblArticles = new Object[oldTblArticles.length - 1][7];
        int x = 0;
        for (int i = 0; i < oldTblArticles.length; i++) {
            long actualArticleId = ((SupplierOrderDetailPK) oldTblArticles[i][0]).getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle();
            if (actualArticleId != deleteArticleId) {
                newTblArticles[x] = oldTblArticles[i];
                x++;
            }
        }

        formSupplierOrder.setTblArticles(newTblArticles);
    }

    /**
     * @return the supplierOrderObjects
     */
    public Object[][] getSupplierOrderObjects() {
        return supplierOrderObjects;
    }

    /**
     * @param supplierOrderObjects the supplierOrderObjects to set
     */
    public void setSupplierOrderObjects(Object[][] supplierOrderObjects) {
        this.supplierOrderObjects = supplierOrderObjects;
    }

    /**
     * @return the formSupplierOrder
     */
    public FormSupplierOrder getFormSupplierOrder() {
        return formSupplierOrder;
    }

    /**
     * @param formSupplierOrder the formSupplierOrder to set
     */
    public void setFormSupplierOrder(FormSupplierOrder formSupplierOrder) {
        this.formSupplierOrder = formSupplierOrder;
    }

    /**
     * @return the supplierOrderId
     */
    public long getSupplierOrderId() {
        return supplierOrderId;
    }

    /**
     * @param supplierOrderId the supplierOrderId to set
     */
    public void setSupplierOrderId(long supplierOrderId) {
        this.supplierOrderId = supplierOrderId;
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
