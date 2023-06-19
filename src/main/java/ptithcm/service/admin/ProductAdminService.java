package ptithcm.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.admin.ProductAdminDao;
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;

@Service
public class ProductAdminService {
	@Autowired
	ProductAdminDao productDao;

	public List<Product> getListPaginated(int firstResult, int maxResults, String search) {
		return productDao.listPaginatedProduct(firstResult, maxResults, search);
	}

	public Product getProductById(int productId) {
		return productDao.getProductById(productId);
	}

	public void addNewProduct(Product product) {
		productDao.createNewProduct(product);
	}

	public void addNewProductItem(Product product, List<ProductItem> listProductItems) {
		productDao.createNewProductItem(product, listProductItems);
	}

	public List<ProductItem> getListProductItemByProductId(int productId) {
		return productDao.getProductItemByProductId(productId);
	}

	public void updateProductItem(ProductItem productItem) {
		productDao.updateProductItem(productItem);
	}

	public void addNewProductItemByProductId(int productId) {
		productDao.addNewProductItemByProductId(productId);
	}

	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}

	public ProductItem getProductItemById(int productItemId) {
		return productDao.getProductItemById(productItemId);
	}
	
	public void deleteProductItemById(int productItemId) {
		productDao.deleteProductItemById(productItemId);
	}
}
