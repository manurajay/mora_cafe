package com.mora.cafe.dao;

import com.mora.cafe.POJO.Category.Category;
import com.mora.cafe.wrapper.CategoryWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {

    Category findCategoryByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT new com.mora.cafe.wrapper.CategoryWrapper(c.id, c.categoryName, c.categoryType) FROM Category c")
    List<CategoryWrapper> getAllCategories();

    @Query("UPDATE Category c SET c.categoryName = :categoryName , c.categoryType = :categoryType where c.id = :id")
    int updateCategoryById(@Param("id") Long id, @Param("categoryName") String categoryName, @Param("categoryType") String categoryType);

    @Query("DELETE from Category c where c.id = :id")
    int deleteCategoryById(@Param("id") Long id);

    Category getCategoryById(@Param("id") Long id);
}
