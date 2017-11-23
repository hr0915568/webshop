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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}