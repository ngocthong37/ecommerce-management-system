package ptithcm.dto;

public class CustomerOrderDTO {
	private String id;
	private Long quantity;
	private Long price;
	private String dateOrdered;
	private String productItemName;
	private Integer orderId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getDateOrdered() {
		return dateOrdered;
	}
	public void setDateOrdered(String dateOrdered) {
		this.dateOrdered = dateOrdered;
	}
	public String getProductItemName() {
		return productItemName;
	}
	public void setProductItemName(String productItemName) {
		this.productItemName = productItemName;
	}
	public CustomerOrderDTO() {
		
	}
	public CustomerOrderDTO(String id, Long quantity, Long price, String dateOrdered, String productItemName, int orderId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.dateOrdered = dateOrdered;
		this.productItemName = productItemName;
		this.orderId = orderId;
	}
	
	
	
	
	
}