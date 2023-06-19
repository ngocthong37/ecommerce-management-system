package ptithcm.model.warranty;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ptithcm.model.user.User;

@Entity
@Table(name = "Warranty")
public class Warranty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne()
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne()
	@JoinColumn(name="status_id")
	private WarrantyStatus warrantyStatus;
	@Column(name="create_at")
	private LocalDate createAt;
	public Warranty() {
		super();
	}
	public Warranty(Integer id, User user, WarrantyStatus warrantyStatus, LocalDate createAt) {
		super();
		this.id = id;
		this.user = user;
		this.warrantyStatus = warrantyStatus;
		this.createAt = createAt;
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
	public WarrantyStatus getWarrantyStatus() {
		return warrantyStatus;
	}
	public void setWarrantyStatus(WarrantyStatus warrantyStatus) {
		this.warrantyStatus = warrantyStatus;
	}
	public LocalDate getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}
	
}
