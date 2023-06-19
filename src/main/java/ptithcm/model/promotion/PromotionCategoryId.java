package ptithcm.model.promotion;

import java.io.Serializable;

import ptithcm.model.product.ProductCategory;

public class PromotionCategoryId implements Serializable {

	private static final long serialVersionUID = 1L;
	private Promotion promotion;
	private ProductCategory productCategory;

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
