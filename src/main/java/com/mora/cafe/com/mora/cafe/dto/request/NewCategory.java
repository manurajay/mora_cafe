package com.mora.cafe.com.mora.cafe.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewCategory {

    @NotBlank
    private String categoryName;

    @NotBlank
    private String categoryType;

    public NewCategory(String categoryName, String categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
