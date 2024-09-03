package com.example.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog.Exception.ResourceNotFoundException;
import com.example.blog.Model.User;
import com.example.blog.Repository.UserRepo;
@Service
public class CustomUserDetailService  implements UserDetailsService{

	@Autowired
	private UserRepo userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO loading user  from database
		User user=this.userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user","email"+username,0));
		return user;
	}

}
