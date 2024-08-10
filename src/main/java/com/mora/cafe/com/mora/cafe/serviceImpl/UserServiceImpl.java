package com.mora.cafe.com.mora.cafe.serviceImpl;

import com.mora.cafe.com.mora.cafe.POJO.User;
import com.mora.cafe.com.mora.cafe.constants.CafeConstants;
import com.mora.cafe.com.mora.cafe.dao.UserDao;
import com.mora.cafe.com.mora.cafe.service.UserService;
import com.mora.cafe.com.mora.cafe.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

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
        user.setContactNumebr(requestMap.get("contactNumber"));
        user.setEmial(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
