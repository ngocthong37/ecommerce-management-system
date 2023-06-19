package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.customer.CustomerDao;
import ptithcm.model.customer.Customer;
import ptithcm.model.order.OrderLine;
import ptithcm.model.shop.ShopOrder;

@Service
public class CustomerService {
	@Autowired
	CustomerDao customerDao;
	
	public Customer getCustomerById(int id) {
		Customer customer = customerDao.getCustomerById(id);
		if (customer != null) {
			return  customer;
		}
		return null;
	}
	
	public Customer getCustomerByUsernamePassword(String userName,String password) {
		Customer customer = customerDao.getCustomerByUsernamePassword(userName, password);
		if (customer != null) {
			return  customer;
		}
		return null;
	}
	
	public List<ShopOrder> getShopOrdersById(int ctmId){
		List<ShopOrder> list = customerDao.getOrderListById(ctmId);
		if (list != null) {
			return  list;
		}
		return null;
	}
	
	public List<OrderLine> getLines(int shopOrderId){
		List<OrderLine> list = customerDao.getLinesById(shopOrderId);
		if (list != null) {
			return  list;
		}
		return null;
	}
	
	public ShopOrder getShopOrderById(int orderId) {
		return customerDao.getShopOrderById(orderId);
	}
	
}
