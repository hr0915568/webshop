package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Product;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.ProductService;
import play.data.Form;
import play.data.FormFactory;
import io.ebean.*;


import javax.inject.Inject;
import java.util.List;

public class ProductController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result addProduct() {

        Form<ProductInput> productForm = formFactory.form(ProductInput.class).bindFromRequest();

        if (productForm.hasErrors()) {
            return badRequest(productForm.getGlobalError().toString());
        } else {
            Product product = new Product();
            product.setProductname(productForm.get().getProductname());
            product.setDescription(productForm.get().getDescription());
            product.setPrice(productForm.get().getPrice());
            product.update();
            return ok("new product added");
        }
    }

    public Result editProduct(Long id)
    {

        Form<ProductInput> productForm = formFactory.form(ProductInput.class).bindFromRequest();

        if (productForm.hasErrors()) {
            return badRequest(productForm.getGlobalError().toString());
        } else {
          //update profile
            Product product = ProductService.findByID(id);
            product.setProductname(productForm.get().getProductname());
            product.setDescription(productForm.get().getDescription());
            product.setPrice(productForm.get().getPrice());
            product.update();
            return ok("success");
        }
    }

    public void deleteProduct(Long id) {

        ProductService.deleteProduct(id);

    }

    public List<Product> getAllProducts() {
        return ProductService.getAllProducts();
    }

    public List<Product> getProductsByCategory(Long id){return ProductService.getProductsByCategory(id);}

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
