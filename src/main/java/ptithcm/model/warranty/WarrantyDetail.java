package ptithcm.model.warranty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ptithcm.model.product.ProductItem;
import ptithcm.model.shop.ShopOrder;

@Entity
@Table(name = "Warranty_Detail")
public class WarrantyDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "product_item_id")
	private ProductItem productItem;
	@ManyToOne
	@JoinColumn(name = "warranty_id")
	private Warranty warranty;
	@ManyToOne
	@JoinColumn(name = "shop_order_id")
	private ShopOrder shopOrder;

	public WarrantyDetail() {
		super();
	}

	public WarrantyDetail(Integer id, ProductItem productItem, Warranty warranty, ShopOrder shopOrder) {
		super();
		this.id = id;
		this.productItem = productItem;
		this.warranty = warranty;
		this.shopOrder = shopOrder;
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

	public Warranty getWarranty() {
		return warranty;
	}

	public void setWarranty(Warranty warranty) {
		this.warranty = warranty;
	}

	public ShopOrder getShopOrder() {
		return shopOrder;
	}

	public void setShopOrder(ShopOrder shopOrder) {
		this.shopOrder = shopOrder;
	}

}
