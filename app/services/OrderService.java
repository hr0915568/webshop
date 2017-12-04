package services;

import io.ebean.Finder;
import models.OrderModel;
import models.Product;
import models.User;

import java.util.List;

public class OrderService {

    public static final Finder<Long, OrderModel> find = new Finder<>(OrderModel.class);

    public static OrderModel findOrder(Long id) {
        OrderModel orderModel = OrderService.find.query().where().eq("id", id)
            .findOne();

            return orderModel;
    }

    public static List<Product> findOrderProducts(Long id) {
        OrderModel orderModelProduct = OrderService.find.query().where().eq("id", id)
                .findOne();

        if (orderModelProduct.getProducts().size() == 0) {
            return null;
        }

        return orderModelProduct.getProducts();
    }

    public static List<OrderModel> findOrdersPerUser(User user){
        List<OrderModel> orderModels = OrderService.find.query().where().eq("user", user)
                .findList();

        if (orderModels.get(0) == null){
            return null;
        }

        return orderModels;
    }

}