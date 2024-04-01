package mr.springbootapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 This class represents a menu item entity in the application.
 It's annotated with @Entity to indicate it's a persistent entity that will be mapped to a database table by Spring Data JPA.
*/
@Entity
/*
 You can optionally specify the table name using the @Table annotation.
 If not specified, Spring might use a default table name based on the class name.
*/
@Table(name = "menu_item")
public class MenuItem {
    /*
     This field represents the unique identifier (primary key) of the menu item.
     It's annotated with @Id to mark it as the primary key.
    */
    @Id
  /*
   The @GeneratedValue annotation specifies that the value will be generated automatically
   using the GenerationType.IDENTITY strategy (typically auto-incrementing in the database).
  */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     This field represents the name of the menu item.
     It's annotated with @Column(nullable = false) to indicate it's a column in the database table and that the value cannot be null.
    */
    @Column(nullable = false)
    private String name;

    private String otherName;
    private String description;

    @Column(nullable = false)
    private float price;

    private String imageUrl;
    private String ingredients;
    private String category;
    private boolean hot;

    public MenuItem(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }
}
