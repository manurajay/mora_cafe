package com.mora.cafe.com.mora.cafe.POJO.Product;


import com.mora.cafe.com.mora.cafe.POJO.Category.Category;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "product")
public class NewProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "category_id")  // Foreign key to Category
    private Category category;

    @Column(name = "availability")
    private Boolean isAvailable;

    @Column(name = "manufactureDate")
    private Date manufacturedDate;

    @Column(name = "expirationDate")
    private Date expirationDate;

    public NewProduct() {}

    public NewProduct(String productName, Category category, Boolean isAvailable, Date manufacturedDate, Date expirationDate) {
        this.productName = productName;
        this.category = category;
        this.isAvailable = isAvailable;
        this.manufacturedDate = manufacturedDate;
        this.expirationDate = expirationDate;
    }

    public void setIsAvailable() {
        this.isAvailable = !isAvailable;
    }
}

