package fr.dta.formafond.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dta.formafond.model.Product;
import fr.dta.formafond.model.ResultListCounted;
import fr.dta.formafond.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public ProductService() {
	}

	public Product get(Long id) {

		return productRepository.get(id);
	}

	public List<Product> getAll() {
		return productRepository.getAll();
	}

	public Product save(Product p) {
		return productRepository.save(p);
	}

	// public boolean remove(Product p) {
	// return productRepository.remove(p);
	// }

	public boolean remove(Long id) {
		return productRepository.remove(id);
		/*
		 * TODO Tester si le produit est commander
		 */
	}

	public ResultListCounted search(String name, String category, int page, int resultByPage) {
		return productRepository.search(name, category, page, resultByPage);
	}
	
	public void activate(Long prod_id) {
		Product p = this.get(prod_id);
		if(p==null) return;
		p.setActiv(true);
		productRepository.save(p);
	}
	
	public void deactivate(Long prod_id) {
		Product p = this.get(prod_id);
		if(p==null) return;
		p.setActiv(false);
		productRepository.save(p);
	}
	
	public void changeQuantity(Long id,Long newQty) {
		Product p = this.get(id);
		if(p==null) return;
		p.setQty(newQty.intValue());
		productRepository.save(p);
	}

}
