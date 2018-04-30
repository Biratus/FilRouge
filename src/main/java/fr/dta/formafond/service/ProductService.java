package fr.dta.formafond.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.formafond.model.Category;
import fr.dta.formafond.model.Product;
import fr.dta.formafond.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public ProductService() {
	}

	public Product get(long id) {
		return productRepository.get(id);
	}

	public List<Product> getAll() {
		return productRepository.getAll();
	}

	public Product save(Product p) {
		return productRepository.save(p);
	}

	public boolean remove(Product p) {
		return productRepository.remove(p);
	}

	public boolean remove(long id) {
		return productRepository.remove(id);
	}

	public Product filterCategory(Category category) {
		return productRepository.filterCategory(category);
	}
	
	public Product filterType(String type) {
		return productRepository.filterType(type);
	}
	
	public Product filterName(String name) {
		return productRepository.filterName(name);
	}
	
	public Product filterPrice(int price) {
		return productRepository.filterPrice(price);
	}
	
	public Product filterQuantity(int qty) {
		return productRepository.filterQuantity(qty);
	}
}
