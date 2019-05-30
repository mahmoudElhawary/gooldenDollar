package com.egypt.daily.life.shopping.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.egypt.daily.life.shopping.security.JwtTokenUtil;

public class AuthenticationTokenFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailesService;
	@Autowired
	private JwtTokenUtil jwtTokenUtile;

	@Value("${jwt.header}")
	private String tokenHeader;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authToken = request.getHeader(this.tokenHeader);
		if (authToken != null && authToken.length() > 7) {
			authToken = authToken.substring(7);
		}
		String username = jwtTokenUtile.getUserNameFromeToken(authToken);
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails=this.userDetailesService.loadUserByUsername(username) ;
			boolean isValid = jwtTokenUtile.ValidateToken(authToken, userDetails) ;
			if(isValid) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,  null , userDetails.getAuthorities()) ;
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
