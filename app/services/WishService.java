package services;

import io.ebean.Finder;
import models.Product;
import models.User;
import models.WishProduct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WishService {

    public static final Finder<Long, WishProduct> find = new Finder<>(WishProduct.class);

    public static void newWish(Product product, User user)
    {
        WishProduct p = new WishProduct();
        p.setProduct(product);
        p.setUser(user);
        p.save();
    }

    public static List<Product> getProducts(User user)
    {
        List<WishProduct> wishProducts = WishService.find.query().where().eq("user_id", user.getId().toString())
                .setMaxRows(500)
                .findPagedList()
                .getList();

        List<Product> products = new ArrayList<>();
        Iterator<WishProduct> iterator = wishProducts.iterator();
        while(iterator.hasNext()) {
            Product p = iterator.next().getProduct();
            products.add(p);
        }

        return products;
    }

}
