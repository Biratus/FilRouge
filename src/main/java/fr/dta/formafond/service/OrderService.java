package fr.dta.formafond.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.formafond.exception.ProductNotFoundException;
import fr.dta.formafond.model.Order;
import fr.dta.formafond.model.Product;
import fr.dta.formafond.repository.OrderRepository;
import fr.dta.formafond.repository.ProductRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository prodRep;

	public OrderService() {

	}

	public List<Order> getAll() {
		return orderRepository.getAll();
	}

	public Order save(Order o) {
		return orderRepository.save(o);
	}

	public boolean remove(Long id) {
		return orderRepository.remove(id);
	}

	public List<Order> getOrdersWithProduct(Long id) throws ProductNotFoundException {
		Product p = this.prodRep.get(id);
		if (p == null)
			throw new ProductNotFoundException(id);
		else {
			return orderRepository.getOrderWithProduct(p);
		}
	}
}