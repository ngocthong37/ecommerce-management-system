package ptithcm.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Variation")
public class Variation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategory productCategory;
	@Column(name = "name")
	private String name;

	public Variation() {
		super();
	}

	public Variation(Integer id, ProductCategory category, String name) {
		super();
		this.id = id;
		this.productCategory = category;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductCategory getCategory() {
		return productCategory;
	}

	public void setCategory(ProductCategory category) {
		this.productCategory = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
