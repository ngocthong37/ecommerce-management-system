package ptithcm.model.customer;

import java.util.Collection;
import java.util.List;

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

import ptithcm.model.address.Address;
import ptithcm.model.shop.ShopOrder;


@Entity
@Table(name = "Customer_Address")
public class CustomerAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "is_default")
	private Boolean isDefault;
	@ManyToOne()
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@ManyToOne()
	@JoinColumn(name = "address_id")
	private Address address;
	@OneToMany(mappedBy = "customerAddress", fetch = FetchType.LAZY)
	private Collection<ShopOrder> shopOrders;
	
	public CustomerAddress() {
		super();
	}

	public CustomerAddress(Integer id, Boolean isDefault, Customer customer, Address address) {
		super();
		this.id = id;
		this.isDefault = isDefault;
		this.customer = customer;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
