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

import javax.inject.Inject;

public class ProductController extends Controller {

    @Inject
    FormFactory formFactory;

//    public Product addProduct()
//    {
//
//        Form<Product> productForm = formFactory.form(Product.class).bindFromRequest();
//
//    }

//    public Product editProduct(Long id)
//    {
//
//        Form<Product> productForm = formFactory.form(Product.class).bindFromRequest();
//
//        if (productForm.hasErrors()) {
//            return badRequest(productForm.getGlobalError().toString());
//        } else {
//          //update profile
//            Product product = ProductService.findByID(id);
//            product.setProductname(productForm.get().getProductName());
//            product.update();
//            return ok("success");
//        }
//    }
}
