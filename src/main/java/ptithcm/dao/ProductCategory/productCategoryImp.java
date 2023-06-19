package ptithcm.dao.ProductCategory;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.model.product.ProductCategory;


@Service
public class productCategoryImp implements productCategoryDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<ProductCategory> getAllProductCategory() {
		Session session = sessionFactory.openSession();
		String hql = "From ProductCategory";
		Query query = session.createQuery(hql);
		List<ProductCategory> list = query.list();
		return list;
	}
	
	public ProductCategory getProductCategory(int cateId) {
		Session session = sessionFactory.openSession();
		String hql = "FROM ProductCategory pc where pc.id = :cateId";
		Query query = session.createQuery(hql);
		query.setParameter("cateId", cateId);
		return (ProductCategory) query.uniqueResult();
	}
	
}
