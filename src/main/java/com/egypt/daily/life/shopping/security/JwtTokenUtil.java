package com.egypt.daily.life.shopping.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4695906060488491034L;

	static final String CLAM_KEY_USERNAME = "sub";
	static final String CLAM_KEY_AUDUIENCE = "audience";
	static final String CLAM_KEY_CREATED = "created";
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String getUserNameFromeToken(String authToken) {
		String userName;
		try {
			final Claims claims = getClaimsFromToken(authToken);
			userName = claims.getSubject();
		} catch (Exception e) {
			userName = null;
		}
		return userName;
	}

	private Claims getClaimsFromToken(String authToken) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken).getBody();

		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	public boolean ValidateToken(String authToken, UserDetails userDetails) {
		JwtUser user = (JwtUser) userDetails;
		final String username = getUserNameFromeToken(authToken);

		return (username.equals(user.getUsername()) && !isTokenExpired(authToken));
	}

	private boolean isTokenExpired(String authToken) {
		final Date expiration = getExpirationDateFromToken(authToken);
		return expiration.before(new Date());
	}

	private Date getExpirationDateFromToken(String authToken) {
		Date expiration = null;
		try {
			final Claims claims = getClaimsFromToken(authToken);
			if (claims != null) {
				expiration = claims.getExpiration();
			} else {
				expiration = null;
			}
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	public String generateToken(JwtUser jwtUser) {
		Map<String, Object> claims = new HashMap<String, Object>() ;
		claims.put(CLAM_KEY_USERNAME, jwtUser.getUsername() ) ;
		claims.put(CLAM_KEY_CREATED, new Date()) ;
		return generateToken(claims);
	}

	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis()+ expiration * 1000);
	}
}
