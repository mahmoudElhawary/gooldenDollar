package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	User getUserByEmail(String email);

	User getUser(Long id);

	User updateUser(User userDB);

	User findOne(Long id);

}
