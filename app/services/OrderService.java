package services;

import controllers.OrderController;
import io.ebean.Finder;
import models.*;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderService {

    public static final Finder<Long, Order> find = new Finder<>(Order.class);
    public static final Finder<Long, OrderProduct> findProducts = new Finder<>(OrderProduct.class);

    public static Order findOrder(Long id) {
        Order order = OrderService.find.query().where().eq("id", id)
            .findOne();

            return order;
    }

    public static List<Product> findOrderProducts(Order ordermodel) {
        List<OrderProduct> orderProducts = OrderService.findProducts.query().where().eq("orderedproducts", ordermodel)
                .findList();


        if (orderProducts.size() == 0) {
            return null;
        }

        List<Product> products = new ArrayList<>();

        for(OrderProduct order : orderProducts) {
            products.add(order.getOrderedproduct());
        }

        return products;
    }

    public static List<Order> findOrdersPerUser(User user){
        List<Order> orders = OrderService.find.query().where().eq("user", user)
                .findList();

        if (orders.get(0) == null){
            return null;
        }

        return orders;
    }

    public static Integer findProductCount(Product product){
        List<OrderProduct> products = OrderService.findProducts.query().where().eq("orderedproduct", product)
                .findList();

        if (products.get(0) == null){
            return 0;
        }

        return products.size();
    }

    /**
     * This method does three things: 1. create user account, 2. create order, 3. generate an invoice.
     * @param input
     * @return
     */
    public static Order placeOrderAsGuest(OrderController.OrderInputGuest input) {
        User user = UserService.newUser(input.getEmail(), input.getFirtsName(), input.getLastName(), input.getPassword());
        Order order = generateOrder(
                input.getProducts(),
                new Address(input.getCountry(), input.getZipcode(), input.getStreet(), input.getStreetNumber(), input.getAddressExtra()),
                user,
                input.getOrderNotes(),
                input.getCompany()
        );

        InvoiceService.generateInvoice(order);

        return order;
    }

    public static Order placeOrderAsRegisteredUser(OrderController.OrderInput input, User user) {
        Order order = generateOrder(
                input.getProducts(),
                new Address(input.getCountry(), input.getZipcode(), input.getStreet(), input.getStreetNumber(), input.getAddressExtra()),
                user,
                input.getOrderNotes(),
                input.getCompany()
        );

        InvoiceService.generateInvoice(order);

        return order;
    }


    private static Order generateOrder(List<CartProduct> products, Address address, User user, String notes, String company) {
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setCompany(company);
        order.save();

        Iterator<CartProduct> iterator = products.iterator();
        List<OrderProduct> orderProducts = new ArrayList<>();
        while(iterator.hasNext()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);

            CartProduct cartProduct = iterator.next();

            Product product = ProductService.findByID(Long.valueOf(cartProduct.getProductId()));

            orderProduct.setOrderedproducts(product);
            orderProduct.setQuantity(cartProduct.getQuantity());
            orderProduct.setPriceAtOrdertime(product.getPrice());

            orderProduct.save();
        }

        order.setOrderProducts(orderProducts);

        return  order;
    }

}