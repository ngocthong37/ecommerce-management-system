package ptithcm.model.user;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ptithcm.model.address.Ward;

@Entity
@Table(name="User_Profile")
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	@Column(name="name")
	private String name;
	@ManyToOne
	@JoinColumn(name="address")
	private Ward ward;
	@Column(name="phoneNumber")
	private String phoneNumber;
	@Column(name="create_at")
	private Date createAt;
	@Column(name="modified_at")
	private Date modifiedAt;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Ward getWard() {
		return ward;
	}
	public void setWard(Ward ward) {
		this.ward = ward;
	}
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
	public UserProfile(Integer id, User user, String name, Ward ward, String phoneNumber, Date createAt,
			Date modifiedAt) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.ward = ward;
		this.phoneNumber = phoneNumber;
		this.createAt = createAt;
		this.modifiedAt = modifiedAt;
	}
	public UserProfile() {
		super();
	}
	
	
	
}