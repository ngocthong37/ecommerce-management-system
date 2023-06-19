package ptithcm.dao.admin;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.model.product.ConfigProduct;

@Repository
public class ConfigProductDaoImpl implements ConfigProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addConfigProduct(ConfigProduct configProduct) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(configProduct);
	}

	@Override
	public ConfigProduct getConfigProductById(int productItemId, int variationId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ConfigProduct cp WHERE cp.id.productItem.id = :productItemId AND cp.id.variation.id = :variationId";
		Query query = session.createQuery(hql);
		query.setParameter("productItemId", productItemId);
		query.setParameter("variationId", variationId);
		return (ConfigProduct) query.uniqueResult();
	}

	@Override
	public List<ConfigProduct> getAllConfigProducts() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ConfigProduct";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public void updateConfigProduct(ConfigProduct configProduct) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(configProduct);
	}

	@Override
	public void deleteConfigProduct(ConfigProduct configProduct) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(configProduct);
	}

	@Override
	public List<ConfigProduct> getConfigProductsByProductItemId(int productItemId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ConfigProduct cp WHERE cp.id.productItem.id = :productItemId";
		Query query = session.createQuery(hql);
		query.setParameter("productItemId", productItemId);
		return query.list();
	}

	@Override
	public void deleteConfigProductsByProductItemId(int productItemId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "DELETE FROM ConfigProduct cp WHERE cp.id.productItem.id = :productItemId";
		Query query = session.createQuery(hql);
		query.setParameter("productItemId", productItemId);
		query.executeUpdate();
	}
}
