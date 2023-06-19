package ptithcm.dao.order;


import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import ptithcm.model.order.OrderStatus;
import ptithcm.model.pay.CustomerPaymentMethod;
import ptithcm.model.product.ProductItem;
import ptithcm.model.ship.ShippingMethod;

@Controller
@Transactional
public class OrderStatusDaoImp implements OrderStatusDao {
	@Autowired
	SessionFactory factory;
	@Override
	public OrderStatus getOrderStatusById(int Id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderStatus p WHERE p.id = :Id";
		Query query = session.createQuery(hql);
		query.setParameter("Id",Id);
		return (OrderStatus) query.uniqueResult();
	}
}