package com.mora.cafe.wrapper;

import com.mora.cafe.POJO.Category.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ProductWrapper {
    private String productName;
    private Category category;
    private Boolean isAvailable;
    private Date manufactureDate;
    private Date expirationDate;

    public ProductWrapper(String productName, Category category, Boolean isAvailable, Date manufactureDate, Date expirationDate) {
        this.productName = productName;
        this.category = category;
        this.isAvailable = isAvailable;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
    }
}
