package fr.dta.formafond.repository;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.formafond.model.User;

@Repository
@Transactional
public class UserRepository extends PrimeDAO<User>{

	public UserRepository() {
		super(User.class);
	}

	public User findByUsername(String username) {
		TypedQuery<User> query = em.createQuery("select  u from User u where username=:name",User.class);
		query.setParameter("name", username);
		return query.getSingleResult();
	}
}
