package models;

import io.ebean.annotation.Sql;

import javax.persistence.Entity;

@Entity
@Sql
public class ProductsView {

    public String date;

    public int count;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
