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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.dta.formafond.model.Category;
import fr.dta.formafond.model.Product;
import fr.dta.formafond.service.ProductService;

@RestController
@RequestMapping("/Api/product")
public class ProductController {

	@Autowired
	ProductService productService;

	public ObjectNode toJson(Product p) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("id", p.getId());
		node.put("name", p.getName());
		node.put("type", p.getType());
		node.put("price", p.getPrice());
		node.put("category", p.getCategory().toString());
		node.put("qty", p.getQty());
		node.put("src", p.getSrc());
		node.put("activ", p.isActiv());

		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return node;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{searchId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getProduct(@PathVariable("searchId") long id) {
		return toJson(productService.get(id));
	}

	@CrossOrigin
	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getAll() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrNode = mapper.createArrayNode();
		for (Product products : productService.getAll()) {
			arrNode.add(toJson(products));
		}
		return arrNode;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody Product p) {
		productService.save(p);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long id) {
		productService.remove(id);
	}

	// @CrossOrigin
	// @RequestMapping(method = RequestMethod.DELETE, consumes =
	// MediaType.APPLICATION_JSON_VALUE)
	// public void remove(@RequestBody Product p) {
	// System.out.println(p);
	// productService.remove(p);
	// }

	@CrossOrigin
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> search(@RequestParam(required = false) String name,
			@RequestParam(required = false) Category category, @RequestParam(required = false) int page,
			@RequestParam(required = false) int resultByPage) {
		return productService.search(name, category, page, resultByPage);
	}

}
