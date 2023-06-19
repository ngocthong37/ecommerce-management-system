package ptithcm.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ptithcm.model.order.OrderLine;

@Entity
@Table(name = "Customer_Review")

public class CustomerReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@ManyToOne()
	@JoinColumn(name = "ordered_product_id")
	private OrderLine orderLine;
	@Column(name = "rating_value")
	private Integer ratingValue;
	@Column(name = "comment")
	private String comment;

	public CustomerReview() {
		super();
	}

	public CustomerReview(Integer id, Customer customer, OrderLine orderLine, Integer ratingValue, String comment) {
		super();
		this.id = id;
		this.customer = customer;
		this.orderLine = orderLine;
		this.ratingValue = ratingValue;
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderLine getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}

	public Integer getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(Integer ratingValue) {
		this.ratingValue = ratingValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
