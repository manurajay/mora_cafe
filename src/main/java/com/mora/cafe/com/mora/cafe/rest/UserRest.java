package com.mora.cafe.com.mora.cafe.rest;

import com.mora.cafe.com.mora.cafe.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {

    // create new user
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true)Map<String, String> requestMap);

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<List<UserWrapper>> getAllUser(@RequestBody(required = true)Map<String, String> requestMap);

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteUsers(@RequestBody(required = true)Map<String, String> requestMap);

    @PostMapping(path = "/approve")
    public ResponseEntity<String> approveAcc(@RequestBody(required = true)Map<String, String> requestMap);

    @PutMapping(path = "/reset")
    public ResponseEntity<String> resetPassword(@RequestBody(required = true)Map<String, String> requestMap);

}