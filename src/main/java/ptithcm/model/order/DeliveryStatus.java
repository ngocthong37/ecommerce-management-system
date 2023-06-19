package ptithcm.model.order;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="delivery_status")

public class DeliveryStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "status")
	private String status;
	
	@OneToMany(mappedBy = "deliveryStatus", fetch = FetchType.LAZY)
	private Collection<OrderDelivery> orderDeliveries;

	public DeliveryStatus() {
		super();
	}

	public DeliveryStatus(String status, Collection<OrderDelivery> orderStatus) {
		super();
		this.status = status;
		this.orderDeliveries = orderStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<OrderDelivery> getOrderDeliveries() {
		return orderDeliveries;
	}

	public void setOrderDeliveries(Collection<OrderDelivery> orderDeliveries) {
		this.orderDeliveries = orderDeliveries;
	}

	
	
	
}