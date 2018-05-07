package fr.dta.formafond.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.formafond.model.Order;

@Repository
@Transactional
public class OrderRepository  extends PrimeDAO<Order>{
	
	public OrderRepository() {
		super(Order.class);
	}
	
}
