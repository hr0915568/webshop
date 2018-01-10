package services;

import models.Invoice;
import models.InvoiceRow;
import models.Order;
import models.OrderProduct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InvoiceService {

    public static Invoice generateInvoice(Order order) {
        Invoice invoice = new Invoice();
        invoice.setCountry(order.getCountry());
        invoice.setStreet(order.getStreet());
        invoice.setStreetNumber(order.getStreetNumber());
        invoice.setZipcode(order.getZipcode());
        invoice.setAddressExtra(order.getAddressExtra());

        invoice.setUser(order.getUser());
        invoice.setOrdermodel(order);
        invoice.setShippingCosts(0F);

        invoice.save();

        List<OrderProduct> orderProducts = order.getOrderProducts();
        Iterator<OrderProduct> iterator = orderProducts.iterator();

        List<InvoiceRow> invoiceRows = new ArrayList<>();
        while(iterator.hasNext()) {
            OrderProduct orderProduct = iterator.next();
            InvoiceRow invoiceRow = new InvoiceRow();
            invoiceRow.setDescription(orderProduct.getOrderedproduct().getDescription());
            invoiceRow.setQuantity(orderProduct.getQuantity());
            invoiceRow.setUnitPrice(orderProduct.getPriceAtOrdertime());
            invoiceRow.setInvoice(invoice);
            invoiceRow.save();

            invoiceRows.add(invoiceRow);
        }

        invoice.setInvoiceRows(invoiceRows);

        return invoice;
    }
}
