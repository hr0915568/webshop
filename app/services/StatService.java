package services;

import io.ebean.Ebean;
import io.ebean.Query;
import io.ebean.RawSql;
import io.ebean.RawSqlBuilder;
import models.*;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

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


    public static void newProductEvent(Product p, ProductStatAction action, Date date) {
        ProductStat productStat = new ProductStat();
        productStat.setProduct(p);
        productStat.setType(action);
        productStat.setCreateAt(date);
        productStat.save();
    }

    public static void newProductEvent(Product p, ProductStatAction action, User user, Date date) {
        ProductStat productStat = new ProductStat();
        productStat.setProduct(p);
        productStat.setType(action);
        productStat.setVisitor(user);
        productStat.setCreateAt(date);
        productStat.save();
    }

    public static List<ProductsView> getViewsByDay()
    {
        String sql = "SELECT datum,  count(product_id) as counts\n" +
                "FROM (\n" +
                "  SELECT\n" +
                "    product_id,\n" +
                "    date(create_at) AS datum\n" +
                "  FROM product_stat\n" +
                "  WHERE type = 0 AND product_id > 0\n" +
                ") as x\n" +
                "GROUP BY  x.datum\n" +
                "ORDER BY  datum";
        RawSql rawSql =
                RawSqlBuilder.parse(sql).columnMapping("datum", "date")
                .columnMapping("counts", "count").create();

        Query<ProductsView> query = Ebean.find(ProductsView.class);
        query.setRawSql(rawSql);
        List<ProductsView> list = query.findList();

        return list;

    }

public static List<CategoryView> getViewsByDayOfWeek()
    {
        String sql = "SELECT dayofweek,  round(count(product_id)) as counts, category_name, categories_id\n" +
                "FROM (\n" +
                "  SELECT\n" +
                "    product_id,\n" +
                "    p.categories_id,\n" +
                "    c.category_name,\n" +
                "    dayofweek(create_at) AS dayofweek\n" +
                "  FROM product_stat ps\n" +
                "    JOIN product p on p.id = ps.product_id\n" +
                "    join category c on c.id = p.categories_id\n" +
                "  WHERE type = 0 AND product_id > 0\n" +
                ") as x\n" +
                "GROUP BY  dayofweek, category_name, categories_id\n" +
                "ORDER BY category_name\n" +
                ";";
        RawSql rawSql =
                RawSqlBuilder.parse(sql)
                        .columnMapping("dayofweek", "dayofweek")
                        .columnMapping("counts", "count")
                        .columnMapping("category_name", "categoryName")
                        .columnMapping("categories_id", "categoryId")
                        .create();

        Query<CategoryView> query = Ebean.find(CategoryView.class);
        query.setRawSql(rawSql);
        List<CategoryView> list = query.findList();

        return list;

    }

}
