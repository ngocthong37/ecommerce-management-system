package ptithcm.dto;

import java.sql.Date;
import java.util.List;

import ptithcm.model.order.OrderLine;

public class OrderDeliveryDTO {
	private Integer id;
	private Integer orderId;
	private Date deliveryDate;
	private Date deliveryReceived;
	private Integer userId;
	private String customerName;
	private String phoneNumber;
	private List<OrderLine> listOrderDelivery;
	private String customerAddress;
	private Long totolMoney;
	private String userName;

	public OrderDeliveryDTO() {
		super();
	}

	public OrderDeliveryDTO(Integer orderId, String status, Date deliveryDate, Date deliveryReceived, Integer userId,
			List<OrderLine> listOrderDelivery, String customerName, String phoneNumber, String customerAddress, Long totalMoney, String userName) {
		super();
		this.orderId = orderId;
		this.deliveryDate = deliveryDate;
		this.deliveryReceived = deliveryReceived;
		this.userId = userId;
		this.listOrderDelivery = listOrderDelivery;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.customerAddress = customerAddress;
		this.totolMoney = totalMoney;
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getDeliveryReceived() {
		return deliveryReceived;
	}

	public void setDeliveryReceived(Date deliveryReceived) {
		this.deliveryReceived = deliveryReceived;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<OrderLine> getListOrderDelivery() {
		return listOrderDelivery;
	}

	public void setListOrderDelivery(List<OrderLine> listOrderDelivery) {
		this.listOrderDelivery = listOrderDelivery;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getCustomerAddress() {
		return customerAddress;
	}
	
	public void setCustomerAddress(String address) {
		this.customerAddress = address;
	}
	
	public Long getTotalMoney() {
		return totolMoney;
	}
	
	public void setTotalMoney(Long totalMoney) {
		this.totolMoney = totalMoney;
	}

	public Long getTotolMoney() {
		return totolMoney;
	}

	public void setTotolMoney(Long totolMoney) {
		this.totolMoney = totolMoney;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
