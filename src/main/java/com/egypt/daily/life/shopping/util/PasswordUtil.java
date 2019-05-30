package com.egypt.daily.life.shopping.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder() ;
	public static String GetPasswordHah(String password) {
		return bCryptPasswordEncoder.encode(password);
	}

}
