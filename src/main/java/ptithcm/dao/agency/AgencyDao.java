package ptithcm.dao.agency;

import java.util.List;

import ptithcm.model.agency.*;
import ptithcm.model.inventory.StatusReceiving;

public interface AgencyDao {
	public Agency getAgencyById (int id);
	public List<Agency> getListAgencies();
	public StatusReceiving getStatusReceivingById(int Id);
	public int deleteAgencyById(int id);	
}