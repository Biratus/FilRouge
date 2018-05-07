package fr.dta.formafond.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.formafond.model.Order;
import fr.dta.formafond.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public OrderService(){
		
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
}
