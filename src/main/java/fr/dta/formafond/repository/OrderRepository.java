package fr.dta.formafond.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.formafond.model.Order;
import fr.dta.formafond.model.Product;
import fr.dta.formafond.model.ResultListCounted;
import fr.dta.formafond.model.User;

@Repository
@Transactional
public class OrderRepository extends PrimeDAO<Order> {

	public OrderRepository() {
		super(Order.class);
	}

	public List<Order> getOrderWithProduct(Product p) {
		// trouver le produit avec l'id : p
		TypedQuery<Order> query = em.createQuery("select o from Order o where :p in elements(o.products)", Order.class);
		query.setParameter("p", p);
		return query.getResultList();
	}
	
	public ResultListCounted search(String mail, String lastName, String firstName, int page, int resultByPage) {
		// Criteria Count
		Criteria criteriaCount = getSession().createCriteria(User.class);
		constructQuerySearch(criteriaCount, mail, lastName, firstName);
		Integer count = ((Long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		// Criteria Search
		Criteria criteriaSearch = getSession().createCriteria(User.class);

		// Criteria ResultList
		constructQuerySearch(criteriaSearch, mail, lastName, firstName);
		Criteria listSearch = criteriaSearch.setFirstResult(resultByPage * (page - 1)).setMaxResults(resultByPage);
		List<Product> searchList = criteriaSearch.list();
		return new ResultListCounted(count, searchList,User.class);
	}

	private void constructQuerySearch(Criteria criteria, String mail, String lastName, String firstName) {
		// Criteria Search by mail
		if (mail != null && !mail.isEmpty()) {
			criteria.add(Restrictions.ilike("mail", "%" + mail + "%"));
		}
		if (lastName != null && !lastName.isEmpty()) {
			criteria.add(Restrictions.ilike("lastName", "%" + lastName + "%"));
		}
		if (firstName != null && !firstName.isEmpty()) {
			criteria.add(Restrictions.ilike("firstName", "%" + firstName + "%"));
		}	
	}
	
	public ResultListCounted searchPrice(Integer priceMin, Integer priceMax, int page, int resultByPage) {
		// Criteria Count
		Criteria criteriaCount = getSession().createCriteria(Order.class);
		constructQuerySearchPrice(criteriaCount, priceMin, priceMax);
		Integer count = ((Long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		
		// Criteria Search
		Criteria criteriaSearch = getSession().createCriteria(Order.class);

		// Criteria ResultList
		constructQuerySearchPrice(criteriaSearch, priceMin, priceMax);
		Criteria listSearch = criteriaSearch.setFirstResult(resultByPage * (page - 1)).setMaxResults(resultByPage);
		List<Product> searchList = criteriaSearch.list();
		return new ResultListCounted(count, searchList,Order.class);
	}
	
	private void constructQuerySearchPrice(Criteria criteria, Integer priceMin, Integer priceMax) {
		//Criteria Search by Price
		if (priceMin != null) {
			criteria.add(Restrictions.ge("priceTot", priceMin));
		}
		if (priceMax != null) {
			criteria.add(Restrictions.le("priceTot", priceMax));
		}
	}
}
