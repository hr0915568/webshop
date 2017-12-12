package services;

import io.ebean.Finder;
import models.OrderModel;
import models.OrderProducts;
import models.Product;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public static final Finder<Long, OrderModel> find = new Finder<>(OrderModel.class);
    public static final Finder<Long, OrderProducts> findProducts = new Finder<>(OrderProducts.class);

    public static OrderModel findOrder(Long id) {
        OrderModel orderModel = OrderService.find.query().where().eq("id", id)
            .findOne();

            return orderModel;
    }

    public static List<Product> findOrderProducts(OrderModel ordermodel) {
        List<OrderProducts> orderProducts = OrderService.findProducts.query().where().eq("orderedproducts", ordermodel)
                .findList();


        if (orderProducts.size() == 0) {
            return null;
        }

        List<Product> products = new ArrayList<>();

        for(OrderProducts order : orderProducts) {
            products.add(order.getOrderedproduct());
        }

        return products;
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