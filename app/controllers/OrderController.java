package controllers;

import models.OrderModel;
import models.Product;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Result;
import services.OrderService;

import javax.inject.Inject;

import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class OrderController {

    @Inject
    FormFactory formFactory;

    public Result placeOrder() {

        Form<OrderInput> orderForm = formFactory.form(OrderInput.class).bindFromRequest();

        if (orderForm.hasErrors()) {
            return badRequest(orderForm.getGlobalError().toString());
        } else {
            OrderModel order = new OrderModel();
            order.setAddressStreet(orderForm.get().getAddressStreet());
            order.setAddressNumber(orderForm.get().getAddressNumber());
            order.setAddressNumberAdd(orderForm.get().getAddressNumberAdd());
            order.setPostalCode(orderForm.get().getPostalCode());
            order.update();
            return ok("order placed");
        }
    }

    public Result getOrder(Long id){
        OrderModel order = OrderService.findOrder(id);
        return ok(Json.toJson(order));
    }

    public Result getOrderProducts(Long id){
        List<Product> products = OrderService.findOrderProducts(id);
        return ok(Json.toJson(products));
    }

    public Result getOrdersPerUser(User user){
        List<OrderModel> orders = OrderService.findOrdersPerUser(user);
        return ok(Json.toJson(orders));
    }

    @Constraints.Validate
    public static class OrderInput implements Constraints.Validatable<String> {

        public String addressStreet;
        public Long addressNumber;
        public String addressNumberAdd;
        public String postalCode;

        public String getAddressStreet() {
            return addressStreet;
        }

        public void setAddressStreet(String addressStreet) {
            this.addressStreet = addressStreet;
        }

        public Long getAddressNumber() {
            return addressNumber;
        }

        public void setAddressNumber(Long addressNumber) {
            this.addressNumber = addressNumber;
        }

        public String getAddressNumberAdd() {
            return addressNumberAdd;
        }

        public void setAddressNumberAdd(String addressNumberAdd) {
            this.addressNumberAdd = addressNumberAdd;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String validate() {

            if (addressStreet == null) {
                return "addressStreet cannot be empty";
            }

            if (addressNumber == null) {
                return "addressNumber cannot be empty";
            }

            if (postalCode == null) {
                return "postalCode cannot be empty";
            }

            return null;
        }
    }
}
