package fr.dta.formafond.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.formafond.exception.UserAlreadyExistsException;
import fr.dta.formafond.model.Order;
import fr.dta.formafond.model.User;
import fr.dta.formafond.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository urep;

	@Autowired
	PasswordEncoder pwdEncod;

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public void createUser(User u) throws UserAlreadyExistsException {
		User u_ = urep.findByUsername(u.getMail());
		if (u_ != null)
			throw new UserAlreadyExistsException(u_.getMail());
		else {
			u.setPassword(pwdEncod.encode(u.getPassword()));
			urep.save(u);
		}
	}

	public User getById(Long id) {
		return urep.get(id);
	}

	public void updateUser(User u) {
		urep.save(u);
	}

	public User getByUsername(String username) {
		return urep.findByUsername(username);
	}
	
	public List<Order> getOrdersOfUser(Long id) {
		User u = urep.get(id);
		
		List<Order> orderL=urep.getOrdersOf(u);
		
		return orderL;
	}

}
