package fr.dta.formafond.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.dta.formafond.exception.ProductNotFoundException;
import fr.dta.formafond.model.Order;
import fr.dta.formafond.service.OrderService;
import fr.dta.formafond.service.ProductService;

@RestController
@RequestMapping("/Api/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	ProductService prodServ;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getAll() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrNode = mapper.createArrayNode();
		for (Order order : orderService.getAll()) {
			arrNode.add(order.toJson());
		}
		return arrNode;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveOrder(@RequestBody ObjectNode node) {
		orderService.createOrder(node.get("user").get("id").asLong(),node.get("products"));
	}

	@CrossOrigin
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectNode productIsInOrder(@PathVariable long id) {

		ObjectNode node = JsonNodeFactory.instance.objectNode();
		try {
			List<Order> orders = orderService.getOrdersWithProduct(id);
			ArrayNode arr = node.putArray("orders");
			for (Order o : orders) {
				arr.add(o.getId());
			}
		} catch (ProductNotFoundException e) {
			node.put("exception", "Product with id " + e.getProdId() + " Not Found");
		}
		return node;
	}

	@CrossOrigin
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectNode search(@RequestParam(required = false) String mail,
			@RequestParam(required = false) String lastName, @RequestParam(required = false) String firstName,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer resultByPage) {
		return orderService.search(mail, lastName, firstName, page, resultByPage).toJsonOrder();
	}

	@CrossOrigin
	@RequestMapping(value = "/price", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectNode search(@RequestParam(required = false) Integer priceMin,
			@RequestParam(required = false) Integer priceMax, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer resultByPage) {
		return orderService.search(priceMin, priceMax, page, resultByPage).toJsonPrice();
	}
}
