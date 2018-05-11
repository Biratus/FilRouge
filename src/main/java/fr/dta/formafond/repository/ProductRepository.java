package fr.dta.formafond.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.dta.formafond.model.Category;
import fr.dta.formafond.model.Product;
import fr.dta.formafond.model.ResultListCounted;

@Repository
@Transactional
public class ProductRepository extends PrimeDAO<Product> {

	public ProductRepository() {
		super(Product.class);
	}

	public ResultListCounted search(String name, String category, int page, int resultByPage) {
		String[] listCategories = category.isEmpty() ? null : category.split("-");

		// Criteria Count
		Criteria criteriaCount = getSession().createCriteria(Product.class);
		constructQuerySearch(criteriaCount, name, listCategories);
		Integer count = ((Long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		// Criteria Search
		Criteria criteriaSearch = getSession().createCriteria(Product.class);
		criteriaSearch.addOrder(Order.asc("price"));

		// Criteria ResultList
		constructQuerySearch(criteriaSearch, name, listCategories);
		Criteria listSearch = criteriaSearch.setFirstResult(resultByPage * (page - 1)).setMaxResults(resultByPage);
		List<Product> searchList = criteriaSearch.list();
		return new ResultListCounted(count, searchList,Product.class);
	}

	private void constructQuerySearch(Criteria criteria, String name, String[] listCategories) {
		// Criteria Search by name
		if (name != null && !name.isEmpty()) {
			criteria.add(Restrictions.ilike("name", "%" + name + "%"));
		}
		// Criteria Search by categories
		if (listCategories != null && listCategories.length != 0) {
			criteria.add(Restrictions.in("category",
					Stream.of(listCategories).map(str -> Category.fromString(str)).collect(Collectors.toList())));
		}
	}
}
