package fr.dta.formafond.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.formafond.model.Order;
import fr.dta.formafond.model.Product;

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

}
