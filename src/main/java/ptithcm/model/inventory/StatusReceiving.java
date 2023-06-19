package ptithcm.model.inventory;

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
@Table(name = "status_receiving")
public class StatusReceiving {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "statusReceiving", fetch = FetchType.LAZY)
	private Collection<InventoryReceiving> inventoryReceivings;

	public StatusReceiving() {
		super();
	}

	public StatusReceiving(Integer id, String status, Collection<InventoryReceiving> inventoryReceivings) {
		super();
		this.id = id;
		this.status = status;
		this.inventoryReceivings = inventoryReceivings;
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

	public Collection<InventoryReceiving> getInventoryReceivings() {
		return inventoryReceivings;
	}

	public void setInventoryReceivings(Collection<InventoryReceiving> inventoryReceivings) {
		this.inventoryReceivings = inventoryReceivings;
	}

}
