package fr.dta.formafond.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Entity
@SequenceGenerator(name = "order_gen", sequenceName = "order_gen", initialValue = 100, allocationSize = 1)
@Table(name = "order_")
public class Order extends PrimeModel {

	@Id
	@GeneratedValue(generator = "order_gen")
	private Long id;

	@NotNull
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="order",fetch=FetchType.EAGER)
	private Set<OrderProduct> orderProd=new HashSet<>();

	private Date date;
	private Integer priceTot;

	public Order() {

	}

	public Order(Long id, Date date, Integer priceTot) {
		this.id = id;
		this.date = date;
		this.priceTot = priceTot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", products=" + orderProd + ", date=" + date + ", priceTot=" + priceTot+ "]";
	}

	public ObjectNode toJson() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		ObjectNode node = mapper.createObjectNode();
		node.put("id", this.id);
		node.put("date", this.date.toString());
		node.put("user_id", this.user.getId());
		ArrayNode products = node.putArray("products");
		for (OrderProduct op : this.orderProd) {
			products.add(op.toJson());
		}
		return node;
	}

	public Integer getPriceTot() {
		return priceTot;
	}

	public void setPriceTot(Integer priceTot) {
		this.priceTot = priceTot;
	}

	public Set<OrderProduct> getOrderProd() {
		return orderProd;
	}

	public void setOrderProd(Set<OrderProduct> orderProd) {
		this.orderProd = orderProd;
	}

	
}
