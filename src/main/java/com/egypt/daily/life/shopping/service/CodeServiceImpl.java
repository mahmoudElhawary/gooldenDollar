package com.egypt.daily.life.shopping.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egypt.daily.life.shopping.model.Code;
import com.egypt.daily.life.shopping.model.User;
import com.egypt.daily.life.shopping.repository.CodeRepository;


@Service
public class CodeServiceImpl implements CodeService{

	@Autowired
	private CodeRepository codeRepository;
	
	@Override
	public void save(Code code){
		SecureRandom random = new SecureRandom();
    	String codeStr = new BigInteger(130, random).toString(32);
    	code.setCodeStr(codeStr);
		codeRepository.save(code);
	}
	
	@Override
	public Code findByCodeStr(String codeStr){
		return codeRepository.findByCodeStr(codeStr);
	}
	@Override
	public void delete(Code code){
		codeRepository.delete(code);
	}
	@Override
	public List<Code> findByCodeTypeAndUser(int codeType, User user){
		return codeRepository.findByCodeTypeAndUser(codeType, user);
	}
}
