package com.mora.cafe.repo;


import com.mora.cafe.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    Boolean existsByUsername(String userName);

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);


}
