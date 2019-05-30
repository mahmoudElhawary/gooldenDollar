package com.egypt.daily.life.shopping.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.repository.UserRepository;
import com.egypt.daily.life.shopping.util.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository ;

	@Override
	public User save(User user) {
		String password = PasswordUtil.GetPasswordHah(user.getPassword()) ;
		user.setPassword(password);
		user.setCreatedDate(new Date());
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		user.setUpdatedDate(new Date());
		return userRepository.save(user);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findById(id).get();
	}
	
}
