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
import org.haegerp.jsf.controller.form.FormClientOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Bean für die Seiten "ClientOfferManagement" und "Details"
 *
 * @author Fábio Codinha
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
    //Hilfsvariable für die Methode, die eine Kundenbestellung löschen.
    private long clientOfferId;
    //Klasse, wo die Felder von dem Formular gespeichert werden
    private FormClientOffer formClientOffer;
    //Object, dass der Inhalt von der Tabelle hat
    private Object[][] clientOfferObjects;
    //Wie viel Lieferantenbestellung wurde in einer Seite gezeigt
    private int pageSize;
    //Die Suche, die der Benutzer eingefügt hat
    private String search;

    /**
     * Defaultwert
     */
    public ClientOfferBean() {
        pageSize = 10;
    }

    /**
     * Diese Methode lädt die Daten und bereitet das Formular vor
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
     * Wenn der Benutzer die Suche benutzen möchte, wird diese Methode gerufen
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
     * Kundenangebot kann geändert werden.
     * @return Wenn die ID gültig ist, dann die Seite der Details wird geladen
     */
    public String prepareView(long id, boolean disabled) {
        clientOffer = clientOfferController.getClientOfferById(id);
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
     * Dieser Betrieb wird gemacht, wenn der Benutzer im Knopf "Cancel" gedrückt
     * hat
     *
     * @return Zu welcher Seite wird der Benutzer geführt
     */
    public String btnCancel_ActionPerformed() {
        if (formClientOffer.isDisabled() || clientOffer.getIdClientOffer() == 0) {
            return "clientOfferManagement?faces-redirect=true";
        } else {
            formClientOffer = new FormClientOffer(clientOffer, true);
            formClientOffer.setTblArticles(clientOfferDetailController.loadTableRows(clientOffer.getIdClientOffer()));
            return "clientOfferDetails?faces-redirect=true";
        }
    }

    /**
     * Wenn die Seite in Zeigen Modus ist, dann wird sie zur Anderung geändert.
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
                    values[i][1] = formClientOffer.getTblArticles()[i][5];
                    values[i][2] = formClientOffer.getTblArticles()[i][6];
                }
                clientOfferDetailController.deleteAllArticles(clientOffer.getIdClientOffer());
                clientOffer.setClientOfferDetail(clientOfferDetailController.doUpdateOfferArticle(values, clientOffer.getIdClientOffer()));
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
     * Das System versucht ein Kundenangebot zu löschen.
     */
    public void delete() {
        ClientOffer deleteClientOffer = clientOfferController.getClientOfferById(clientOfferId);
        FacesMessage fMessage;
        FacesMessage.Severity severity;
        String msg;
        if (deleteClientOffer != null) {
            try {
                if (deleteClientOffer.getSendDate() == null) {
                    clientOfferDetailController.deleteAllArticles(deleteClientOffer.getIdClientOffer());
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
     * Die nächste Seite wird geladen
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
     * Wenn der Benutzer einen Kunden auswählt
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
     * Wenn der Benutzer einen Artikel auswählt
     *
     * @param idArticle ID des Artikels
     */
    public void articleChooserSelect(long idArticle) {
        Article article = articleController.getArticleById(idArticle);

        long idArticleHistory = articleHistoryController.getLastVersion(idArticle);

        ArticleHistoryPK articleHistoryPK = new ArticleHistoryPK(idArticleHistory, article);
        ArticleHistory articleHistory = articleHistoryController.getArticleHistoryById(articleHistoryPK);

        ClientOfferDetailPK detailPK = new ClientOfferDetailPK(clientOffer, articleHistory);
        int x = 1;
        int i = 0;
        Object[][] newModel;

        if (formClientOffer.getTblArticles() != null) {
            for (int j = 0; j < formClientOffer.getTblArticles().length; j++) {
                ClientOfferDetailPK clientOfferDetailPK = (ClientOfferDetailPK) formClientOffer.getTblArticles()[j][0];

                if (clientOfferDetailPK.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle() == idArticle
                        && clientOfferDetailPK.getArticleHistory().getArticleHistoryPK().getIdArticleHistory() == idArticleHistory) {
                    long quantity = Long.parseLong(String.valueOf(formClientOffer.getTblArticles()[j][5]));
                    formClientOffer.getTblArticles()[j][5] = quantity + 1;
                    return;
                }
            }
            x = formClientOffer.getTblArticles().length + 1;
            newModel = new Object[x][8];

            for (i = 0; i < formClientOffer.getTblArticles().length; i++) {
                newModel[i] = formClientOffer.getTblArticles()[i];
            }
        } else {
            newModel = new Object[x][8];
        }


        Object[] row = {
            detailPK,
            articleHistory.getEan(),
            articleHistory.getName(),
            articleHistory.getPriceGross() + " €",
            (Math.floor(articleHistory.getPriceVat() * 10000) / 100) + " %",
            "1",
            "0",
            (articleHistory.getPriceGross() * (1 + articleHistory.getPriceVat())) + " €"
        };

        newModel[i] = row;

        formClientOffer.setTblArticles(newModel);
    }

    /**
     * Ein Kunde muss ausgewählt werden
     *
     * @return True - Wenn der Kunde ausgewählt wurde; False - Sonst.
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
        formClientOffer.setTxtBilled(new SimpleDateFormat("dd-MM-yyyy HH:mm")
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
     * Ein Artikel des Angebots wird gelöscht
     *
     * @param clientOfferDetail ID des Artikels
     */
    public void deleteArticle(ClientOfferDetailPK clientOfferDetail) {
        long deleteArticleId = clientOfferDetail.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle();

        Object[][] oldTblArticles = formClientOffer.getTblArticles();
        Object[][] newTblArticles = new Object[oldTblArticles.length - 1][7];
        int x = 0;
        for (int i = 0; i < oldTblArticles.length; i++) {
            long actualArticleId = ((ClientOfferDetailPK) oldTblArticles[i][0]).getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle();
            if (actualArticleId != deleteArticleId) {
                newTblArticles[x] = oldTblArticles[i];
                x++;
            }
        }

        formClientOffer.setTblArticles(newTblArticles);
    }

    /**
     * @return the clientOfferObjects
     */
    public Object[][] getClientOfferObjects() {
        return clientOfferObjects;
    }

    /**
     * @param clientOfferObjects the clientOfferObjects to set
     */
    public void setClientOfferObjects(Object[][] clientOfferObjects) {
        this.clientOfferObjects = clientOfferObjects;
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

    /**
     * @return the clientOfferId
     */
    public long getClientOfferId() {
        return clientOfferId;
    }

    /**
     * @param clientOfferId the clientOfferId to set
     */
    public void setClientOfferId(long clientOfferId) {
        this.clientOfferId = clientOfferId;
    }

    /**
     * @return the formClientOffer
     */
    public FormClientOffer getFormClientOffer() {
        return formClientOffer;
    }

    /**
     * @param formClientOffer the formClientOffer to set
     */
    public void setFormClientOffer(FormClientOffer formClientOffer) {
        this.formClientOffer = formClientOffer;
    }
}
