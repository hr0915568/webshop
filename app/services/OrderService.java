package services;

import io.ebean.Finder;
import models.Order;
import models.Product;
import scala.xml.Null;

import java.util.List;

public class OrderService {

    public static final Finder<Long, Order> find = new Finder<>(Order.class);

    public static Order findOrder(Long id) {
        Order order = OrderService.find.query().where().eq("id", id)
            .findOne();

            return order;
    }

    public static List<Product> findOrderProducts(Long id) {
        Order orderProduct = OrderService.find.query().where().eq("id", id)
                .findOne();

        if (orderProduct.getProducts().size() == 0) {
            return null;
        }

        return orderProduct.getProducts();
    }

}