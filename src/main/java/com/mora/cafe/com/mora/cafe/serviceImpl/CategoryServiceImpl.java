package com.mora.cafe.com.mora.cafe.serviceImpl;

import com.mora.cafe.com.mora.cafe.POJO.Category.Category;
import com.mora.cafe.com.mora.cafe.dao.CategoryDao;
import com.mora.cafe.com.mora.cafe.service.CategoryService;
import com.mora.cafe.com.mora.cafe.wrapper.CategoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> createCategory(Map<String, String> requestMap) {
        try {
            Category category = categoryDao.findCategoryByCategoryName(requestMap.get("categoryName"));

            if (Objects.isNull(category)) {
                Category category1 = new Category();
                category1.setCategoryType(requestMap.get("categoryType"));
                category1.setCategoryName(requestMap.get("categoryName"));
                categoryDao.save(category1);
                System.out.println(requestMap.get("categoryName") + "added successfully");
                return new ResponseEntity<>("Category Created", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Category exists" , HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Creation Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllCategory() {
        try {
            List<CategoryWrapper> categories = categoryDao.getAllCategories();
            if (!Objects.isNull(categories)) {
                System.out.println("thiye thiye");
                return new ResponseEntity<>(categoryDao.getAllCategories(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Categories not available", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateCategory(Map<String, Object> requestMap) {
        try {
            Category category = categoryDao.getCategoryById((Long) requestMap.get("id"));
            if (!Objects.isNull(category)) {
                if (categoryDao.updateCategoryById((Long) requestMap.get("id"), (String) requestMap.get("categoryName"), (String) requestMap.get("categoryType")) == 1) {
                    return new ResponseEntity<>("Category Deleted", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Category Not available", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCategory(Map<String, Long> requestMap) {
        try {
            Category category = categoryDao.getCategoryById(requestMap.get("id"));
            if (!Objects.isNull(category)) {
                if (categoryDao.deleteCategoryById(requestMap.get("id")) == 1) {
                    return new ResponseEntity<>("Category Deleted", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Invalid ID", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
