package ptithcm.model.user;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ptithcm.model.inventory.InventoryReceiving;
import ptithcm.model.order.OrderDelivery;
import ptithcm.model.promotion.Promotion;
import ptithcm.model.updation.UpdatePriceProductItem;
import javax.persistence.CascadeType;

@Entity
@Table(name = "[User]")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "user_permission")
	private UserPermission userPermission;
	@Column(name = "username")
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "status")
	private Boolean status;
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private UserProfile userProfile;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<InventoryReceiving> inventoryReceivings;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<UpdatePriceProductItem> updatePriceProductItems;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<Promotion> promotions;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<OrderDelivery> orderDeliveries;
	
	
	public User() {
		super();
	}

	public User(Integer id, UserPermission userPermission, String username, String email, String password,
			Boolean status) {
		super();
		this.id = id;
		this.userPermission = userPermission;
		this.username = username;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public UserProfile getUserProfile() {
		return userProfile;	
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Collection<InventoryReceiving> getInventoryReceivings() {
		return inventoryReceivings;
	}

	public void setInventoryReceivings(Collection<InventoryReceiving> inventoryReceivings) {
		this.inventoryReceivings = inventoryReceivings;
	}

	public Collection<UpdatePriceProductItem> getUpdatePriceProductItems() {
		return updatePriceProductItems;
	}

	public void setUpdatePriceProductItems(Collection<UpdatePriceProductItem> updatePriceProductItems) {
		this.updatePriceProductItems = updatePriceProductItems;
	}

	public Collection<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(Collection<Promotion> promotions) {
		this.promotions = promotions;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserPermission getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(UserPermission userPermission) {
		this.userPermission = userPermission;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
