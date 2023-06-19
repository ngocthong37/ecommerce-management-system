package ptithcm.dao.customer;

import java.awt.font.LineMetrics;
import java.util.List;
import java.util.function.IntToDoubleFunction;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.PicocliException;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.loader.collection.PaddedBatchingCollectionInitializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.model.customer.Customer;
import ptithcm.model.order.OrderLine;
import ptithcm.model.shop.ShopOrder;


@Service
public class CustomerDaoImp implements CustomerDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Customer getCustomerById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Customer c where c.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (Customer) query.uniqueResult();
	}


	public Customer getCustomerByUsernamePassword(String userName, String password) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Customer c where c.userName = :userName and c.password = :password";
		Query query = session.createQuery(hql);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		return (Customer) query.uniqueResult();
	}


	@Override
	public List<ShopOrder> getOrderListById(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ShopOrder s where s.customerAddress.customer.id = :customerId";
		Query query = session.createQuery(hql);
		query.setParameter("customerId", customerId);
		List<ShopOrder> list = query.list();
		return list;
	} 
	
	
	@Override
	public List<OrderLine> getLinesById( int shopOrderId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "from OrderLine s where s.shopOrder.id = :shopOrderId";
		Query query = session.createQuery(hql);
		query.setParameter("shopOrderId", shopOrderId);
		List<OrderLine> list = query.list();
		return list;
	}
	
	@Override
	public ShopOrder getShopOrderById(int orderId) {
		Session session = sessionFactory.openSession();
		String hql = "select SO from ShopOrder SO where SO.id = :orderId";
		Query query = session.createQuery(hql);
		query.setParameter("orderId", orderId);
		return (ShopOrder) query.uniqueResult();
	}

}
