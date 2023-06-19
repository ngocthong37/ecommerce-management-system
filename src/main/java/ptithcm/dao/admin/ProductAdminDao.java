package ptithcm.dao.admin;

import java.util.List;

import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;

public interface ProductAdminDao {
	public void createNewProductItem(Product product, List<ProductItem> listProductItems);

	public Product getProductById(int productId);
	
	public String updateProductItem(ProductItem productItem);
	
	public void addNewProductItemByProductId(int productId);
	
	public void updateProduct(Product product);
	
	public ProductItem getProductItemById(int productItemId);

	public List<ProductItem> getProductItemByProductId(int productId);

	public void createNewProduct(Product product);

	public void deleteProduct(Product product);

	public List<Product> listPaginatedProduct(int firstResult, int maxResults, String search);
	
	public void deleteProductItemById(int productItemId);
}
