package org.haegerp.controller.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;

import org.haegerp.controller.SupplierOrderDetailController;
import org.haegerp.entity.Article;
import org.haegerp.entity.ArticleHistory;
import org.haegerp.entity.ArticleHistoryPK;
import org.haegerp.entity.SupplierOrder;
import org.haegerp.entity.SupplierOrderDetail;
import org.haegerp.entity.SupplierOrderDetailPK;
import org.haegerp.entity.repository.article.ArticleHistoryRepository;
import org.haegerp.entity.repository.article.ArticleRepository;
import org.haegerp.entity.repository.supplier.SupplierOrderDetailRepository;
import org.haegerp.entity.repository.supplier.SupplierOrderRepository;
import org.haegerp.exception.LengthOverflowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierOrderDetailControllerImpl implements SupplierOrderDetailController {

    @Autowired
    private SupplierOrderDetailRepository supplierOrderDetailRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleHistoryRepository articleHistoryRepository;
    @Autowired
    private SupplierOrderRepository supplierOrderRepository;

    public SupplierOrderDetailControllerImpl() {
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Object[][] loadTableRows(long idSupplierOrder) {
        List<SupplierOrderDetail> list = this.loadPage(idSupplierOrder);
        Object[][] rows = new Object[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            SupplierOrderDetail supplierOrderDetail = list.get(i);
            
            rows[i][0] = supplierOrderDetail.getSupplierOrderDetailPK();
            rows[i][1] = supplierOrderDetail.getSupplierOrderDetailPK().getArticleHistory().getEan();
            rows[i][2] = supplierOrderDetail.getSupplierOrderDetailPK().getArticleHistory().getName();
            rows[i][3] = supplierOrderDetail.getSupplierOrderDetailPK().getArticleHistory().getPriceSupplier() + " €";
            rows[i][4] = supplierOrderDetail.getQuantity();
            rows[i][5] = Math.floor(supplierOrderDetail.getDiscount() * 10000) / 100;
            rows[i][6] = supplierOrderDetail.getTotalArticle() + " €";
        }

        return rows;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<SupplierOrderDetail> loadPage(long idSupplierOrder) {
        return supplierOrderDetailRepository.findAllByIdSupplierOrder(idSupplierOrder);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public SupplierOrderDetail save(SupplierOrderDetail supplierOrderDetail) {
        SupplierOrderDetail savedSupplierOrderDetail = supplierOrderDetailRepository.save(supplierOrderDetail);
        return savedSupplierOrderDetail;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Set<SupplierOrderDetail> updateOrderArticle(JTable table, long idSupplierOrder) throws LengthOverflowException {
        Object[][] values = new Object[table.getRowCount()][3];
        for (int x = 0; x < table.getRowCount(); x++) {
            values[x][0] = table.getModel().getValueAt(x, 0);
            values[x][1] = table.getValueAt(x, 3);
            values[x][2] = table.getValueAt(x, 4);
        }

        return doUpdateOrderArticle(values, idSupplierOrder);
    }

    @Override
    public Set<SupplierOrderDetail> doUpdateOrderArticle(Object[][] values, long idSupplierOrder) throws LengthOverflowException {
        /**
         * [X][0] - ArticleHistoryPK
         * [X][1] - Quantity
         * [X][2] - Discount
         */
        Set<SupplierOrderDetail> orderDetails = new HashSet<SupplierOrderDetail>(0);
        for (int x = 0; x < values.length; x++) {
            SupplierOrderDetailPK id = (SupplierOrderDetailPK) values[x][0];
            Long quantity = Long.valueOf(String.valueOf(values[x][1]));
            Float discount = Float.valueOf(String.valueOf(values[x][2]));
        
        
            long idArticle = id.getArticleHistory().getArticleHistoryPK().getArticle().getIdArticle();

            Article article = articleRepository.findOne(idArticle);
            ArticleHistoryPK articleHistoryPK = new ArticleHistoryPK(articleHistoryRepository.findByIdArticle(idArticle), article);
            ArticleHistory articleHistory = articleHistoryRepository.findOne(articleHistoryPK);
            SupplierOrder supplierOrder = supplierOrderRepository.findOne(idSupplierOrder);

            SupplierOrderDetail supplierOrderDetail = supplierOrderDetailRepository.findOne(new SupplierOrderDetailPK(supplierOrder, articleHistory));

            if (supplierOrderDetail == null) {
                supplierOrderDetail = new SupplierOrderDetail();
                supplierOrderDetail.setSupplierOrderDetailPK(new SupplierOrderDetailPK(supplierOrder, articleHistory));
            }

            supplierOrderDetail.setDiscount(discount / 100);
            supplierOrderDetail.setQuantity(quantity);
            supplierOrderDetailRepository.save(supplierOrderDetail);
            orderDetails.add(supplierOrderDetail);
        }
        return orderDetails;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteAllArticles(long idSupplierOrder) {

        Set<SupplierOrderDetail> entities = supplierOrderRepository.findOne(idSupplierOrder).getSupplierOrderDetail();

        supplierOrderDetailRepository.delete(entities);
    }

    @Override
    public void delete(SupplierOrderDetail supplierOrderDetail) {
        supplierOrderDetailRepository.delete(supplierOrderDetail);
    }
}