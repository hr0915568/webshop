package models;

import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Category {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String categoryName;

    @OneToMany(mappedBy="categories")
    public List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}