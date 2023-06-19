package ptithcm.model.product;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ptithcm.model.promotion.PromotionCategory;

@Entity
@Table(name = "Product_Category")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "parent_category_id")
	private Integer parentCategoryId;

	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
	private Collection<Product> products;

	@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
	private Collection<PromotionCategory> promotionCategories;

	@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
	private Collection<Variation> variations;

	public ProductCategory() {
		super();
	}

	public ProductCategory(Integer id, Integer parentCategoryId, String categoryName, Collection<Product> products,
			Collection<PromotionCategory> promotionCategories, Collection<Variation> variations) {
		super();
		this.id = id;
		this.parentCategoryId = parentCategoryId;
		this.categoryName = categoryName;
		this.products = products;
		this.promotionCategories = promotionCategories;
		this.variations = variations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public Collection<PromotionCategory> getPromotionCategories() {
		return promotionCategories;
	}

	public void setPromotionCategories(Collection<PromotionCategory> promotionCategories) {
		this.promotionCategories = promotionCategories;
	}

	public Collection<Variation> getVariations() {
		return variations;
	}

	public void setVariations(Collection<Variation> variations) {
		this.variations = variations;
	}

}
