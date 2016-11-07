package com.ali.service;

import com.ali.domain.Item;
import com.ali.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Ali on 25.10.2016.
 */
public interface UserService {
    void addUser(User user);

    Iterable<User> getUser();

    List<String> getUserNames();

    User getUserByUserName(String userName);

    User getUserById(long id);

    Map<String,List<Item>>numberOfItemsByType(long userId);
}
