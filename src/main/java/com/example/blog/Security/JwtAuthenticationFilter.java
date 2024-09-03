package com.example.blog.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
@Autowired
private UserDetailsService userDetailService;

@Autowired
private JwtTokenHepler jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String requestToken = request.getHeader("Authorization");
		String username=null;
		String token= null;
		System.out.println(requestToken);
		if (requestToken !=null && requestToken.startsWith("Bearer ")) {
			 token= requestToken.substring(7);
			try {
				username=this.jwtTokenHelper.extractUsername(token);
			} catch (JwtException e) {
				// TODO: handle exception
				System.out.println("unable to get token");
			}
		}
		else {
			System.out.println("jwt token doesn't begin with token");
		}
		if (username!=null&& SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails=this.userDetailService.loadUserByUsername(username);
			if (this.jwtTokenHelper.validateToken(token, userDetails)) {
				// it is working and will autheniticate
				
				UsernamePasswordAuthenticationToken userNamePasswordAutheniticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				userNamePasswordAutheniticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userNamePasswordAutheniticationToken);
			}else {
				System.out.println("Invalid Jwt token");
			}
		}
		else {
			System.out.println("username is null or Security Context is null");
		}
		filterChain.doFilter(request, response);
	}

	
}
