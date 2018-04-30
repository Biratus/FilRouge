package fr.dta.formafond.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.dta.formafond.model.Category;
import fr.dta.formafond.model.Product;

@Repository
@Transactional
public class ProductRepository extends PrimeDAO<Product> {

	public ProductRepository() {
		super(Product.class);
	}

	public Product filterCategory(Category category) {
		return em.find(Product.class, category);
	}
	
	public Product filterType(String type) {
		return em.find(Product.class, type);
	}
	
	public Product filterName(String name) {
		return em.find(Product.class, name);
	}
	
	public Product filterPrice(int price) {
		return em.find(Product.class, price);
	}
	
	public Product filterQuantity(int qty) {
		return em.find(Product.class, qty);
	}
}
