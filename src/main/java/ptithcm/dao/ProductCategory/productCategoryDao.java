package ptithcm.dao.ProductCategory;

import java.util.List;

import ptithcm.model.product.ProductCategory;

public interface productCategoryDao {
	public List<ProductCategory> getAllProductCategory();
	public ProductCategory getProductCategory(int cateId);
}
