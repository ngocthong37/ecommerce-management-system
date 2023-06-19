package ptithcm.dao.address;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ptithcm.model.address.Address;
import ptithcm.model.address.District;
import ptithcm.model.address.Province;
import ptithcm.model.address.Ward;
import ptithcm.model.customer.CustomerAddress;
import ptithcm.model.product.ProductItem;

@Controller
@Transactional
public class AddressDaoImp implements AddressDao {
	@Autowired
	SessionFactory factory;

	@Override
	public List<CustomerAddress> getAddressListByID(int addressId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerAddress s WHERE s.customer.id = :addressId";
		Query query = session.createQuery(hql);
		query.setParameter("addressId", addressId);
		List<CustomerAddress> addressList = query.list();
		return addressList;
	}

	@Override
	public int deleteAddress(int id) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM Address WHERE id = " + id;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public int deleteCustomerAddress(int id) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM CustomerAddress WHERE id = " + id;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public CustomerAddress getAddressById(int addressId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CustomerAddress p WHERE p.id = :addressId";
		Query query = session.createQuery(hql);
		query.setParameter("addressId", addressId);
		return (CustomerAddress) query.uniqueResult();
	}

	@Override
	public List<Province> getProvincesList() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Province";
		Query query = session.createQuery(hql);
		List<Province> provinceList = query.list();
		return provinceList;
	}

	@Override
	public List<Ward> getWardsList(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Ward w WHERE w.district.id = :districtId";
		Query query = session.createQuery(hql);
		query.setParameter("districtId",id );
		List<Ward> List =  query.list();
		return List;
	}

	@Override
	public List<District> getDistricesList(int provinceId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM District d WHERE d.province.id = :provinceId";
		Query query = session.createQuery(hql);
		query.setParameter("provinceId",provinceId);
		List<District> list = query.list();
		return list;
	}

	@Override
	public Province getProvince(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Province p WHERE p.id = :Id";
		Query query = session.createQuery(hql);
		query.setParameter("Id", id);
		return (Province) query.uniqueResult();
	}

	@Override
	public District getDistrict(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM District p WHERE p.id = :Id";
		Query query = session.createQuery(hql);
		query.setParameter("Id", id);
		return (District) query.uniqueResult();
	}

	@Override
	public Ward getWard(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Ward p WHERE p.id = :Id";
		Query query = session.createQuery(hql);
		query.setParameter("Id", id);
		return (Ward) query.uniqueResult();
	}

	
}
