package com.mora.cafe.com.mora.cafe.wrapper;

import lombok.Data;

@Data
public class CategoryWrapper {
    private Long id;
    private String categoryName;
    private String categoryType;

    public CategoryWrapper(Long id, String categoryName, String categoryType) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
