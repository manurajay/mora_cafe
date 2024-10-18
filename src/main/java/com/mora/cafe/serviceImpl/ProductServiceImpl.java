package com.mora.cafe.serviceImpl;

import com.mora.cafe.POJO.Category.Category;
import com.mora.cafe.POJO.Product.NewProduct;
import com.mora.cafe.dao.ProductDao;
import com.mora.cafe.service.ProductService;
import com.mora.cafe.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createProduct(Map<String, Object> requestMap) {
        try {
            if (validateResponse(requestMap)) {
                return new ResponseEntity<>(getProductMap(requestMap), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid JSON", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getAllProducts() {
        try {
            List<ProductWrapper> productList = productDao.getAllProducts();
            if (Objects.isNull(productList)) {
                return new ResponseEntity<>(productList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateProduct(Map<String, Object> requestMap) {
        try {
            if (validateResponse(requestMap)) {
                NewProduct existingProduct = productDao.getNewProductById((Long) requestMap.get("id"));
                if (!Objects.isNull(existingProduct)) {
                    existingProduct.setProductName(requestMap.get("productName").toString());
                    existingProduct.setCategory((Category) requestMap.get("category"));
                    existingProduct.setManufacturedDate((Date) requestMap.get("manufacturedDate"));
                    existingProduct.setExpirationDate((Date) requestMap.get("expirationDate"));
                    productDao.save(existingProduct);
                } else {
                    return new ResponseEntity<>("No product available", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Invalid JSON", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProduct(Map<String, Long> requestMap) {
        try {
            if (productDao.deleteNewProductById((Long) requestMap.get("id")) == 1) {
                return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No product Available", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Boolean> updateAvailable(Long id) {
        try {
            NewProduct existingProduct = productDao.getNewProductById(id);
            if (!Objects.isNull(existingProduct)) {
                existingProduct.setIsAvailable();
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Boolean validateResponse(Map<String, Object> requestMap) {
        return requestMap.containsKey("prodctName") &&
                requestMap.containsKey("category") &&
                requestMap.containsKey("manufacturedDate") &&
                requestMap.containsKey("expirationDate");
    }

    private NewProduct getProductMap(Map<String, Object> requestMap) {
        if (validateResponse(requestMap)) {
            NewProduct newProduct = new NewProduct();
            newProduct.setProductName(requestMap.get("productName").toString());
            newProduct.setCategory((Category) requestMap.get("category"));
            newProduct.setIsAvailable(false);
            newProduct.setManufacturedDate((Date) requestMap.get("manufacturedDate"));
            newProduct.setExpirationDate((Date) requestMap.get("expirationDate"));
            return newProduct;
        }
        return null;
    }
}
