package fr.dta.formafond.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.dta.formafond.model.User;
import fr.dta.formafond.repository.UserRepository;

@Service(value = "securityService")
public class SecurityService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7673858988135194245L;

	@Autowired
	UserRepository urep;

	public SecurityService() {
		// TODO Auto-generated constructor stub
	}

	public User getConnectedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null) {
			return null;
		}
		org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) auth
				.getPrincipal();
		return urep.findByUsername(u.getUsername());
	}
}
