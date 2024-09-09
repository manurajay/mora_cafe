package com.mora.cafe.com.mora.cafe.dao;

import com.mora.cafe.com.mora.cafe.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);


//    User findByUserName(String username);
}
