package fr.dta.formafond.model;

import java.util.List;

public class ResultListCounted {
	private Integer count;
	private List<Product> listSearch;

	public ResultListCounted (Integer count, List<Product> listSearch) {
		this.count = count;
		this.listSearch = listSearch;
	}
	
	public ResultListCounted () {
		
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


