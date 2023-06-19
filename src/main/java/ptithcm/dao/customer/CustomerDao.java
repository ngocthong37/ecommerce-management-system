package ptithcm.dao.customer;

import java.util.List;

import ptithcm.model.customer.Customer;
import ptithcm.model.order.OrderLine;
import ptithcm.model.shop.ShopOrder;

public interface CustomerDao {
	public Customer getCustomerById(int id);
	public Customer getCustomerByUsernamePassword(String userName, String password);
	public List<ShopOrder> getOrderListById(int customerId);
	public List<OrderLine> getLinesById(int shopOrderId);
	ShopOrder getShopOrderById(int orderId);
}
