package fr.dta.formafond.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
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

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Product> products;

	private Date date;

	public Order() {

	}

	public Order(Long id, Date date) {
		this.id = id;
		this.date = date;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", products=" + products + ", date=" + date + "]";
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
		for (Product p : this.products) {
			products.add(p.toJson());
		}

		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return node;
	}

}
