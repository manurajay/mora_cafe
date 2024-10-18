package com.mora.cafe.dao;

import com.mora.cafe.POJO.User;
import com.mora.cafe.wrapper.UserWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);

    List<UserWrapper> getAllUsers();
    List<UserWrapper> getAllAdmins();

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.email = :email")
    int deleteUser(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = true WHERE u.email = :email AND u.status = false")
    int approveAccount(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
    int updatePassword(@Param("email") String email, @Param("password") String password);

//    User findByUserName(String username);
}
