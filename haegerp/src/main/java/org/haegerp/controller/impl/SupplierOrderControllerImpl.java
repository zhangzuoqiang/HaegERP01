package org.haegerp.controller.impl;

import java.text.SimpleDateFormat;

import org.haegerp.controller.SupplierOrderController;
import org.haegerp.entity.SupplierBill;
import org.haegerp.entity.SupplierOrder;
import org.haegerp.entity.repository.employee.EmployeeRepository;
import org.haegerp.entity.repository.supplier.SupplierBillRepository;
import org.haegerp.entity.repository.supplier.SupplierOrderRepository;
import org.haegerp.entity.repository.supplier.SupplierRepository;
import org.haegerp.exception.LengthOverflowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierOrderControllerImpl implements SupplierOrderController {

    @Autowired
    private SupplierOrderRepository supplierOrderRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SupplierBillRepository supplierBillRepository;
    private String search;
    private int enableAll = 1;
    private int enableSearch = 0;
    private int pageNumber;
    private Page<SupplierOrder> page;

    @Override
    public Page<SupplierOrder> getPage() {
        return page;
    }

    public SupplierOrderControllerImpl() {
        pageNumber = 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Object[][] loadTableRows(int size) {
        Page<SupplierOrder> soPage = this.loadPage(size);
        Object[][] rows = new Object[soPage.getContent().size()][10];
        for (int i = 0; i < soPage.getContent().size(); i++) {
            SupplierOrder supplierOrder = soPage.getContent().get(i);

            rows[i][0] = supplierOrder.getIdSupplierOrder();
            rows[i][1] = supplierOrder.getSupplier().getName();
            rows[i][2] = supplierOrder.getEmployee().getName();
            rows[i][3] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrder.getOrderDate());
            rows[i][4] = (supplierOrder.getSendDate() == null) ? "Order not sent" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrder.getSendDate());
            rows[i][5] = supplierOrder.getTotal();
            if (supplierOrder.getSupplierBill() == null) {
                rows[i][6] = "Bill not received";
                rows[i][7] = "Bill not paid";
            } else {
                rows[i][6] = (supplierOrder.getSupplierBill().getReceivedDate() == null) ? "Bill not received" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrder.getSupplierBill().getReceivedDate());
                rows[i][7] = (supplierOrder.getSupplierBill().getPaidDate() == null) ? "Bill not paid" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrder.getSupplierBill().getPaidDate());
            }
            rows[i][8] = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(supplierOrder.getLastModifiedDate());
            rows[i][9] = employeeRepository.findOne(supplierOrder.getIdEmployeeModify()).getName();
        }

        return rows;
    }

    @Override
    public void setSearch(String value, int size) {
        enableSearch = 0;
        if (value.equals("")) {
            enableAll = 1;
        } else {
            enableSearch = 1;
            enableAll = 0;
            search = value;
            pageNumber = 0;
        }
    }

    @Override
    public boolean getNextPage(int size) {
        if (page.hasNextPage()) {
            pageNumber++;
            page = loadPage(size);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getPreviousPage(int size) {
        if (page.hasNextPage()) {
            pageNumber--;
            page = loadPage(size);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getFirstPage(int size) {
        pageNumber = 0;
        page = loadPage(size);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<SupplierOrder> loadPage(int size) {
        PageRequest pageRequest = new PageRequest(pageNumber, size);
        this.page = supplierOrderRepository.findWithFilters(enableSearch, search,
                enableAll, pageRequest);
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SupplierOrder save(SupplierOrder supplierOrder) {
        SupplierOrder savedSupplierOrder = supplierOrderRepository.save(supplierOrder);
        return savedSupplierOrder;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SupplierOrder updateSupplierOrder(SupplierOrder supplierOrder) throws LengthOverflowException {
        SupplierOrder newSupplierOrder = supplierOrderRepository.findOne(supplierOrder.getIdSupplierOrder());

        newSupplierOrder.setDescription(supplierOrder.getDescription());
        newSupplierOrder.setEmployee(supplierOrder.getEmployee());
        newSupplierOrder.setIdEmployeeModify(supplierOrder.getIdEmployeeModify());
        newSupplierOrder.setLastModifiedDate(supplierOrder.getLastModifiedDate());
        newSupplierOrder.setOrderDate(supplierOrder.getOrderDate());
        newSupplierOrder.setSendDate(supplierOrder.getSendDate());
        newSupplierOrder.setSupplier(supplierOrder.getSupplier());
        newSupplierOrder.setSupplierBill(supplierOrder.getSupplierBill());
        newSupplierOrder.setSupplierOrderDetail(supplierOrder.getSupplierOrderDetail());
        newSupplierOrder.calculateTotal();

        newSupplierOrder = supplierOrderRepository.save(newSupplierOrder);

        return newSupplierOrder;
    }

    @Override
    public void delete(SupplierOrder supplierOrder) {
        supplierOrderRepository.delete(supplierOrder);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SupplierOrder newSupplierOrder(SupplierOrder supplierOrder) throws LengthOverflowException {
        SupplierOrder newSupplierOrder = new SupplierOrder();

        newSupplierOrder.setDescription(supplierOrder.getDescription());
        newSupplierOrder.setEmployee(supplierOrder.getEmployee());
        newSupplierOrder.setIdEmployeeModify(supplierOrder.getIdEmployeeModify());
        newSupplierOrder.setLastModifiedDate(supplierOrder.getLastModifiedDate());
        newSupplierOrder.setOrderDate(supplierOrder.getOrderDate());
        newSupplierOrder.setSendDate(supplierOrder.getSendDate());
        newSupplierOrder.setSupplier(supplierOrder.getSupplier());
        newSupplierOrder.setSupplierBill(supplierOrder.getSupplierBill());
        newSupplierOrder.setSupplierOrderDetail(supplierOrder.getSupplierOrderDetail());

        newSupplierOrder = supplierOrderRepository.save(newSupplierOrder);

        return newSupplierOrder;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SupplierBill getSupplierBillById(long idSupplierBill) {
        return supplierBillRepository.findOne(idSupplierBill);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SupplierBill saveBill(SupplierBill supplierBill) {
        return supplierBillRepository.save(supplierBill);
    }

    @Override
    public SupplierOrder getSupplierOrderById(long idSupplierOrder) {
        return supplierOrderRepository.findOne(idSupplierOrder);
    }
}
