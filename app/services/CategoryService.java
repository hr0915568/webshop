package services;

import io.ebean.Finder;
import models.Product;
import models.User;
import models.Category;
import java.util.List;

public class CategoryService {
    public static final Finder<Long, Category> find = new Finder<>(Category.class);

    public static List<Category> getAllCategories() {
        List<Category> categories = CategoryService.find.query().fetchLazy("products")
                .setMaxRows(500)
                .findPagedList()
                .getList();

        return categories;
    }


}
