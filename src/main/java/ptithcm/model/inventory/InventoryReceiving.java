package ptithcm.model.inventory;

import java.sql.Date;
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

import ptithcm.model.agency.Agency;
import ptithcm.model.user.User;

@Entity
@Table(name = "Inventory_receiving")
public class InventoryReceiving {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name="received_date")
	private Date date;
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne()
	@JoinColumn(name = "agency_id")
	private Agency agency;
	@ManyToOne()
	@JoinColumn(name = "status_id")
	private StatusReceiving statusReceiving;
	@OneToMany(mappedBy = "inventoryReceiving", fetch = FetchType.LAZY)
	private Collection<InventoryReceivingDetails> inventoryReceivingDetails ;
	
	public Collection<InventoryReceivingDetails> getInventoryReceivingDetails() {
		return inventoryReceivingDetails;
	}

	public void setInventoryReceivingDetails(Collection<InventoryReceivingDetails> inventoryReceivingDetails) {
		this.inventoryReceivingDetails = inventoryReceivingDetails;
	}

	public InventoryReceiving() {
		super();
	}

	public InventoryReceiving(Integer id, Date date, User user, Agency agency, StatusReceiving statusReceiving) {
		super();
		this.id = id;
		this.date = date;
		this.user = user;
		this.agency = agency;
		this.statusReceiving = statusReceiving;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public StatusReceiving getStatusReceiving() {
		return statusReceiving;
	}

	public void setStatusReceiving(StatusReceiving statusReceiving) {
		this.statusReceiving = statusReceiving;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
