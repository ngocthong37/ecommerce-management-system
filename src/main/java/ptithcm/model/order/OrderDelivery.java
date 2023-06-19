package ptithcm.model.order;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import ptithcm.model.shop.ShopOrder;
import ptithcm.model.user.User;

@Entity
@Table(name = "Order_Delivery")
public class OrderDelivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "order_id")
	private ShopOrder shopOrder;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "delivery_date")
	private Date deliveryDate;
	@Column(name = "received_date")
	private Date receivedDate;

	@ManyToOne()
	@JoinColumn(name = "delivery_status_id")
	private DeliveryStatus deliveryStatus;
	
	public OrderDelivery() {
		super();
	}

	public OrderDelivery(ShopOrder shopOrder, User user, Date createdAt, Date deliveryDate, Date receivedDate, DeliveryStatus deliveryStatus) {
		super();
		this.shopOrder = shopOrder;
		this.user = user;
		this.createdAt = createdAt;
		this.deliveryDate = deliveryDate;
		this.receivedDate = receivedDate;
		this.deliveryStatus = deliveryStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShopOrder getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(ShopOrder shopOrder) {
		this.shopOrder = shopOrder;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	
	
}
