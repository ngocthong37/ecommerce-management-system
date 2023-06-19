package ptithcm.dao.ManageOrder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.model.order.OrderDelivery;
import ptithcm.model.shop.ShopOrder;


@Service
public class MangeOrderImp implements ManageOrderDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<ShopOrder> getListShopOrderByStatus() {
		Session session = sessionFactory.openSession();
		String hql = "select distinct SO from OrderLine OL, ShopOrder SO where OL.shopOrder.id = SO.id and SO.orderStatus.id = 1";
		Query query = session.createQuery(hql);
		List<ShopOrder> shopOrderList = query.list();
		return shopOrderList;
	}
	
	@Override
	public Long getQuantityOfOrder(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT SUM(OL.quantity) AS total_quantity FROM OrderLine OL, ShopOrder SO where OL.shopOrder.id = SO.id and SO.id = :orderId group by OL.shopOrder.id";
		Query query = session.createQuery(hql);
		query.setParameter("orderId", orderId);
		return (Long) query.uniqueResult();
	}
	
	
	@Override
	public ShopOrder getShopOrderById(int orderId) {
		Session session = sessionFactory.openSession();
		String hql = "select SO from ShopOrder SO where SO.id = :orderId";
		Query query = session.createQuery(hql);
		query.setParameter("orderId", orderId);
		return (ShopOrder) query.uniqueResult();
	}
	
	@Override
	public List<OrderDelivery> getListOfOrderShippingByShipper(int userId) {
	   Session session = sessionFactory.openSession();
	    String hql = "SELECT OD FROM OrderDelivery OD, ShopOrder SO WHERE OD.shopOrder.id = SO.id and SO.orderStatus.id = 2 and OD.deliveryStatus = 1 and OD.user.id = :userId";
	    Query query = session.createQuery(hql);
	    query.setParameter("userId", userId);
	    List<OrderDelivery> shopOrderList = query.list();
	    return shopOrderList;
	}
	
	@Override 
	public List<OrderDelivery> getAllOrderShipping() {
		Session session = sessionFactory.openSession();
	    String hql = "SELECT OD FROM OrderDelivery OD, ShopOrder SO WHERE OD.shopOrder.id = SO.id and SO.orderStatus.id = 2 and OD.deliveryStatus = 1";
	    Query query = session.createQuery(hql);
	    List<OrderDelivery> shopOrderList = query.list();
	    return shopOrderList;
	}
	
	
}
