package com.egypt.daily.life.shopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByEmailIgnoreCase(String username);

}
