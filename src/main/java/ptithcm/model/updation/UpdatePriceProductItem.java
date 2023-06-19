package ptithcm.model.updation;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ptithcm.model.product.ProductItem;
import ptithcm.model.user.User;


@Entity
@Table(name = "Update_price_product_item")
public class UpdatePriceProductItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "product_item_id")
	private ProductItem productItem;
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "old_price")
	private Integer oldPrice;
	@Column(name = "new_price")
	private Integer newPrice;
	@Column(name = "update_date")
	private LocalDate updateDate;
	
}
