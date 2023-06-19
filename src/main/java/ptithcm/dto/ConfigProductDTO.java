package ptithcm.dto;

import java.util.ArrayList;
import java.util.List;

import ptithcm.model.product.ConfigProduct;
import ptithcm.model.product.Variation;

public class ConfigProductDTO {
	private int productItemId;
	private List<Variation> listVariation;
	private List<String> variationValueList;

	public ConfigProductDTO(List<ConfigProduct> listConfigProducts, int productItemId) {
		this.productItemId = productItemId;
		this.listVariation = new ArrayList<>();
		this.variationValueList = new ArrayList<>();

		for (int i = 0; i < listConfigProducts.size(); i++) {
			if (listConfigProducts.get(i).getId().getProductItem().getId() == productItemId) {
				this.listVariation.add(listConfigProducts.get(i).getId().getVariation());
				this.variationValueList.add(listConfigProducts.get(i).getValue());
			}
		}
	}

	public int getProductItemId() {
		return productItemId;
	}

	public void setProductItemId(int productItemId) {
		this.productItemId = productItemId;
	}

	public List<Variation> getListVariation() {
		return listVariation;
	}

	public void setListVariation(List<Variation> listVariation) {
		this.listVariation = listVariation;
	}

	public List<String> getVariationValueList() {
		return variationValueList;
	}

	public void setVariationValueList(List<String> variationValueList) {
		this.variationValueList = variationValueList;
	}
}
