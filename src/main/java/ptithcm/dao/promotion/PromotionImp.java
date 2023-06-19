package ptithcm.dao.promotion;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.EntityKey;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import ptithcm.model.promotion.Promotion;
import ptithcm.model.promotion.PromotionCategory;

@Service
public class PromotionImp implements PromotionDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public long getPriceDiscount(int productId, long oldPrice) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT PM.discountRate FROM ProductItem PI JOIN PI.product P JOIN P.productCategory PC JOIN PC.promotionCategories PMC JOIN PMC.promotion PM WHERE PI.id = :productId";
		Query query = session.createQuery(hql);
		query.setParameter("productId", productId);
		int percentDiscount = (int) query.uniqueResult();
		long newPrice = (oldPrice * (100 - percentDiscount)) / 100;
		return newPrice;
	};

	@Override
	public List<Promotion> getListPromotion() {
		Session session = sessionFactory.openSession();
		try {
			String hql = "FROM Promotion P where P.endDate > current_timestamp";
			Query query = session.createQuery(hql);
			List<Promotion> list = query.list();
			return list;
		} catch (Exception e) {
			System.out.println("error: " + e);
			return null;
		}
	}

	@Override
	public Promotion getPromotionById(int promotionId) {
		Session session = sessionFactory.openSession();
		String hql = "FROM Promotion P where P.id = :promotionId";
		Query query = session.createQuery(hql);
		query.setParameter("promotionId", promotionId);
		return (Promotion) query.uniqueResult();
	}

	@Override
	public Integer getProductCategoryId(int promotionId) {
		Session session = sessionFactory.openSession();
		String hql = "SELECT PMC.productCategory.id FROM PromotionCategory PMC WHERE promotion_id = :promotionId";
		Query query = session.createQuery(hql);
		query.setParameter("promotionId", promotionId);
		return (Integer) query.uniqueResult();
	}

	public void updatePromotionById(Promotion promotion) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(promotion);
			System.out.println("Update thanh cong");
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				System.out.println("That bai: " + e.toString());
			}
		} finally {
			if (session != null) {
				System.out.println("Close 1");
				session.close();
			}
		}
	}

	@Override
	public PromotionCategory getPromotionCategoryById(int promotionId) {
		Session session = sessionFactory.openSession();
		String hql = "From PromotionCategory P where P.promotion.id = :promotionId";
		Query query = session.createQuery(hql);
		query.setParameter("promotionId", promotionId);
		return (PromotionCategory) query.uniqueResult();
	}
	
	@Override
	public PromotionCategory getPromotionCategoryByCateId(int promotionId, int cateId) {
		Session session = sessionFactory.openSession();
		String hql = "From PromotionCategory P where P.promotion.id = :promotionId and P.productCategory.id = :cateId";
		Query query = session.createQuery(hql);
		query.setParameter("promotionId", promotionId);
		query.setParameter("cateId", cateId);
		System.out.println("Lay thanh cong");
		return (PromotionCategory) query.uniqueResult();
	}

	

	@Override
	public void updatePromotionCategory(PromotionCategory promotionCategory) {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			session.merge(promotionCategory);
			tx.commit();
			System.out.println("Update promotion category thanh cong");
		} catch (Exception e) {
			System.out.println("Update promotion category that bai: " + e.toString());
			tx.rollback();
		} finally {
			if (session != null) {
				System.out.println("Close 2");
				session.close();
			}
		}
	}
	
	@Override
	public void deleteOldPromotionCategory(PromotionCategory promotionCategory) {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		try {
			tx = session.beginTransaction();
			Statistics statistics = sessionFactory.getStatistics();
			int count = (int) statistics.getSessionOpenCount();
			System.out.println("session open: " + count);
			session.delete(promotionCategory);
			tx.commit();
			System.out.println("delete promotion category thanh cong");
		} catch (Exception e) {
			System.out.println("delete promotion category that bai: " + e.toString());
			tx.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
}
