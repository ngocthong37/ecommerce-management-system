package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.inventory.InventoryDao;
import ptithcm.dao.user.UserDao;
import ptithcm.model.inventory.InventoryReceiving;
import ptithcm.model.inventory.InventoryReceivingDetails;
import ptithcm.model.inventory.StatusReceiving;
import ptithcm.model.user.User;

@Service 
public class InventoryService {
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private UserDao userDao;
	
	public List<StatusReceiving> getAllStatusReceivings() {
		List<StatusReceiving> listStatus = inventoryDao.getAllStatusReceivings();
		if (listStatus == null) {
			return null;
		}
		return listStatus;
	}
	
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}
	
	public List<InventoryReceiving> getAllInventoryReceivings(){
		List<InventoryReceiving> listStatus = inventoryDao.getAllInventoryReceivings();
		if (listStatus == null) {
			return null;
		}
		return listStatus;
	}
	
	public List<InventoryReceivingDetails> listInventoryReceivingDetails(int id){
		List<InventoryReceivingDetails> listDetails = inventoryDao.getDetails(id);
		if (listDetails == null) {
			return null;
		}
		return listDetails;
	}
	
	public InventoryReceiving getInventoryReceivingById(int id) {
		return inventoryDao.getInventoryReceivingById(id);
	}
}