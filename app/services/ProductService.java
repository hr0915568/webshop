package services;

import io.ebean.Ebean;
import io.ebean.Finder;
import models.Category;
import models.Product;

import java.util.*;
import javax.persistence.*;
import java.util.List;

public class ProductService {

    public static final Finder<Long, Product> find = new Finder<>(Product.class);
    public static final Finder<Long, Category> findCategory = new Finder<>(Category.class);

    public static Product findByName(String productname) {
        List<Product> product = ProductService.find.query().where().eq("productname", productname)
                .setMaxRows(1)
                .findPagedList()
                .getList();

        if (product.size() == 0) {
            return null;
        }

        return product.get(0);
    }

    public static Product findByID(Long id) {
        List<Product> product = ProductService.find.query().where().eq("id", id)
                .setMaxRows(1)
                .findPagedList()
                .getList();

        if (product.size() == 0) {
            return null;
        }

        return product.get(0);
    }

    public static List<Product> findProductsWithinPriceRange(Float minPrice, Float maxPrice) {
        List<Product> products = ProductService.find.query().where().ge("price", minPrice).le("price", maxPrice)
                .findPagedList()
                .getList();

        if (products.size() == 0) {
            return null;
        }

        products.get(0).setViewed(products.get(0).getViewed() + 1);
        return products;
    }

    public static List<Product> getAllProducts() {
        List<Product> products = ProductService.find.query()
                .setMaxRows(500)
                .findPagedList()
                .getList();

        return products;
    }

    public static void deleteProduct(Long id) {

        Ebean.delete(findByID(id));

    }

    public static List<Product> getProductsByCategory(Long id){
        List<Product> products = ProductService.find.query().where().eq("categories_id", id)
                .setMaxRows(500)
                .findPagedList()
                .getList();

        return products;
    }
}
