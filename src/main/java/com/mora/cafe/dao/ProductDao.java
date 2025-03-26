package com.mora.cafe.dao;

import com.mora.cafe.pojo.Category.Category;
import com.mora.cafe.pojo.Product.NewProduct;
import com.mora.cafe.wrapper.ProductWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<NewProduct, Integer> {

    @Query("select new com.mora.cafe.wrapper.ProductWrapper(np.productName, np.category, np.isAvailable, np.manufacturedDate, np.expirationDate) from NewProduct np")
    List<ProductWrapper> getAllProducts();

    NewProduct getNewProductById(@Param("id") Long id);

    NewProduct getNewProductByCategory(@Param("category") Category category);

    @Modifying
    @Transactional
    @Query("UPDATE NewProduct np SET np.productName = :productName, np.category = :category, np.manufacturedDate = :manufacturedDate, np.expirationDate = :expirationDate where np.id = :id")
    int updateNewProductById(@Param("id") Long id, @Param("productName") String productName, @Param("category") Category category, @Param("manufacturedDate") Date manufacturedDate, @Param("expirationDate") Date expirationDate);

    @Modifying
    @Transactional
    @Query("UPDATE NewProduct np SET np.isAvailable = :isAvailable where np.id = :id")
    int updateAvailability(@Param("id") Long id, @Param("isAvailable") Boolean isAvailable);

    int deleteNewProductById(@Param("id") Long id);
}
