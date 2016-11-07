package com.ali.domain;

import com.ali.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

/**
 * Created by Ali on 26.10.2016.
 */
@Component
public class RegisterValidator implements Validator {
    private final UserService userService;

    @Autowired
    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

//    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

//    @Override
    public void validate(Object o , Errors errors){
        User form=(User) o;
        validateUserName(errors,form);
    }

    private void validateUserName(Errors errors, User form) {//kontrol ettiğimiz tek şey girilen kullanıcı adının sistemde olup olmadığı.
        if (userService.getUserByUserName(form.getUserName())!=null);{//register sayfamızda onay için şifrenin iki kez girilmesini istesek,
            errors.reject("userName.exists","User with this username already exists ");//burada girilen iki şifrenin birbiriyle aynı olup olmadığını kontrol edebilirdik.
        }
    }


    @Override
    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        return null;
    }

    @Override
    public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }

    @Override
    public ExecutableValidator forExecutables() {
        return null;
    }
}
