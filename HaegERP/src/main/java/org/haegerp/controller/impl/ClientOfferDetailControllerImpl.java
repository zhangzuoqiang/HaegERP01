package org.haegerp.controller.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;

import org.haegerp.controller.ClientOfferDetailController;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.haegerp.entity.ClientOffer;
import org.haegerp.entity.ClientOfferDetail;
import org.haegerp.entity.ClientOfferDetailPK;
import org.haegerp.entity.repository.article.ArticleHistoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.entity.repository.client.ClientOfferDetailRepository;
import org.haegerp.entity.repository.client.ClientOfferRepository;
import org.haegerp.exception.LengthOverflowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientOfferDetailControllerImpl implements ClientOfferDetailController {

    @Autowired
    private ClientOfferDetailRepository clientOfferDetailRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;
    @Autowired
    private ClientOfferRepository clientOfferRepository;

    public ClientOfferDetailControllerImpl() {
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Object[][] loadTableRows(long idClientOffer) {
        List<ClientOfferDetail> list = this.loadPage(idClientOffer);
        Object[][] rows = new Object[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            ClientOfferDetail clientOfferDetail = list.get(i);

            rows[i][0] = clientOfferDetail.getClientOfferDetailPK();
            rows[i][1] = clientOfferDetail.getClientOfferDetailPK().getArticleHistory().getEan();
            rows[i][2] = clientOfferDetail.getClientOfferDetailPK().getArticleHistory().getName();
            rows[i][3] = clientOfferDetail.getClientOfferDetailPK().getArticleHistory().getPriceGross() + " €";
            rows[i][4] = (Math.floor(clientOfferDetail.getClientOfferDetailPK().getArticleHistory().getPriceVat() * 10000) / 100) + " %";
            rows[i][5] = clientOfferDetail.getQuantity();
            rows[i][6] = Math.floor(clientOfferDetail.getDiscount() * 10000) / 100;
            rows[i][7] = clientOfferDetail.getTotalArticle() + " €";
        }

        return rows;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<ClientOfferDetail> loadPage(long idClientOffer) {
        return clientOfferDetailRepository.findAllByIdClientOffer(idClientOffer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Set<ClientOfferDetail> updateOrderArticle(JTable table, long idClientOffer) throws LengthOverflowException {
        Set<ClientOfferDetail> orderDetails = new HashSet<ClientOfferDetail>(0);
        float discount;
        int quantity;
        for (int x = 0; x < table.getRowCount(); x++) {
            ClientOfferDetailPK id = (ClientOfferDetailPK) table.getModel().getValueAt(x, 0);
            discount = Float.parseFloat(String.valueOf(table.getValueAt(x, 5)));
            quantity = Integer.parseInt(String.valueOf(table.getValueAt(x, 4)));

            long idArticle = id.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle();

            Article article = articleRepository.findOne(idArticle);
            ArticleHistoryPK articleHistoryPK = new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(idArticle), article);
            ArticleHistory articleHistory = articleHistoryRepository.findOne(articleHistoryPK);
            ClientOffer clientOffer = clientOfferRepository.findOne(idClientOffer);

            ClientOfferDetail clientOfferDetail = clientOfferDetailRepository.findOne(new ClientOfferDetailPK(clientOffer, articleHistory));

            if (clientOfferDetail == null) {
                clientOfferDetail = new ClientOfferDetail();
                clientOfferDetail.setClientOfferDetailPK(new ClientOfferDetailPK(clientOffer, articleHistory));
            }

            clientOfferDetail.setDiscount(discount / 100);
            clientOfferDetail.setQuantity(quantity);
            clientOfferDetailRepository.save(clientOfferDetail);
            orderDetails.add(clientOfferDetail);
        }

        return orderDetails;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAllArticles(long idClientOffer) {

        Set<ClientOfferDetail> entities = clientOfferRepository.findOne(idClientOffer).getClientOfferDetail();

        clientOfferDetailRepository.delete(entities);
    }
}
