package com.mora.cafe.restImpl;


import com.mora.cafe.rest.CategoryRest;
import com.mora.cafe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CategoryController implements CategoryRest {

    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<String> newCategory(Map<String, String> requestMap) {
        try {
            return categoryService.createCategory(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllCategory() {
        try {
            return categoryService.getAllCategory();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateCategory(Map<String, Long> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCategory(Map<String, Long> requestMap) {
        try {
            return categoryService.deleteCategory(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Category Delete Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
