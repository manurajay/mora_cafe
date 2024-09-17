package com.mora.cafe.com.mora.cafe.serviceImpl;

import com.mora.cafe.com.mora.cafe.POJO.Product.NewProduct;
import com.mora.cafe.com.mora.cafe.dao.ProductDao;
import com.mora.cafe.com.mora.cafe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public ResponseEntity<String> createProduct(Map<String, Object> requestMap) {
//        try {
//            NewProduct newProduct = productDao.getNewProductById((Long) requestMap.get("id"));
//        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateProduct(Map<String, Object> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteProduct(Map<String, Long> requestMap) {
        return null;
    }
}
