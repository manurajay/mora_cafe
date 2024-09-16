package com.mora.cafe.com.mora.cafe.service;

import com.mora.cafe.com.mora.cafe.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<List<UserWrapper>> getAllUser(Map<String, String> requestMap);

    ResponseEntity<String> deleteUsers(Map<String, String> requestMap);

    ResponseEntity<String> approveAcc(Map<String, String> requestMap);

    ResponseEntity<String> resetPassword(Map<String, String> requestMap);

}
