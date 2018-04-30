package fr.dta.formafond.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.dta.formafond.model.User;
import fr.dta.formafond.repository.UserRepository;

@Component
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository urep;

	public AuthenticationService() {}

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		
		User u= urep.findByUsername(mail);
		
		if(u!=null) {
			List<GrantedAuthority> rules = u.getAuthorities();
            return new org.springframework.security.core.userdetails.User(u.getMail(), u.getPassword(), rules);
		}
		
		throw new UsernameNotFoundException("User not found");
	}

}