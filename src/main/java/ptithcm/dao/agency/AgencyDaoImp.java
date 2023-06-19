package ptithcm.dao.agency;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.model.agency.Agency;
import ptithcm.model.inventory.StatusReceiving;

@Service
public class AgencyDaoImp implements AgencyDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Agency getAgencyById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From Agency c where c.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (Agency) query.uniqueResult();
	}

	@Override
	public List<Agency> getListAgencies() {
		Session session = sessionFactory.openSession();
		String hql = "From Agency";
		Query query = session.createQuery(hql);
		List<Agency> list = query.list();
		return list;
	}

	
	@Override
	public StatusReceiving getStatusReceivingById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "From StatusReceiving c where c.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (StatusReceiving) query.uniqueResult();
	}
	
	@Override
	public int deleteAgencyById(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "DELETE FROM Agency A WHERE A.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result;
	}
}
