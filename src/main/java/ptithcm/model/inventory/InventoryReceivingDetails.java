package ptithcm.model.inventory;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import ptithcm.model.product.ProductItem;


@Entity
@Table(name="Inventory_Receiving_Details")
public class InventoryReceivingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "inventory_receiving_id")
	private InventoryReceiving inventoryReceiving;
	@ManyToOne()
	@JoinColumn(name="product_item_id")
	private ProductItem productItem;
	@Column(name = "price")
	private int price;
	@Column(name = "qty")
	private int qty;
	
	public InventoryReceivingDetails() {
		super();
	}
	
	public InventoryReceivingDetails(Integer id, InventoryReceiving inventoryReceiving,ProductItem productItem,Integer price,Integer qty) {
		super();
		this.id = id;
		this.inventoryReceiving = inventoryReceiving;
		this.productItem = productItem;
		this.price =price;
		this.qty=qty;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public InventoryReceiving getInventoryReceiving() {
		return inventoryReceiving;
	}

	public void setInventoryReceiving(InventoryReceiving inventoryReceiving) {
		this.inventoryReceiving = inventoryReceiving;
	}

	public ProductItem getProductItem() {
		return productItem;
	}

	public void setProductItem(ProductItem productItem) {
		this.productItem = productItem;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
}