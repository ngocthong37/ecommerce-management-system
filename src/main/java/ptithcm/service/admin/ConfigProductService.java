package ptithcm.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.admin.ConfigProductDao;
import ptithcm.model.product.ConfigProduct;

@Service
public class ConfigProductService {

	@Autowired
	ConfigProductDao configProductDAO;

	public void addConfigProduct(ConfigProduct configProduct) {
		configProductDAO.addConfigProduct(configProduct);
	}

	public ConfigProduct getConfigProductById(int productItemId, int variationId) {
		return configProductDAO.getConfigProductById(productItemId, variationId);
	}

	public List<ConfigProduct> getAllConfigProducts() {
		return configProductDAO.getAllConfigProducts();
	}

	public void updateConfigProduct(ConfigProduct configProduct) {
		configProductDAO.updateConfigProduct(configProduct);
	}

	public void deleteConfigProduct(ConfigProduct configProduct) {
		configProductDAO.deleteConfigProduct(configProduct);
	}

	public List<ConfigProduct> getConfigProductsByProductItemId(int productItemId) {
		return configProductDAO.getConfigProductsByProductItemId(productItemId);
	}
	public void deleteConfigProductsByProductItemId(int productItemId) {
		configProductDAO.deleteConfigProductsByProductItemId(productItemId);
	}

}
