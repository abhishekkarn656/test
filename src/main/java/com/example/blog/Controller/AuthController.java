package com.example.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.Security.JwtAuthResponse;
import com.example.blog.Security.JwtTokenHepler;
import com.example.blog.utils.JwtAuthRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private JwtTokenHepler jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
@PostMapping("/login")
public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
	
	this.authentication(request.getUsername(),request.getPassword());
	UserDetails userDetails= this.userDetailService.loadUserByUsername(request.getUsername());
	String token = this.jwtTokenHelper.generateToken(userDetails);
	JwtAuthResponse authResponse= new JwtAuthResponse();
	authResponse.setToken(token);
	return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.OK)
;	
	
}
private void authentication(String username,String password) {
	
	UsernamePasswordAuthenticationToken authenticationtoken= new UsernamePasswordAuthenticationToken(username, password);
	try {
		this.authenticationManager.authenticate(authenticationtoken);

	} catch (BadCredentialsException e) {
		// TODO: handle exception
		System.out.println("Invalid Details");
	}
}
	
}
