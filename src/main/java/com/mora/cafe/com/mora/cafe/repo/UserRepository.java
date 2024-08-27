package com.mora.cafe.com.mora.cafe.repo;

import com.security.test.SecUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<SecUser, Integer> {
    Optional<SecUser> findByEmail(String email);
}
