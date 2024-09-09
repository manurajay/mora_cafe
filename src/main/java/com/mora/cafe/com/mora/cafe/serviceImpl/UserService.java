package com.mora.cafe.com.mora.cafe.serviceImpl;

import com.mora.cafe.com.mora.cafe.POJO.SecUser;
import com.mora.cafe.com.mora.cafe.POJO.User;
import com.mora.cafe.com.mora.cafe.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        users.addAll(userRepository.findAll());

        return users;
    }
}
