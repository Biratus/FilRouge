package fr.dta.formafond.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Entity
@SequenceGenerator(name = "orderprod_gen", sequenceName = "orderprod_gen", initialValue = 100, allocationSize = 1)
public class OrderProduct extends PrimeModel {
	
	@Id
	@GeneratedValue(generator="orderprod_gen")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="order_id")
	private Order order;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;
	
	private int quantity;

	
	public OrderProduct() {}

	public OrderProduct(Order o,Product p, int qty) {
		this.order=o;
		this.product=p;
		this.quantity=qty;
	}

	@Override
	public Long getId() {
		return this.id;
	}
	
	public ObjectNode toJson() {
		ObjectNode node=this.product.toJson();
		node.put("qty", this.quantity);

		return node;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "OrderProduct [id=" + id + ", order=" + order + ", product=" + product + ", quantity=" + quantity + "]";
	}


}
