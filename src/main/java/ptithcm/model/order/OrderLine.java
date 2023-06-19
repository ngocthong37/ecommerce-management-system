package ptithcm.model.order;

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

import ptithcm.model.customer.CustomerReview;
import ptithcm.model.product.ProductItem;
import ptithcm.model.shop.ShopOrder;

@Entity
@Table(name = "Order_Line")
public class OrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "product_item_id")
	private ProductItem productItem;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private ShopOrder shopOrder;			
	@Column(name = "qty")
	private Integer quantity;
	@Column(name = "price")
	private Long price;
	
	@OneToMany(mappedBy = "orderLine", fetch = FetchType.LAZY)
	private Collection<CustomerReview> customerReivew;
	
	public OrderLine() {
		super();
	}

	public OrderLine(Integer id, ProductItem productItem, ShopOrder shopOrder, Integer quantity, Long price) {
		super();
		this.id = id;
		this.productItem = productItem;
		this.shopOrder = shopOrder;
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductItem getProductItem() {
		return productItem;
	}

	public void setProductItem(ProductItem productItem) {
		this.productItem = productItem;
	}

	public ShopOrder getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(ShopOrder shopOrder) {
		this.shopOrder = shopOrder;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
