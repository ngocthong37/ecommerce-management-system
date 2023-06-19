package ptithcm.dao.variation;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.model.product.Variation;

@Repository
public class VariationDaoImpl implements VariationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void deleteById(int variationId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete Variation v where v.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", variationId);
		query.executeUpdate();
	}

	@Override
	public List<Variation> getAllVariations() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Variation v ORDER BY v.id";
		Query query = session.createQuery(hql);
		List<Variation> list = query.list();
		return list;
	}

	@Override
	public Variation getVariationById(int variationId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Variation v where v.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", variationId);
		return (Variation) query.uniqueResult();
	}

	@Override
	public void insert(Variation variation) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			session.save(variation);

			session.getTransaction().commit();

			System.out.print("insert variation success!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

	}

	@Override
	public List<Variation> listPaginatedProductVariation(int firstResult, int maxResults, String search) {
		firstResult = firstResult <= 0 ? 0 : firstResult;
		maxResults = maxResults <= 5 ? 5 : maxResults;
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Variation v WHERE v.name LIKE :search ORDER BY v.id";
		Query query = session.createQuery(hql);
		query.setParameter("search", "%" + search + "%");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List<Variation> list = query.list();
		return list;
	}

	@Override
	public void updateById(Variation variation) {
		Session session = sessionFactory.getCurrentSession();
		session.update(variation);

	}

	@Override
	public List<Variation> getListVariationByCategoryId(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Variation v WHERE v.productCategory.id = :categoryId";
		Query query = session.createQuery(hql);
		query.setParameter("categoryId", categoryId);
		List<Variation> list = query.list();
		return list;
	}
}
