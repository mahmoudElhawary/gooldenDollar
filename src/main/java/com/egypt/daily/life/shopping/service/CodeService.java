package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.Code;
import com.egypt.daily.life.shopping.model.User;

public interface CodeService {
	
	List<Code> findByCodeTypeAndUser(int codeType, User user);
	
	void save(Code code);
	
	Code findByCodeStr(String codeStr);
	
	void delete(Code code);
}
