package services;

import models.Product;
import models.ProductStat;
import models.ProductStatAction;
import models.User;

public class StatService {

    public static void newProductEvent(Product p, ProductStatAction action) {
        ProductStat productStat = new ProductStat();
        productStat.setProduct(p);
        productStat.setType(action);
        productStat.save();
    }

    public static void newProductEvent(Product p, ProductStatAction action, User user) {
        ProductStat productStat = new ProductStat();
        productStat.setProduct(p);
        productStat.setType(action);
        productStat.setVisitor(user);
        productStat.save();
    }
}
