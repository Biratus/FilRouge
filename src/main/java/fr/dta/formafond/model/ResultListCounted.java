package fr.dta.formafond.model;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ResultListCounted {
	private Integer count;
	private List<Product> listSearchProd;
	private List<User> listSearchOrder;
	private List<Order> listSearchPrice;

	/*
	 * Attention ce code est expérimental
	 * 
	 */
	
	public <T> ResultListCounted(Integer count, List<T> listSearch,Class klass) {
		this.count = count;
		if(Product.class.equals(klass)) this.listSearchProd =(List<Product>) listSearch;
		else if(User.class.equals(klass)) this.listSearchOrder=(List<User>)listSearch;
		else if(Order.class.equals(klass)) this.listSearchPrice=(List<Order>)listSearchPrice;
	}

	public ResultListCounted() {

	}

	public ObjectNode toJson() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("count", this.count);
		ArrayNode r = node.putArray("listSearch");
		for (Product ls : listSearchProd) {
			r.add(ls.toJson());
		}
		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return node;
	}
	
	public ObjectNode toJsonOrder() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("count", this.count);
		ArrayNode r = node.putArray("listSearch");
		for (User ls : listSearchOrder) {
			r.add(ls.toJson());
		}
		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return node;
	}
	
	public ObjectNode toJsonPrice() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("count", this.count);
		ArrayNode r = node.putArray("listSearch");
		for (Order ls : listSearchPrice) {
			r.add(ls.toJson());
		}
		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return node;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Product> getListSearch() {
		return listSearchProd;
	}

	public void setListSearch(List<Product> listSearch) {
		this.listSearchProd = listSearch;
	}

	@Override
	public String toString() {
		return "ResultListCounted [count=" + count + ", listSearch=" + listSearchProd + "]";
	}

}
