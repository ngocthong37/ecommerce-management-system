package ptithcm.dao.admin;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;

@Repository
public class ProductAdminDaoImpl implements ProductAdminDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createNewProductItem(Product product, List<ProductItem> listProductItems) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			System.out.print(product.getId());

			for (ProductItem item : listProductItems) {
				item.setProduct(product);
				session.save(item);
			}

			session.getTransaction().commit();

			System.out.print("insert category success!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public void createNewProduct(Product product) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			session.save(product);

			System.out.print(product.getId());

			session.getTransaction().commit();

			System.out.print("insert category success!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public List<Product> listPaginatedProduct(int firstResult, int maxResults, String search) {
		firstResult = firstResult <= 0 ? 0 : firstResult;
		maxResults = maxResults <= 5 ? 5 : maxResults;
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Product proc WHERE proc.name LIKE :search ORDER BY proc.id";
		Query query = session.createQuery(hql);
		query.setParameter("search", "%" + search + "%");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List<Product> list = query.list();
		return list;
	}

	@Override
	public Product getProductById(int productId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Product proc where proc.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", productId);
		return (Product) query.uniqueResult();
	}

	@Override
	public List<ProductItem> getProductItemByProductId(int productId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ProductItem procItem WHERE procItem.product.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", productId);
		List<ProductItem> list = query.list();
		return list;
	}

	@Override
	public void deleteProduct(Product product) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			// Delete associated product items
			List<ProductItem> productItems = getProductItemByProductId(product.getId());
			for (ProductItem item : productItems) {
				session.delete(item);
			}

			// Delete the product
			session.delete(product);

			session.getTransaction().commit();

			System.out.println("Product deletion successful!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public void addNewProductItemByProductId(int productId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			Product product = getProductById(productId);
			if (product == null) {
				System.out.println("Product not found!");
				return;
			}

			ProductItem newItem = new ProductItem();
			newItem.setProduct(product);
			// Set other properties for the new product item

			session.save(newItem);

			session.getTransaction().commit();

			System.out.println("New product item added successfully!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public void updateProduct(Product product) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			session.update(product);

			session.getTransaction().commit();

			System.out.println("Product update successful!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public String updateProductItem(ProductItem productItem) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			session.update(productItem);

			session.getTransaction().commit();

			System.out.println("Product items update successful!");

			return "Product items update successful!";
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return "Failed to update product items";
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public void deleteProductItemById(int productItemId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "DELETE FROM ProductItem WHERE id = :itemId";
		Query query = session.createQuery(hql);
		query.setParameter("itemId", productItemId);
		int deletedCount = query.executeUpdate();
		if (deletedCount > 0) {
			System.out.println("Product item deletion successful!");
		} else {
			System.out.println("Product item not found!");
		}

	}

	@Override
	public ProductItem getProductItemById(int productItemId) {
		Session session = sessionFactory.openSession();
		String hql = "FROM ProductItem p WHERE p.id = :productId";
		Query query = session.createQuery(hql);
		query.setParameter("productId", productItemId);
		return (ProductItem) query.uniqueResult();
	}
}
