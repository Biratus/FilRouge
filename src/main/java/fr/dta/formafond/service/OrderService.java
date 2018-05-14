package fr.dta.formafond.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import fr.dta.formafond.exception.ProductNotFoundException;
import fr.dta.formafond.model.Order;
import fr.dta.formafond.model.OrderProduct;
import fr.dta.formafond.model.Product;
import fr.dta.formafond.model.ResultListCounted;
import fr.dta.formafond.model.User;
import fr.dta.formafond.repository.OrderProdRepository;
import fr.dta.formafond.repository.OrderRepository;
import fr.dta.formafond.repository.ProductRepository;
import fr.dta.formafond.repository.UserRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository prodRep;
	
	@Autowired
	UserRepository uRep;
	
	@Autowired
	OrderProdRepository opRep;

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

	public ResultListCounted search(String mail, String lastName, String firstName, int page, int resultByPage) {
		return orderRepository.search(mail, lastName, firstName, page, resultByPage);
	}

	public ResultListCounted search(Integer priceMin, Integer priceMax, Integer page, Integer resultByPage) {
		return orderRepository.searchPrice(priceMin, priceMax, page, resultByPage);
	}

	public void createOrder(Long user_id, JsonNode products) {
		Order o = new Order();
		Date date = new Date();
		o.setDate(date);
		
		// user
		User user = uRep.get(user_id);
		o.setUser(user);

		// price
		Integer priceTot = 0;
		for (JsonNode pNode : products) {
			Product p = prodRep.get(pNode.get("id").asLong());
			priceTot += (pNode.get("qty").asInt()) + p.getPrice();
		}
		o.setPriceTot(priceTot);

		orderRepository.save(o);

		for (JsonNode pNode : products) {
			Product p = prodRep.get(pNode.get("id").asLong());

			OrderProduct op = new OrderProduct(o, p, pNode.get("quantity").asInt());
			opRep.save(op);
		}
	}
}