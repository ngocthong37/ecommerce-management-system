package ptithcm.dao.payment;


import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ptithcm.model.pay.CustomerPaymentMethod;
import ptithcm.model.pay.PaymentType;
import ptithcm.model.ship.ShippingMethod;


@Controller
@Transactional
public class PaymentDaoImp implements PaymentDao {
	@Autowired
	SessionFactory factory;

	public List<CustomerPaymentMethod> getPaymentListById(int ctmID) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerPaymentMethod s where s.customer.id = :ctmID";
		Query query = session.createQuery(hql);
		query.setParameter("ctmID", ctmID);
		List<CustomerPaymentMethod> list = query.list();
		return list;
	}

	public List<ShippingMethod> getListShippingMethods() {
		Session session = factory.getCurrentSession();
		String hql = "FROM ShippingMethod";
		Query query = session.createQuery(hql);
		List<ShippingMethod> list = query.list();
		return list;
	}

	@Override
	public ShippingMethod getShippingById(int ID) {
			Session session = factory.getCurrentSession();
			String hql = "FROM ShippingMethod p WHERE p.id = :ID";
			Query query = session.createQuery(hql);
			query.setParameter("ID",ID);
			return (ShippingMethod) query.uniqueResult();
	}

	@Override
	public CustomerPaymentMethod getPaymentById(int ID) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerPaymentMethod p WHERE p.id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID",ID);
		return (CustomerPaymentMethod) query.uniqueResult();
	}

	@Override
	public PaymentType gePaymentTypeById(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM PaymentType p WHERE p.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id",id);
		return (PaymentType) query.uniqueResult();
	}
	

}