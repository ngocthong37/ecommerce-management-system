package ptithcm.model.shoppingCart;

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
@Table(name = "Shopping_Cart_Item")
public class ShoppingCartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private ShoppingCart cart;
	@ManyToOne()
	@JoinColumn(name = "product_item_id")
	private ProductItem productItem;
	@Column(name = "qty")
	private Integer quantity;

	public ShoppingCartItem() {
		super();
	}

	public ShoppingCartItem(Integer id, ShoppingCart cart, ProductItem productItem, Integer quantity) {
		super();
		this.id = id;
		this.cart = cart;
		this.productItem = productItem;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public ProductItem getProductItem() {
		return productItem;
	}

	public void setProductItem(ProductItem productItem) {
		this.productItem = productItem;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
