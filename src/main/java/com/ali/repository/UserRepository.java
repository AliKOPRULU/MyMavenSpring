package com.ali.repository;

import com.ali.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ali on 25.10.2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
