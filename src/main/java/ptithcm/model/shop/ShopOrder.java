package ptithcm.model.shop;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ptithcm.model.customer.CustomerAddress;
import ptithcm.model.order.OrderDelivery;
import ptithcm.model.order.OrderLine;
import ptithcm.model.order.OrderStatus;
import ptithcm.model.pay.CustomerPaymentMethod;
import ptithcm.model.ship.ShippingMethod;

@Entity
@Table(name = "Shop_Order")
public class ShopOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "shipping_method_id")
	private ShippingMethod shippingMethod;

	@ManyToOne()
	@JoinColumn(name = "shipping_address_id")
	private CustomerAddress customerAddress;

	@ManyToOne()
	@JoinColumn(name = "order_status_id")
	private OrderStatus orderStatus;
	@ManyToOne()
	@JoinColumn(name = "payment_method_id")
	private CustomerPaymentMethod customerPaymentMethod;

	@OneToMany(mappedBy = "shopOrder", fetch = FetchType.LAZY)
	private Collection<OrderLine> orderLines;

	@OneToMany(mappedBy = "shopOrder", fetch = FetchType.LAZY)
	private Collection<OrderDelivery> orderDeliveries;

	@Column(name = "order_date")
	private Date orderDate;
	@Column(name = "order_total")
	private Long orderTotal;

	@Column(name = "address_delivery")
	private String addressDelivery;

	public ShopOrder() {
		super();
	}

	public ShopOrder(Integer id, ShippingMethod shippingMethod, CustomerAddress customerAddress,
			OrderStatus orderStatus, CustomerPaymentMethod customerPaymentMethod, Collection<OrderLine> orderLines,
			Date orderDate, Long orderTotal, String addressDelivery) {
		super();
		this.id = id;
		this.shippingMethod = shippingMethod;
		this.customerAddress = customerAddress;
		this.orderStatus = orderStatus;
		this.customerPaymentMethod = customerPaymentMethod;
		this.orderLines = orderLines;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.addressDelivery = addressDelivery;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public CustomerAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public CustomerPaymentMethod getCustomerPaymentMethod() {
		return customerPaymentMethod;
	}

	public void setCustomerPaymentMethod(CustomerPaymentMethod customerPaymentMethod) {
		this.customerPaymentMethod = customerPaymentMethod;
	}

	public Collection<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Collection<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Long orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getAddressDelivery() {
		return addressDelivery;
	}

	public void setAddressDelivery(String addressDelivery) {
		this.addressDelivery = addressDelivery;
	}

}
