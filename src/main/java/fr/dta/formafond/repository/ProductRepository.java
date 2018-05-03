package fr.dta.formafond.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public List<Product> search(String name, String category, int page, int resultByPage) {
		String[] listCategories = category.isEmpty()?null:category.split("-");

		Criteria criteria = getSession().createCriteria(Product.class).setMaxResults(50);
		if (name != null && !name.isEmpty()) {
			criteria.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (listCategories != null && listCategories.length != 0) {
			for (String ct : listCategories) {
				System.out.println(listCategories);
				System.out.println(" cat: ."+ct+".");
			}
			criteria.add(Restrictions.in("category", Stream.of(listCategories).map(str -> Category.fromString(str)).collect(Collectors.toList())));
		}
		criteria.setFirstResult(resultByPage * (page - 1)).setMaxResults(resultByPage);
		return criteria.list();
	}
}
