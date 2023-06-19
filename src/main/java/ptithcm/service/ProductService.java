package ptithcm.service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.product.ProductDao;
import ptithcm.model.customer.CustomerReview;
import ptithcm.model.order.OrderLine;
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;
import ptithcm.model.promotion.Promotion;
import ptithcm.model.promotion.PromotionCategory;
import ptithcm.model.shoppingCart.ShoppingCartItem;
import ptithcm.service.admin.PromotionService;

@Service
public class ProductService {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private PromotionService promotionService;

	public List<Product> getListProducts() {
		return productDao.getAllProducts();
	}

	public ProductItem getProductItemById(int productItemId) {
		return productDao.getProductItemById(productItemId);
	}

	public Product getProductById(int id) {
		Product product = productDao.getProductById(id);
		if (product != null) {
			return product;
		}
		return null;
	}

	public int updateQty(int produtItemId, int qty) {
		int update = productDao.updateQty(produtItemId, qty);
		return update;
	}

	public List<CustomerReview> getAllCommentsById(int id) {
		List<CustomerReview> comments = productDao.getAllCommentsById(id);
		if (!comments.isEmpty()) {
			return comments;
		}
		return null;
	}

	public int getOrderId(int id) {
		int value = productDao.getOrderID(id);
		return value;
	}

	public Double getRatingAverageProduct(int id) {
		Double value = productDao.getRatingAverageProduct(id);
		if (value != null) {
			System.out.println("Value: " + value);
			return value;
		}
		return null;
	}

	public void deleteProductItem(int id) {
		productDao.deleteProductItem(id);
	}

	public List<ProductItem> searchProductItem(String name) {
		List<ProductItem> list = productDao.searchProductItem(name);
		if (list != null) {
			return list;
		}
		return null;
	}

	public List<Product> getAllProductByCateId(int categoryId) {
		List<Product> list = productDao.getAllProductByCateId(categoryId);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	public boolean isExistInCart(int productId) {
		if (getListProducts() == null) {
			return false;
		}
//		for (ProductItem productItem: getListProducts()) {
//			if (productItem.getId() == productId) {
//				return true;
//			}
//		}
		return false;
	}

	public void addToCart(ShoppingCartItem shoppingCartItem, int cartId, int customerId, int bonus, int quantity) {
		productDao.addToCart(shoppingCartItem, cartId, customerId, bonus, quantity);
	}

	public List<ProductItem> getListPaginatedCategories(int firstResult, int maxResults, String search) {
		return productDao.listPaginatedProductCategory(firstResult, maxResults, search);
	}

	public OrderLine isBoughtThisProduct(int customerId, int productItemId) {
		return productDao.isBoughtByCustomer(customerId, productItemId);
	}

	public List<ProductItem> getProductItemByProductId(int productId) {
		return productDao.getProductItemByProductId(productId);
	}

	public boolean isReviewed(int customerId, int productItemId, List<CustomerReview> listCustomerReiview) {
		for (CustomerReview customerReview : listCustomerReiview) {
			if (customerReview.getCustomer().getId() == customerId
					&& customerReview.getOrderLine().getProductItem().getId() == productItemId) {
				return true;
			}
		}
		return false;
	}

	public void checkPromotion() {
		List<ProductItem> listProductItemAdd = new ArrayList<>();
		Date curentDate = new Date();
		List<Promotion> promotionList = promotionService.getAllPromotions();
		if(promotionList == null) {
			return;
		}
		for (Promotion promotion : promotionList) {
			int compare = curentDate.compareTo(promotion.getStartDate());
			System.out.println("compare ngay bat dau: " + compare);
			if (compare >= 0) {
				PromotionCategory promotionCategory = promotionService.getPromotionCategoryById(promotion.getId());
				int categoryId = promotionCategory.getProductCategory().getId();
				List<Product> listProductPromotion = getAllProductByCateId(categoryId);
				for (Product product : listProductPromotion) {
					List<ProductItem> listProductItems = (List<ProductItem>) product.getProductItems();
					listProductItemAdd.addAll(listProductItems);
				}
				Session session = sessionFactory.openSession();
				Transaction tx = null;
				try {
					tx = session.beginTransaction();
					for (ProductItem productItem : listProductItemAdd) {
						productItem.setStatus("ON_SALE");
						session.merge(productItem);
					}
					tx.commit();
					System.out.println("Thanh cong");
				} catch (Exception e) {
					if (tx != null) {
						tx.rollback();
						System.out.println("That bai: " + e.toString());
					}
				} finally {
					if (session != null) {
						session.close();
					}
				}
			}
		}
		for (Promotion promotion : promotionList) {
			int compare = curentDate.compareTo(promotion.getEndDate());
			System.out.println("compare ngay ket thuc: " + compare);
			if (compare > 0) {
				PromotionCategory promotionCategory = promotionService.getPromotionCategoryById(promotion.getId());
				int categoryId = promotionCategory.getProductCategory().getId();
				List<Product> listProductPromotion = getAllProductByCateId(categoryId);
				for (Product product : listProductPromotion) {
					List<ProductItem> listProductItems = (List<ProductItem>) product.getProductItems();
					listProductItemAdd.addAll(listProductItems);
				}
				Session session = sessionFactory.openSession();
				Transaction tx = null;
				try {
					tx = session.beginTransaction();
					for (ProductItem productItem : listProductItemAdd) {
						productItem.setStatus("");
						session.merge(productItem);
					}
					tx.commit();
					System.out.println("Thanh cong");
				} catch (Exception e) {
					if (tx != null) {
						tx.rollback();
						System.out.println("That bai: " + e.toString());
					}
				} finally {
					if (session != null) {
						session.close();
					}
				}
			}
		}
	}

}
