package models;

import java.util.*;
import javax.persistence.*;

import play.Logger;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Product extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String productname;

    @Constraints.Required
    public String description;

    @Constraints.Required
    public Float price;

    public static final Finder<Long, Product> find = new Finder<>(Product.class);

    public static List<Product> getAllProducts() {
        List<Product> products = Product.find.query()
                .findPagedList()
                .getList();

        if (products.size() == 0) {
            return null;
        }

        return products;
    }

    public static Product findByName(String productname) {
        List<Product> product = Product.find.query().where().eq("productname", productname)
                .setMaxRows(1)
                .findPagedList()
                .getList();

        if (product.size() == 0) {
            return null;
        }

        return product.get(0);
    }

    public static List<Product> findProductsWithinPriceRange(Float minPrice, Float maxPrice) {
        List<Product> products = Product.find.query().where().eq("price >= ", minPrice).eq("price <= ", maxPrice)
                .findPagedList()
                .getList();

        if (products.size() == 0) {
            return null;
        }

        return products;
    }
}