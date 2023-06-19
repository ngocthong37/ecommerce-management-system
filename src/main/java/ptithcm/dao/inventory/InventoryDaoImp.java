package ptithcm.dao.inventory;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.model.inventory.InventoryReceiving;
import ptithcm.model.inventory.InventoryReceivingDetails;
import ptithcm.model.inventory.StatusReceiving;
import ptithcm.model.shoppingCart.ShoppingCart;


@Service
public class InventoryDaoImp implements InventoryDao {
	@Autowired
	SessionFactory sessionFactory;
	public List<StatusReceiving> getAllStatusReceivings(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM StatusReceiving";
		Query query = session.createQuery(hql);
		List<StatusReceiving> list = query.list();
		return list;
	}
	@Override
	public List<InventoryReceiving> getAllInventoryReceivings() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM InventoryReceiving";
		Query query = session.createQuery(hql);
		List<InventoryReceiving> list = query.list();
		return list;
	}
	@Override
	public List<InventoryReceivingDetails> getDetails(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM InventoryReceivingDetails c WHERE c.inventoryReceiving.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<InventoryReceivingDetails> list = query.list();
		return list;
	}
	@Override
	public InventoryReceiving getInventoryReceivingById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM InventoryReceiving c WHERE c.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (InventoryReceiving) query.uniqueResult();
	}
}
