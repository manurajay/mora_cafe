package com.mora.cafe.serviceImpl;

import com.mora.cafe.POJO.User;
import com.mora.cafe.constants.CafeConstants;
import com.mora.cafe.dao.UserDao;
import com.mora.cafe.service.UserService;
import com.mora.cafe.utils.CafeUtils;
import com.mora.cafe.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // create a new user
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if(Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Registered Succefully", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Email already exits.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser(Map<String, String> requestMap) {
        try {
            if (requestMap.containsValue("ROLE_USER")) {
                if (! userDao.getAllAdmins().isEmpty()) {
                    return new ResponseEntity<>(userDao.getAllUsers(), HttpStatus.OK);
                }
            } else if (requestMap.containsValue("ROLE_ADMIN")) {
                return new ResponseEntity<>(userDao.getAllAdmins(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // delete user by email
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUsers(Map<String, String> requestMap) {
        try {
            if (userDao.deleteUser(requestMap.get("email")) == 1) {
                return new ResponseEntity<>("User Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not deleted2", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Not deleted3", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // approve user account
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> approveAcc(Map<String, String> requestMap) {
        try {
             if (userDao.approveAccount(requestMap.get("email")) == 1) {
                 return new ResponseEntity<>("Account Approved", HttpStatus.OK);
             } else {
                 return new ResponseEntity<>("Not Approved", HttpStatus.BAD_REQUEST);
             }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Failed to Approved", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // reset password
    @Override
    public ResponseEntity<String> resetPassword(Map<String, String> requestMap) {
        try {
            User user = userDao.findByEmailId(requestMap.get("email"));
            if (!Objects.isNull(user)) {
                user.setPassword(passwordEncoder.encode(requestMap.get("password")));
                userDao.save(user);
                return new ResponseEntity<>("Reset Password Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to reset password", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Access denied", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // validate the request map
    private boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("password");

    }

    //create new user object and return
    private User getUserFromMap(Map<String, String> requestMap)  {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus(false);
        return user;
    }
}
