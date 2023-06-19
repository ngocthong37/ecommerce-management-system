package ptithcm.dao.product;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.model.customer.CustomerReview;
import ptithcm.model.order.OrderLine;
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;
import ptithcm.model.shop.ShopOrder;
import ptithcm.model.shoppingCart.ShoppingCart;
import ptithcm.model.shoppingCart.ShoppingCartItem;
import ptithcm.service.ShoppingCartService;

@Service
public class ProductDaoImp implements ProductDao {
	@Autowired
	SessionFactory factory;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Override
	public List<Product> getAllProducts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> list = query.list();
		return list;
	}

	@Override
	public Product getProductById(int productId) {
		Session session = factory.openSession();
		String hql = "FROM Product p WHERE p.id = :productId";
		Query query = session.createQuery(hql);
		query.setParameter("productId", productId);
		return (Product) query.uniqueResult();
	}

	@Override
	public int updateQty(int Id, int qty) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE ProductItem s SET s.quantityInStock = s.quantityInStock + :qty WHERE s.id  = :Id";
		Query query = session.createQuery(hql);
		query.setParameter("qty", qty);
		query.setParameter("Id", Id);
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public List<ProductItem> listPaginatedProductCategory(int firstResult, int maxResults, String search) {
		firstResult = firstResult <= 0 ? 0 : firstResult;
		maxResults = maxResults <= 5 ? 5 : maxResults;
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductItem proc WHERE proc.product.name LIKE :search ORDER BY proc.id";
		Query query = session.createQuery(hql);
		query.setParameter("search", "%" + search + "%");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List<ProductItem> list = query.list();
		return list;
	}

	@Override
	public Integer getOrderID(int productId) {
		Session session = factory.getCurrentSession();
		String hql = "Select ol.id FROM ProductItem pi, OrderLine ol where pi.id = :productId and pi.id = ol.productItem.id";
		Query query = session.createQuery(hql);
		query.setParameter("productId", productId);
		return (int) query.uniqueResult();
	}

	@Override
	public List<CustomerReview> getAllCommentsById(int productId) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT cr FROM CustomerReview cr JOIN cr.orderLine ol JOIN ol.productItem pi WHERE pi.id = :productId";
		Query query = session.createQuery(hql);
		query.setParameter("productId", productId);
		List<CustomerReview> comments = query.list();
		return comments;
	}

	@Override
	public Double getRatingAverageProduct(int productId) {
		Session session = factory.getCurrentSession();
		String hql = "Select AVG(cr.ratingValue) From CustomerReview cr JOIN cr.orderLine ol JOIN ol.productItem pi WHERE pi.id = :productId";
		Query query = session.createQuery(hql);
		query.setParameter("productId", productId);
		return (Double) query.uniqueResult();
	}

	@Override
	public OrderLine getOrderLineById(int customerId) {
		Session session = factory.getCurrentSession();
		String hql = "Select OL from OrderLine OL, Customer C";
		Query query = session.createQuery(hql);
		return (OrderLine) query.uniqueResult();
	}

	@Override
	public void deleteProductItem(int productId) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "Delete From ProductItem pi where pi.id = :productId";
			Query query = session.createQuery(hql);
			query.setParameter("productId", productId);
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public List<ProductItem> searchProductItem(String name) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "SELECT pi FROM ProductItem pi WHERE pi.product.name LIKE :name";
			Query query = session.createQuery(hql);
			query.setParameter("name", name + '%');
			List<ProductItem> list = query.list();
			return list;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}

	}

	@Override
	public List<Product> getAllProductByCateId(int categoryId) {
		try {
			Session session = factory.openSession();
			String hql = "Select p FROM Product p, ProductCategory pc where :categoryId = p.productCategory.id and p.productCategory.id = pc.id";
			Query query = session.createQuery(hql);
			query.setParameter("categoryId", categoryId);
			List<Product> list = query.list();
			return list;

		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	@Override
	public void addToCart(ShoppingCartItem shoppingCartItem, int cartId, int customerId, int bonus, int quantity) {
		Session session = factory.getCurrentSession();
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(cartId, customerId);
		System.out.println("ID shopping cart: " + shoppingCart.getId());
		shoppingCartItem.setCart(shoppingCart);
		if (bonus > 0) {
			shoppingCartItem = shoppingCartService.getExistItemCart(shoppingCartItem.getProductItem().getId(),
					customerId);
			shoppingCartItem.setQuantity(bonus + quantity);
			System.out.println("Update");
			session.update(shoppingCartItem);
		} else {
			shoppingCartItem.setQuantity(quantity);
			System.out.println("Save");
			session.save(shoppingCartItem);
		}
	}

	@Override
	public OrderLine isBoughtByCustomer(int customerId, int productItemId) {
		Session session = factory.getCurrentSession();
		String hql = "select SO from ShopOrder SO, OrderLine OL, CustomerAddress CA where SO.id = OL.shopOrder.id and SO.customerAddress.id = CA.id and SO.orderStatus.id = 3 and OL.productItem.id = :productItemId and CA.customer.id = :customerId";
		Query query = session.createQuery(hql);
		query.setParameter("productItemId", productItemId);
		query.setParameter("customerId", customerId);
		List<ShopOrder> shoppingOrderListId = query.list();
		if (shoppingOrderListId.size() > 0) {
			List<OrderLine> listOderLines = (List<OrderLine>) shoppingOrderListId.get(0).getOrderLines();

			return listOderLines.get(0);
		}
		return null;
	}

	@Override
	public List<ProductItem> getProductItemByProductId(int productId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ProductItem procItem WHERE procItem.product.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", productId);
		List<ProductItem> list = query.list();
		return list;
	}

	@Override
	public ProductItem getProductItemById(int productItemId) {
		Session session = factory.openSession();
		String hql = "FROM ProductItem p WHERE p.id = :productItemId";
		Query query = session.createQuery(hql);
		query.setParameter("productItemId", productItemId);
		return (ProductItem) query.uniqueResult();
	}

}
