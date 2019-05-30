package com.egypt.daily.life.shopping.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.egypt.daily.life.shopping.model.User;

public class JwtUserFactory {

	public static JwtUser creatUser(User user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), user, mapTpGrantedAuthorities(new ArrayList<String>(Arrays.asList("ROLE "+ user.getRole()))), user.isEnabled());
	}

	private static List<GrantedAuthority> mapTpGrantedAuthorities(List<String> authorities) {
		return authorities.stream().map( Authrity -> new SimpleGrantedAuthority(Authrity)).collect(Collectors.toList());
	}

}
