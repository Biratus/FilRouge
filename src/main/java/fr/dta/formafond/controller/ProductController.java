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
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.dta.formafond.model.Category;
import fr.dta.formafond.model.Product;
import fr.dta.formafond.service.ProductService;

@RestController
@RequestMapping("/Api/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@CrossOrigin
	@RequestMapping(value = "/{searchId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getProduct(@PathVariable("searchId") long id) {
		return productService.get(id).toJson();
	}

	@CrossOrigin
	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode getAll() {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrNode = mapper.createArrayNode();
		for (Product products : productService.getAll()) {
			arrNode.add(products.toJson());
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
	public ObjectNode search(@RequestParam(required = false) String name,
			@RequestParam(required = false) String category, @RequestParam(required = false) int page,
			@RequestParam(required = false) int resultByPage) {
		return productService.search(name, category, page, resultByPage).toJson();
	}

	@CrossOrigin
	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getCategories() {
		return Category.getCategories();
	}
}
