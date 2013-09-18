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
import org.haegerp.controller.ClientController;
import org.haegerp.controller.ClientOfferController;
import org.haegerp.controller.ClientOfferDetailController;
import org.haegerp.controller.EmployeeController;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.haegerp.entity.Client;
import org.haegerp.entity.ClientBill;
import org.haegerp.entity.ClientOffer;
import org.haegerp.entity.ClientOfferDetailPK;
import org.haegerp.entity.Employee;
import org.haegerp.entity.SupplierOrderDetailPK;
import org.haegerp.jsf.controller.form.FormClientOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean f�r die Seiten "ClientOfferManagement" und "Details" (Noch Nicht Fertig)
 *
 * @author F�bio Codinha
 */
@ManagedBean
@Controller
@Scope(value = "session")
public class ClientOfferBean implements Serializable {

    @Autowired
    private ClientOfferController clientOfferController;
    @Autowired
    private ClientOfferDetailController clientOfferDetailController;
    @Autowired
    private ArticleController articleController;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private ClientController clientController;
    @Autowired
    private ArticleHistoryController articleHistoryController;
    //Kundenangebot, das in der Seite der Details zeigen wird
    private ClientOffer clientOffer;
    //Kunde der Bestellung
    private Client client;
    //Rechnung des Angebots
    private ClientBill clientBill;
    //Mitarbeiter, der das Angebot erstellt hat
    private Employee employee;
    //Hilfsvariable f�r die Methode, die eine Kundenbestellung l�schen.
    private long clientOrderId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormClientOffer formClientOffer;
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] clientOfferObjects;
    //Wie viel Lieferantenbestellung wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingef�gt hat
    private String search;

    /**
     * Defaultwert
     */
    public ClientOfferBean() {
        pageSize = 10;
    }

    /**
     * Diese Methode l�dt die Daten und bereitet das Formular vor
     */
    @PostConstruct
    public void setUp() {
        clientOfferObjects = clientOfferController.loadTableRows(pageSize);
        clientOffer = new ClientOffer();
        formClientOffer = new FormClientOffer(false);
        formClientOffer.setTblChooserClients(clientController.loadAllTableRows(0, "", 1));
        formClientOffer.setTblChooserArticles(articleController.loadAllTableRows(0, "", 1));
    }

    /**
     * Wenn der Benutzer die Suche benutzen m�chte, wird diese Methode gerufen
     */
    public void setUpSearch() {
        clientOfferController.setSearch(search, pageSize);
        clientOfferObjects = clientOfferController.loadTableRows(pageSize);
    }

    /**
     * Die Seite der Details wird vorbereitet.
     *
     * @param id ID des Kundenangebots
     * @param disabled True - Das Kundenangebot wird nur gezeigt; False - Das
     * Kundenangebot kann ge�ndert werden.
     * @return Wenn die ID g�ltig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        //TODO: Controller muss ein Angebot von der Datenbank holen
        //clientOffer = clientOfferController.getClientOfferById(id);
        if (clientOffer != null) {
            //Lazy Initialization
            //Kunde
            client = clientController.getClientById(clientOffer.getClient().getIdBusinessPartner());
            clientOffer.setClient(client);
            //Mitarbeiter
            employee = employeeController.getEmployeeById(clientOffer.getEmployee().getIdEmployee());
            clientOffer.setEmployee(employee);
            //Rechnung
            if (clientOffer.getClientBill() != null) {
                clientBill = clientOfferController.getClientBillById(clientOffer.getClientBill().getIdClientBill());
                clientOffer.setClientBill(clientBill);
            }
            formClientOffer = new FormClientOffer(clientOffer, disabled);
            formClientOffer.setTblArticles(clientOfferDetailController.loadTableRows(id));
            return "clientOfferDetails?faces-redirect=true";
        } else {
            FacesMessage fMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Client Offer was not found in the database", null);
            FacesContext.getCurrentInstance().addMessage(null, fMessage);
            setUp();
            setUpSearch();
            return "";
        }
    }

    /**
     * Die Seite der Details wird ein neues Kundenangebot zu erstellen
     * vorbereitet
     *
     * @return Seite Details
     */
    public String prepareNew() {
        clientOffer = new ClientOffer();
        formClientOffer = new FormClientOffer(false);
        client = null;
        clientBill = null;
        employee = null;
        return "clientOfferDetails?faces-redirect=true";
    }

    /**
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedr�ckt
     * hat
     *
     * @return Zu welcher Seite wird der Benutzer gef�hrt
     */
    public String btnCancel_ActionPerformed() {
        if (formClientOffer.isDisabled() || clientOffer.getIdClientOffer() == 0) {
            return "clientOrderManagement?faces-redirect=true";
        } else {
            formClientOffer = new FormClientOffer(clientOffer, true);
            formClientOffer.setTblArticles(clientOfferDetailController.loadTableRows(clientOffer.getIdClientOffer()));
            return "clientOfferDetails?faces-redirect=true";
        }
    }

    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung ge�ndert.
     * Sonst versucht das System das Kundenangebot in der Datenbank zu speichern
     */
    public void btnEditSave_ActionPerformed() {
        if (formClientOffer.isDisabled()) {
            formClientOffer = new FormClientOffer(clientOffer, false);
            formClientOffer.setTblArticles(clientOfferDetailController.loadTableRows(clientOffer.getIdClientOffer()));
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
     * Ein Kundenangebot wird in der Datanbank gespeichern
     *
     * @return ID des Kundenangebots
     * @throws Exception Wenn etwas nicht Normal geht, wird eine Ausnahme
     * geworfen.
     */
    public long doSave() throws Exception {
        if (validateClient()) {
            clientOffer.setClient(client);
            clientOffer.setDescription(formClientOffer.getTxtDescription());
            clientOffer.setClientBill(clientBill);

            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            Long idEmployee = (Long) session.getAttribute("idemployee");

            if (clientOffer.getIdClientOffer() == 0) {
                clientOffer.setOfferDate(new Date());
                clientOffer.setEmployee(employeeController.getEmployeeById(idEmployee));
                clientOffer.setIdEmployeeModify(idEmployee);
                clientOffer.setLastModifiedDate(new Date());
                clientOffer = clientOfferController.newClientOffer(clientOffer);
            }

            if (formClientOffer.getTblArticles() != null) {
                Object[][] values = new Object[formClientOffer.getTblArticles().length][3];

                for (int i = 0; i < formClientOffer.getTblArticles().length; i++) {
                    values[i][0] = formClientOffer.getTblArticles()[i][0];
                    values[i][1] = formClientOffer.getTblArticles()[i][4];
                    values[i][2] = formClientOffer.getTblArticles()[i][5];
                }
                clientOfferDetailController.deleteAllArticles(clientOffer.getIdClientOffer());
                //TODO: Wie Lieferantenbestellung
                //clientOffer.setClientOfferDetail(clientOfferDetailController.doUpdateOfferArticle(values, clientOffer.getIdClientOffer()));
            }

            clientOffer.setIdEmployeeModify(idEmployee);
            clientOffer.setLastModifiedDate(new Date());

            ClientOffer newClientOffer = clientOfferController.updateClientOffer(clientOffer);

            return newClientOffer.getIdClientOffer();
        } else {
            return 0;
        }
    }

    /**
     * Das System versucht ein Kundenangebot zu l�schen.
     */
    public void delete() {
        //TODO: Controller muss ein Angebot von der Datenbank holen
        //ClientOffer deleteClientOffer = clientOfferController.getClientOfferById(clientOrderId);
        ClientOffer deleteClientOffer = null;
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteClientOffer != null) {
            try {
                if (deleteClientOffer.getSendDate() == null) {
                    clientOfferController.delete(deleteClientOffer);
                    severity = FacesMessage.SEVERITY_INFO;
                    msg = "Selected Client Offer was deleted.";
                } else {
                    severity = FacesMessage.SEVERITY_WARN;
                    msg = "The selected Client Offer was already sent.";
                }
            } catch (Exception e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                severity = FacesMessage.SEVERITY_FATAL;
                msg = "Error ocurred: " + e.getMessage();
            }
        } else {
            severity = FacesMessage.SEVERITY_ERROR;
            msg = "Client Offer was not found in the Database.";
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
        if (clientOfferController.getPreviousPage(pageSize)) {
            clientOfferObjects = clientOfferController.loadTableRows(pageSize);
        }
    }

    /**
     * Die n�chste Seite wird geladen
     */
    public void nextPage() {
        if (clientOfferController.getNextPage(pageSize)) {
            clientOfferObjects = clientOfferController.loadTableRows(pageSize);
        }
    }

    /**
     * Die Nummer der aktuele Seite und der Seitentotal wird gezeigt
     *
     * @return Der Wert zu zeigen
     */
    public String getPageNumber() {
        return (clientOfferController.getPage().getNumber() + 1) + " / " + clientOfferController.getPage().getTotalPages();
    }

    /**
     * Wenn der Benutzer den 'Suchen' Knopf druckt
     */
    public void clientChooserSearch() {
        if (formClientOffer.getTxtSearchClient().equals("")) {
            formClientOffer.setTblChooserClients(clientController.loadAllTableRows(0, "", 1));
        } else {
            formClientOffer.setTblChooserClients(clientController.loadAllTableRows(1, formClientOffer.getTxtSearchClient(), 0));
        }
    }

    /**
     * Wenn der Benutzer einen Kunden ausw�hlt
     *
     * @param idClient ID des Kunden
     */
    public void clientChooserSelect(long idClient) {
        client = clientController.getClientById(idClient);
        formClientOffer.setTxtClientName(client.getName());
    }

    /**
     * Wenn der Benutzer den 'Suchen' Knopf druckt
     */
    public void articleChooserSearch() {
        if (formClientOffer.getTxtSearchArticle().equals("")) {
            formClientOffer.setTblChooserArticles(articleController.loadAllTableRows(0, "", 1));
        } else {
            formClientOffer.setTblChooserArticles(articleController.loadAllTableRows(1, formClientOffer.getTxtSearchArticle(), 0));
        }
    }

    /**
     * Wenn der Benutzer einen Artikel ausw�hlt
     *
     * @param idArticle ID des Artikels
     */
    public void articleChooserSelect(long idArticle) {
        Article article = articleController.getArticleById(idArticle);

        long idArticleHistory = articleHistoryController.getLastVersion(idArticle);

        ArticleHistoryPK articleHistoryPK = new ArticleHistoryPK(idArticleHistory, article);
        ArticleHistory articleHistory = articleHistoryController.getArticleHistoryById(articleHistoryPK);

        ClientOfferDetailPK detailPK = new ClientOfferDetailPK(clientOffer, articleHistory);

        for (int i = 0; i < formClientOffer.getTblArticles().length; i++) {
            SupplierOrderDetailPK supplierOrderDetailPK = (SupplierOrderDetailPK) formClientOffer.getTblArticles()[i][0];

            if (supplierOrderDetailPK.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == idArticle
                    && supplierOrderDetailPK.getArticleHistory().getArticleHistoryPK().getIdArticleHistory() == idArticleHistory) {
                long quantity = Long.parseLong(String.valueOf(formClientOffer.getTblArticles()[i][4]));
                formClientOffer.getTblArticles()[i][4] = quantity + 1;
                return;
            }
        }

        Object[][] newModel = new Object[formClientOffer.getTblArticles().length + 1][7];
        int i;
        for (i = 0; i < formClientOffer.getTblArticles().length; i++) {
            newModel[i] = formClientOffer.getTblArticles()[i];
        }

        Object[] row = {
            detailPK,
            articleHistory.getEan(),
            articleHistory.getName(),
            articleHistory.getPriceSupplier() + " �",
            "1",
            "0",
            articleHistory.getPriceSupplier() + " �"
        };

        newModel[i] = row;

        formClientOffer.setTblArticles(newModel);
    }

    /**
     * Ein Kunde muss ausgew�hlt werden
     *
     * @return True - Wenn der Kunde ausgew�hlt wurde; False - Sonst.
     */
    public boolean validateClient() {
        if (client == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "A Client must be picked.",
                    "A Client must be picked.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        return client != null;
    }

    /**
     * Als 'geschickt' markieren
     */
    public void markAsSended() {
        if (!formClientOffer.isDisabledSendDate()) {
            Date date = new Date();
            formClientOffer.setTxtSendDate(new SimpleDateFormat("dd-MM-yyyy HH:mm")
                    .format(date));
            clientOffer.setSendDate(date);
            btnEditSave_ActionPerformed();
        }
    }

    /**
     * Die Rechnung wird gemacht
     */
    public void markAsBilled() {
        Date date = new Date();
        formClientOffer.setTxtBillReceived(new SimpleDateFormat("dd-MM-yyyy HH:mm")
                .format(date));
        ClientBill newClientBill = new ClientBill();
        newClientBill.setBilledDate(date);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        newClientBill.setIdEmployeeModify(idEmployee);
        newClientBill.setLastModifiedDate(new Date());
        clientBill = clientOfferController.saveBill(newClientBill);
        clientOffer.setClientBill(clientBill);
        btnEditSave_ActionPerformed();
    }

    /**
     * Die Rechnung wurde bezahlt
     */
    public void markAsBillPaid() {
        Date date = new Date();
        formClientOffer.setTxtBillPaid(new SimpleDateFormat("dd-MM-yyyy HH:mm")
                .format(date));
        ClientBill editClientBill = clientOfferController.getClientBillById(clientOffer.getClientBill().getIdClientBill());
        editClientBill.setPaidDate(date);

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        Long idEmployee = (Long) session.getAttribute("idemployee");

        editClientBill.setIdEmployeeModify(idEmployee);
        editClientBill.setLastModifiedDate(new Date());
        clientBill = clientOfferController.saveBill(editClientBill);
        clientOffer.setClientBill(clientBill);
        btnEditSave_ActionPerformed();
    }

    /**
     * Ein Artikel des Angebots wird gel�scht
     *
     * @param supplierOrderDetail ID des Artikels
     */
    public void deleteArticle(ClientOfferDetailPK clientOfferDetail) {
        long deleteArticleId = clientOfferDetail.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle();

        Object[][] oldTblArticles = formClientOffer.getTblArticles();
        Object[][] newTblArticles = new Object[oldTblArticles.length - 1][7];
        int x = 0;
        for (int i = 0; i < oldTblArticles.length; i++) {
            long actualArticleId = ((SupplierOrderDetailPK) oldTblArticles[i][0]).getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle();
            if (actualArticleId != deleteArticleId) {
                newTblArticles[x] = oldTblArticles[i];
                x++;
            }
        }

        formClientOffer.setTblArticles(newTblArticles);
    }
}
