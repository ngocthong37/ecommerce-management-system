package ptithcm.service;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.agency.AgencyDao;
import ptithcm.dao.customer.CustomerDao;
import ptithcm.model.agency.Agency;
import ptithcm.model.customer.Customer;
import ptithcm.model.inventory.StatusReceiving;
import ptithcm.model.product.ProductItem;

@Service
public class AgencyService {
	@Autowired
	private AgencyDao agencyDao; 
	
	public List<Agency> getListAgencies(){
		List<Agency> listAgencies = agencyDao.getListAgencies() ;
		if (listAgencies == null) {
			return null;
		}
		return listAgencies;
	}
	
	public Agency getAgencyById(int Id) {
		Agency agency = agencyDao.getAgencyById(Id);
		if (agency != null) {
			return agency;
		}
		return null;
	}
	public StatusReceiving getStatusReceivingById (int Id) {
		StatusReceiving status = agencyDao.getStatusReceivingById(Id);
		if (status != null) {
			return status;
		}
		return null;
	}
	
	public int deleteAgencyById(int id) {
		return agencyDao.deleteAgencyById(id);
	}
	
}
