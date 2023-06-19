package ptithcm.model.promotion;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import ptithcm.model.product.ProductCategory;

@Entity
@Table(name = "promotion_category")
@IdClass(PromotionCategoryId.class)
public class PromotionCategory {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id", nullable = false, updatable = true)
    private Promotion promotion;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, updatable = true)
    private ProductCategory productCategory;
    
   
	public PromotionCategory() {
		super();
	}

	public PromotionCategory(Promotion promotion, ProductCategory productCategory) {
		super();
		this.promotion = promotion;
		this.productCategory = productCategory;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
