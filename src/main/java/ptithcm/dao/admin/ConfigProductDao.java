package ptithcm.dao.admin;

import java.util.List;

import ptithcm.model.product.ConfigProduct;

public interface ConfigProductDao {
	void addConfigProduct(ConfigProduct configProduct);

	ConfigProduct getConfigProductById(int productItemId, int variationId);

	List<ConfigProduct> getAllConfigProducts();

	void updateConfigProduct(ConfigProduct configProduct);

	void deleteConfigProduct(ConfigProduct configProduct);

	List<ConfigProduct> getConfigProductsByProductItemId(int productItemId);

	void deleteConfigProductsByProductItemId(int productItemId);
}
