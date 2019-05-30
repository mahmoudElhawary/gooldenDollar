package com.egypt.daily.life.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.Code;
import com.egypt.daily.life.shopping.model.User;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long>{
	
	List<Code> findByCodeTypeAndUser(int codeType, User user);
	
	Code findByCodeStr(String codeStr);
}
