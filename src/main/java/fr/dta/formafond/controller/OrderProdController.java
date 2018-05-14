package fr.dta.formafond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.dta.formafond.model.OrderProduct;
import fr.dta.formafond.repository.OrderProdRepository;

@RestController
@RequestMapping("/api/oderProd")
public class OrderProdController {

	@Autowired
	private OrderProdRepository orderProdRep;

	public OrderProdController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createOrderProd(@RequestBody OrderProduct op) {
		orderProdRep.save(op);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectNode getAllOrderProd() {
		ObjectNode node= JsonNodeFactory.instance.objectNode();
		ArrayNode ops=node.putArray("orderprod");
		for(OrderProduct op : orderProdRep.getAll()) ops.add(op.toJson());
		return node;
	}

}
