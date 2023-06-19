package ptithcm.dao.inventory;

import java.util.List;

import ptithcm.model.inventory.InventoryReceiving;
import ptithcm.model.inventory.InventoryReceivingDetails;
import ptithcm.model.inventory.StatusReceiving;

public interface InventoryDao {
	public List<StatusReceiving> getAllStatusReceivings();
	public List<InventoryReceiving> getAllInventoryReceivings();
	public List<InventoryReceivingDetails> getDetails(int id);
	public InventoryReceiving getInventoryReceivingById(int id);
}
