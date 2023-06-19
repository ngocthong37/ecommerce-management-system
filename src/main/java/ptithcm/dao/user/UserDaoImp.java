package ptithcm.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.model.customer.Customer;
import ptithcm.model.shoppingCart.ShoppingCartItem;
import ptithcm.model.user.User;
import ptithcm.model.user.UserPermission;

@Repository
public class UserDaoImp implements UserDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User findByUserNameAndPasswordAndStatus(String userName, String password, boolean status) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User c where c.username = :username and c.password = :password and c.status = :status";
		Query query = session.createQuery(hql);
		query.setParameter("username", userName);
		query.setParameter("password", password);
		query.setParameter("status", status);
		return (User) query.uniqueResult();
	}

	@Override
	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From User c where c.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (User) query.uniqueResult();
		
	}

	
	
	@Override
	public List<UserPermission> getListPermissions() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserPermission";
		Query query = session.createQuery(hql);
		List<UserPermission> list = query.list();
		return list;
		
	}

	@Override
	public UserPermission getPermissionbyId(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From UserPermission c where c.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (UserPermission) query.uniqueResult();
	}

	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		return list;
	}
	
	@Override
	public List<User> getAllShipper() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User U where U.userPermission.id = 6";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		return list;
	}
	
}
