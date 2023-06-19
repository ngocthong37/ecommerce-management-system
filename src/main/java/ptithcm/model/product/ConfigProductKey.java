package ptithcm.model.product;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ConfigProductKey implements Serializable {
	@ManyToOne
	@JoinColumn(name = "product_item_id")
	private ProductItem productItem;

	@ManyToOne
	@JoinColumn(name = "variation_id")
	private Variation variation;

	public ProductItem getProductItem() {
		return productItem;
	}

	public void setProductItem(ProductItem productItem) {
		this.productItem = productItem;
	}

	public Variation getVariation() {
		return variation;
	}

	public void setVariation(Variation variation) {
		this.variation = variation;
	}

}
