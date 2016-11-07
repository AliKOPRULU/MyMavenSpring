package com.ali.service;

import com.ali.domain.Item;
import com.ali.domain.User;
import com.ali.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Ali on 25.10.2016.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Iterable<User> getUser() {
        return userRepository.findAll();
    }

    public List<String> getUserNames() {
        List<String> userNames = new ArrayList<String>();
        Iterator iterator = getUser().iterator();

        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            userNames.add(user.getUserName());
        }
        return userNames;
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User getUserById(long id) {//User id bul
        return userRepository.findOne(id);
    }

    @Override
    public Map<String, List<Item>> numberOfItemsByType(long userId) {
        Map<String, List<Item>> map = new HashMap<String, List<Item>>();//Item listesi getiriliyor
        Set<Item> items = getUserById(userId).getItems();//kullanıcıya atanmış itemler

        for (Item item : items) {
            List<Item> itemList = new ArrayList<Item>();
            String key = item.getType().toLowerCase();

            if (map.containsKey(key))
                itemList = map.get(key);

            itemList.add(item);
            map.put(key, itemList);
        }
        return map;
    }

//    @Override
    public UserDetails loadUserByUserName(String userName) throws UsernameNotFoundException {//Spring Security’nin user girişini (user, gerçekten var olan login formunu mu kullanıyor,
        User user = getUserByUserName(userName);//girilen password doğru mu, user’ın sistemdeki rolü veya yetkileri nedir gibi) loadUserByUsername methoduyla kontrol ettiği bir interface.
        if (user == null) {
            throw new UsernameNotFoundException("User with username:" + userName + "not found.");
        } else {
            return user;
        }
    }


}
