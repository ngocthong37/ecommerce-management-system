package ptithcm.dao.product;

import java.util.List;

import ptithcm.model.customer.CustomerReview;
import ptithcm.model.order.OrderLine;
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;
import ptithcm.model.shoppingCart.ShoppingCartItem;

public interface ProductDao {
	public List<Product> getAllProducts();

	public Product getProductById(int id);

	public int updateQty(int Id, int qty);

	public Integer getOrderID(int id);

	public List<CustomerReview> getAllCommentsById(int id);

	public Double getRatingAverageProduct(int productId);

	public OrderLine getOrderLineById(int id);

	public void deleteProductItem(int id);

	public List<ProductItem> searchProductItem(String name);

	public List<Product> getAllProductByCateId(int categoryId);

	public void addToCart(ShoppingCartItem shoppingCartItem, int cartId, int customerId, int bonus, int quantity);

	public List<ProductItem> listPaginatedProductCategory(int firstResult, int maxResults, String search);

	public OrderLine isBoughtByCustomer(int customerId, int productItemId);

	public List<ProductItem> getProductItemByProductId(int productId);
	
	public ProductItem getProductItemById(int productItemId);

}
