package fr.dta.formafond.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.formafond.model.User;

@Repository
@Transactional
public class UserRepository extends PrimeDAO<User>{

	public UserRepository() {
		super(User.class);
	}

}
