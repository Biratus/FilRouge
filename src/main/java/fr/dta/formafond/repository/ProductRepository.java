package fr.dta.formafond.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.dta.formafond.model.Category;
import fr.dta.formafond.model.Product;

@Repository
@Transactional
public class ProductRepository extends PrimeDAO<Product> {

	public ProductRepository() {
		super(Product.class);
	}

	public List<Product> search(String name, Category category) {
		@SuppressWarnings("unchecked")
		Criteria criteria = getSession().createCriteria(Product.class).setMaxResults(50);
		if (name != null) {
			criteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (category != null) {
			criteria.add(Restrictions.eq("category", category));
		}
		return criteria.list();
	}
}
