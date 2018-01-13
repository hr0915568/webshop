package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Category;
import models.Product;
import models.ProductStatAction;
import play.data.validation.Constraints;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.CategoryService;
import services.ProductService;
import play.data.Form;
import play.data.FormFactory;
import io.ebean.*;
import services.StatService;


import javax.inject.Inject;
import java.util.List;

public class ProductController extends FEBasecontroller {

    @Inject
    FormFactory formFactory;

//    @Transactional
//    public Result editProduct(Long id)
//    {
//
//        Form<ProductInput> productForm = formFactory.form(ProductInput.class).bindFromRequest();
//
//        if (productForm.hasErrors()) {
//            return badRequest(productForm.getGlobalError().toString());
//        } else {
//          //update profile
//            Product product = ProductService.findByID(id);
//            product.setProductname(productForm.get().getProductname());
//            product.setDescription(productForm.get().getDescription());
//            product.setPrice(productForm.get().getPrice());
//            product.update();
//            return ok("success");
//        }
//    }

    public Result search(String criteria)
    {
        List<Product> products = ProductService.search(criteria);
        return ok(Json.toJson(products));
    }

    public Result getAllCategories()
    {
        List<Category> categories = CategoryService.getAllCategories();
        return ok(Json.toJson(categories));
    }

    public Result getCategoryProducts(Long id)
    {
        List<Product> products = ProductService.getProductsByCategory(id);
        return ok(Json.toJson(products));
    }

    public List<Product> getAllProducts() {
        return ProductService.getAllProducts();
    }

    public List<Product> getProductsByCategory(Long id){return ProductService.getProductsByCategory(id);}

    public Result getProduct(Long id) {
        Product product = ProductService.findByID(id);
        if(isLoggedIn()) {
            StatService.newProductEvent(product, ProductStatAction.VIEW, getSessionUser());
        }else{
            StatService.newProductEvent(product, ProductStatAction.VIEW);
        }

        return ok(Json.toJson(product));
    }

    @Constraints.Validate
    public static class ProductInput implements Constraints.Validatable<String> {

        public String productname;
        public String description;
        public Float price;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public String validate() {

            if (productname == null) {
                return "productname cannot be empty";
            }

            if (description == null) {
                return "description cannot be empty";
            }

            if (price == null) {
                return "price cannot be empty";
            }

            return null;
        }
    }
}
