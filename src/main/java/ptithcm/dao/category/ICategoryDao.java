package ptithcm.dao.category;

import java.util.List;

import ptithcm.model.product.ProductCategory;

public interface ICategoryDao {
	public void insert(ProductCategory category);

	public void deleteById(int categoryId);

	public void updateById(ProductCategory category);

	public ProductCategory getCategoryById(int categoryId);

	public List<ProductCategory> getAllCategory();

	public List<ProductCategory> listPaginatedProductCategory(int firstResult, int maxResults, String search);
	
}
