package ptithcm.model.ship;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ptithcm.model.shop.ShopOrder;

@Entity
@Table(name = "Shipping_Method")
public class ShippingMethod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Integer price;

	@OneToMany(mappedBy = "shippingMethod", fetch = FetchType.LAZY)
	private Collection<ShopOrder> shopOrders;
	
	
	public ShippingMethod() {
		super();
	}


	public ShippingMethod(Integer id, String name, Integer price, Collection<ShopOrder> shopOrders) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.shopOrders = shopOrders;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public Collection<ShopOrder> getShopOrders() {
		return shopOrders;
	}


	public void setShopOrders(Collection<ShopOrder> shopOrders) {
		this.shopOrders = shopOrders;
	}

	

}
