package models;

import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class Order extends Model{

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String streetAddress;

    @OneToMany(mappedBy="orderedProducts")
    public List<Product> products;
}
