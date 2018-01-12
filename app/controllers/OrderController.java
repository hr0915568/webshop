package controllers;

import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.OrderService;
import services.ProductService;
import services.UserService;

import javax.inject.Inject;

import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class OrderController extends Controller{

    @Inject
    FormFactory formFactory;

    private boolean isLoggedIn()
    {
        String email = session("email");
        if(email == null || email.length() <= 1) {
            return false;
        }

        return true;
    }

    private User getSessionUser()
    {
        String email = session("email");
        return UserService.findByEmail(email);
    }

    public Result placeOrderAsGuest() {

        Form<OrderInputGuest> orderForm = formFactory.form(OrderInputGuest.class).bindFromRequest();

        if (orderForm.hasErrors()) {
            return badRequest(orderForm.getGlobalError().toString());
        } else {
            OrderService.placeOrderAsGuest(orderForm.get());
            return ok("order placed");
        }
    }

    public Result placeOrderAsRegisteredUser() {

        Form<OrderInput> orderForm = formFactory.form(OrderInput.class).bindFromRequest();

        if (orderForm.hasErrors()) {
            return badRequest(orderForm.getGlobalError().toString());
        } else {
            OrderService.placeOrderAsRegisteredUser(orderForm.get(), getSessionUser());
            return ok("order placed");
        }
    }


    public Result getOrder(Long id){
        Order order = OrderService.findOrder(id);
        return ok(Json.toJson(order));
    }

    public Result getOrderProducts(Long id){
        List<Product> products = OrderService.findOrderProducts(OrderService.findOrder(id));
        return ok(Json.toJson(products));
    }

    public void setOrderProducts(Long id, List<OrderProduct> orderProducts){
        Order order = OrderService.findOrder(id);
        order.setOrderProducts(orderProducts);
    }

    public Result getOrdersPerUser(User user){
        List<Order> orders = OrderService.findOrdersPerUser(user);
        return ok(Json.toJson(orders));
    }

    public Result getOrderProductCount(Long id){
        Integer productCount = OrderService.findProductCount(ProductService.findByID(id));
        return ok(Json.toJson(productCount));
    }

    @Constraints.Validate
    public static class OrderInputGuest extends OrderInput {
        public String email;
        public String firtsName;
        public String lastName;

        public String password;

        public String getFirtsName() {
            return firtsName;
        }

        public void setFirtsName(String firtsName) {
            this.firtsName = firtsName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

    @Constraints.Validate
    public static class OrderInput implements Constraints.Validatable<String> {

        public String company;

        public String country;
        public String city;
        public String zipcode;

        public String street;
        public String streetNumber;
        public String addressExtra;


        public String orderNotes;

        public List<CartProduct> products;




        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getAddressExtra() {
            return addressExtra;
        }

        public void setAddressExtra(String addressExtra) {
            addressExtra = addressExtra;
        }

        public String validate() {

            if (street == null) {
                return "street cannot be empty";
            }

            if (streetNumber == null) {
                return "streetNumber cannot be empty";
            }

            if (zipcode == null) {
                return "postalCode cannot be empty";
            }

            return null;
        }

        public String getOrderNotes() {
            return orderNotes;
        }

        public void setOrderNotes(String orderNotes) {
            this.orderNotes = orderNotes;
        }

        public List<CartProduct> getProducts() {
            return products;
        }

        public void setProducts(List<CartProduct> products) {
            this.products = products;
        }
    }
}
