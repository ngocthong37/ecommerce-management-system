package ptithcm.model.shoppingCart;

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
import ptithcm.model.customer.Customer;
import ptithcm.model.product.ProductItem;

@Entity
@Table(name = "Shopping_Cart")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
	private Collection<ShoppingCartItem> shoppingCartItems;
	
	public ShoppingCart() {
		super();
	}

	public ShoppingCart(Integer id,  Customer customer, Collection<ShoppingCartItem> shoppingCartItems) {
		super();
		this.id = id;
		this.shoppingCartItems = shoppingCartItems;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(Collection<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
