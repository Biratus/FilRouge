package fr.dta.formafond.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import fr.dta.formafond.model.Order;
import fr.dta.formafond.model.Product;
import fr.dta.formafond.service.OrderService;

@RestController
@RequestMapping("/Api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	

	public ObjectNode toJson(Order o) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		ObjectNode node = mapper.createObjectNode();
		node.put("id", o.getId());
		node.put("date", o.getDate().toString());
		node.put("user_id", o.getUser().getId());
		ArrayNode products=node.putArray("products");
		for(Product p:o.getProducts()) {
			ObjectNode product=products.addObject();
			product.put("id", p.getId());
			product.put("name", p.getName());
			product.put("type", p.getType());
			product.put("descript",p.getDescript());
			product.put("price", p.getPrice());
			product.put("category", p.getCategory().toString());
			product.put("qty", p.getQty());
			product.put("src", p.getSrc());
			product.put("activ", p.isActiv());
		}

		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return node;
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getAll() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrNode = mapper.createArrayNode();
		for (Order orders : orderService.getAll()) {
			arrNode.add(toJson(orders));
		}
		return arrNode;
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Order o) {
		Date date=new Date();
		o.setDate(date);
		orderService.save(o);
	}
	
}
