package ptithcm.dao.category;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.model.product.ProductCategory;

@Repository
public class CategoryDaoImpl implements ICategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void deleteById(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete ProductCategory proc where proc.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", categoryId);
		query.executeUpdate();
	}

	@Override
	public List<ProductCategory> listPaginatedProductCategory(int firstResult, int maxResults, String search) {
		firstResult = firstResult <= 0 ? 0 : firstResult;
		maxResults = maxResults <= 5 ? 5 : maxResults;
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ProductCategory proc WHERE proc.categoryName LIKE :search ORDER BY proc.id";
		Query query = session.createQuery(hql);
		query.setParameter("search", "%" + search + "%");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List<ProductCategory> list = query.list();
		return list;
	}

	@Override
	public List<ProductCategory> getAllCategory() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ProductCategory proc ORDER BY proc.id";
		Query query = session.createQuery(hql);
		List<ProductCategory> list = query.list();
		return list;
	}

	@Override
	public ProductCategory getCategoryById(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ProductCategory proc where proc.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", categoryId);
		return (ProductCategory) query.uniqueResult();
	}

	@Override
	public void insert(ProductCategory category) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			session.save(category);

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
	public void updateById(ProductCategory category) {
		Session session = sessionFactory.getCurrentSession();
		session.update(category);
	}
}
