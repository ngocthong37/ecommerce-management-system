package ptithcm.dto;

import org.springframework.stereotype.Repository;

import ptithcm.model.product.Variation;

@Repository
public class ProductVariationDTO {
	private String variationName;
	private int variationId;
	private String categoryName;
	private int categoryId;

	public ProductVariationDTO(Variation variation) {
		this.variationName = variation.getName();
		this.variationId = variation.getId();
		this.categoryName = variation.getCategory().getCategoryName();
		this.categoryId = variation.getCategory().getId();
	}

	public String getVariationName() {
		return variationName;
	}

	public void setVariationName(String variationName) {
		this.variationName = variationName;
	}

	public int getVariationId() {
		return variationId;
	}

	public void setVariationId(int variationId) {
		this.variationId = variationId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
