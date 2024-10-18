package com.mora.cafe.POJO.Category;

import com.mora.cafe.POJO.Product.NewProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "categoryName")
    private String categoryName;

    @NotBlank
    @Column(name = "categoryType")
    private String categoryType;

    @OneToMany(mappedBy = "category")  // 'category' refers to the field in NewProduct
    private Set<NewProduct> productSet;

    public Category() {
    }

    public Category(String categoryName, String categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }


}

