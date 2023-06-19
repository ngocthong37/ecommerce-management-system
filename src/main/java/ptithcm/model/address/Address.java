package ptithcm.model.address;

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

import ptithcm.model.customer.CustomerAddress;

@Entity
@Table(name="Address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "ward")
	private Ward ward;
	@Column(name="detail_address")
	private String detailAddress;
	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
	private Collection<CustomerAddress> customerAddress;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name = "create_at")
	private Date createAt;
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Date getDeleteAt() {
		return deleteAt;
	}

	public void setDeleteAt(Date deleteAt) {
		this.deleteAt = deleteAt;
	}
	@Column(name = "modified_at")
	private Date modifiedAt;
	@Column(name = "delete_at")
	private Date deleteAt;
	public Address() {
		super();
	}
	
	public Address(int id, Ward ward, String detailAddress) {
		super();
		this.id = id;
		this.ward = ward;
		this.detailAddress = detailAddress;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	
	
}
