package ptithcm.model.promotion;

import java.sql.Date;
import java.time.LocalDate;
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

import ptithcm.model.user.User;

@Entity
@Table(name = "Promotion")
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "discount_rate")
	private int discountRate;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@Column(name = "create_at")
	private Date createAt;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "promotion", fetch = FetchType.EAGER)
	private Collection<PromotionCategory> promotionCategory;

	public Promotion() {
		super();
	}

	public Promotion(String name, String description, int discountRate, Date startDate, Date endDate,
			Date createAt, User user) {
		super();
		this.name = name;
		this.description = description;
		this.discountRate = discountRate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createAt = createAt;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<PromotionCategory> getPromotionCategory() {
		return promotionCategory;
	}

	public void setPromotionCategory(Collection<PromotionCategory> promotionCategory) {
		this.promotionCategory = promotionCategory;
	}

}
