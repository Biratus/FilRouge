package fr.dta.formafond.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.formafond.model.OrderProduct;

@Repository
@Transactional
public class OrderProdRepository extends PrimeDAO<OrderProduct> {
	
	public OrderProdRepository() {super(OrderProduct.class);}

}
