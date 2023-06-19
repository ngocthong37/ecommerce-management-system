package ptithcm.dao.admin;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.model.order.OrderLine;
import ptithcm.model.shop.ShopOrder;

@Repository
public class AnalyticsDaoImpl implements AnalyticsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ShopOrder> getShopOrderByYear(int year) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ShopOrder so WHERE YEAR(so.orderDate) = :year";
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		return query.list();
	}

	@Override
	public List<ShopOrder> getShopOrderByMonthInYear(int month, int year) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ShopOrder so WHERE MONTH(so.orderDate) = :month AND YEAR(so.orderDate) = :year";
		Query query = session.createQuery(hql);
		query.setParameter("month", month);
		query.setParameter("year", year);
		return query.list();
	}

	@Override
	public List<ShopOrder> getShopOrderByQuarter(int year, int quarter) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM ShopOrder so WHERE YEAR(so.orderDate) = :year AND QUARTER(so.orderDate) = :quarter";
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		query.setParameter("quarter", quarter);
		return query.list();
	}

	@Override
	public List<OrderLine> getOrderLineByYear(int year) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM OrderLine ol WHERE YEAR(ol.shopOrder.orderDate) = :year";
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		return query.list();
	}

	@Override
	public List<OrderLine> getOrderLineByYearAndMonth(int year, int month) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM OrderLine ol WHERE YEAR(ol.shopOrder.orderDate) = :year AND MONTH(ol.shopOrder.orderDate) = :month";
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		query.setParameter("month", month);
		return query.list();
	}
	

}
