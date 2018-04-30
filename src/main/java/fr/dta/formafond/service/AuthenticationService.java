package fr.dta.formafond.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.dta.formafond.repository.UserRepository;

@Component
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository urep;

	public AuthenticationService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("admin".equals(username)) {//ADMIN
			System.out.println("ADMIN");
			return new User(username,"pwd",Arrays.asList(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER")));
		} else if("user".equals(username)) {//USER
			System.out.println("USER");
			return new User(username,"pwd",Arrays.asList(new SimpleGrantedAuthority("USER")));
		} else { //VISITOR
			System.out.println("VISITOR");
			return new User("toto","pwd",Arrays.asList());
		}
	}

}
