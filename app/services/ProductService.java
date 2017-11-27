package services;

import io.ebean.Ebean;
import io.ebean.Finder;
import models.Product;

import java.util.*;
import javax.persistence.*;
import java.util.List;

public class ProductService {

    public static final Finder<Long, Product> find = new Finder<>(Product.class);

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
        List<Product> products = ProductService.find.query().where().eq("price >= ", minPrice).eq("price <= ", maxPrice)
                .findPagedList()
                .getList();

        if (products.size() == 0) {
            return null;
        }

        return products;
    }

    public static List<Product> getAllProducts() {
        List<Product> products = ProductService.find.query()
                .findPagedList()
                .getList();

        if (products.size() == 0) {
            return null;
        }

        return products;
    }

    public static void deleteProduct(Long id) {

        Ebean.delete(findByID(id));

    }
}
