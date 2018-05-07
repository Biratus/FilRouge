package fr.dta.formafond.model;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ResultListCounted {
	private Integer count;
	private List<Product> listSearch;

	public ResultListCounted(Integer count, List<Product> listSearch) {
		this.count = count;
		this.listSearch = listSearch;
	}

	public ResultListCounted() {

	}

	public ObjectNode toJson() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("count", this.count);
		ArrayNode r = node.putArray("listSearch");
		for (Product ls : this.getListSearch()) {
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
		return listSearch;
	}

	public void setListSearch(List<Product> listSearch) {
		this.listSearch = listSearch;
	}

	@Override
	public String toString() {
		return "ResultListCounted [count=" + count + ", listSearch=" + listSearch + "]";
	}

}
