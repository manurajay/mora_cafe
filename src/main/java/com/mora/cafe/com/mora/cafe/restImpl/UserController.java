package com.mora.cafe.com.mora.cafe.restImpl;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.mora.cafe.com.mora.cafe.POJO.SecUser;
import com.mora.cafe.com.mora.cafe.POJO.User;
import com.mora.cafe.com.mora.cafe.serviceImpl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<SecUser> authenticatedUser() {
        logger.info("Endpoint /me hit");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        logger.info("Principal class: " + authentication.getPrincipal().getClass().getName());


        if (principal instanceof SecUser) {
            SecUser currentUser = (SecUser) principal;
            return ResponseEntity.ok(currentUser);
        } else {
            // Handle cases where the principal is not a SecUser, possibly return an error response or null
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        System.out.println("sending all users wait until we get the request");
        logger.info("end point hitted");
        List<User> users = userService.allUsers();
        logger.info("Number of users found: " + users.size());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testing() {
        System.out.println("we are testing endpoint");
        return ResponseEntity.ok("tested");
    }
}
