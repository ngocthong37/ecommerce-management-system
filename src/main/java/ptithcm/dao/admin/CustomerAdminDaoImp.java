package ptithcm.dao.admin;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ptithcm.model.customer.Customer;

@Repository
public class CustomerAdminDaoImp implements CustomerAdminDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getListCustomer (){
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Customer";
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		return list;
	};
	
	
}

