package ptithcm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.ManageOrder.ManageOrderDao;
import ptithcm.dto.OrderDeliveryDTO;
import ptithcm.model.order.OrderDelivery;
import ptithcm.model.order.OrderLine;
import ptithcm.model.shop.ShopOrder;

@Service
public class OrderDeliveryService {
	@Autowired
	ManageOrderDao manageOrderDao;
	
	
	
	public List<OrderDeliveryDTO> orderDeliveryDTOListByShipper(int userId) {
		List<OrderDelivery> listOrderForShipping = manageOrderDao.getListOfOrderShippingByShipper(userId);
		System.out.println("List shipping: " + listOrderForShipping.size());
		List<OrderDeliveryDTO> orderDeliveryList = new ArrayList<>();
		if (listOrderForShipping.size() > 0) {
			for (OrderDelivery orderDelivery: listOrderForShipping) {
				List<OrderLine> orderItemList = (List<OrderLine>) orderDelivery.getShopOrder().getOrderLines();
				OrderDeliveryDTO orderDeliveryDTO = new OrderDeliveryDTO();
				orderDeliveryDTO.setId(orderDelivery.getId());
				orderDeliveryDTO.setUserId(orderDelivery.getUser().getId());
				orderDeliveryDTO.setDeliveryDate(orderDelivery.getDeliveryDate());
				orderDeliveryDTO.setDeliveryReceived(orderDelivery.getReceivedDate());
				orderDeliveryDTO.setListOrderDelivery(orderItemList);
				orderDeliveryDTO.setOrderId(orderDelivery.getShopOrder().getId());
				ShopOrder shopOrder = orderDelivery.getShopOrder();
				String address = "";
				if (shopOrder.getAddressDelivery() == null) {
					address = shopOrder.getCustomerAddress().getAddress().getDetailAddress() + ", "
							+ shopOrder.getCustomerAddress().getAddress().getWard().getName() + ", "
							+ shopOrder.getCustomerAddress().getAddress().getWard().getDistrict().getName() + ", "
							+ shopOrder.getCustomerAddress().getAddress().getWard().getProvince().getName();
					orderDeliveryDTO.setCustomerAddress(address); 
				}
				else {
					orderDeliveryDTO.setCustomerAddress(shopOrder.getAddressDelivery());
				}
				
				String customerName = shopOrder.getCustomerAddress().getCustomer().getCustomerProfile().getName();
				String phoneNumber = shopOrder.getCustomerAddress().getAddress().getPhoneNumber();
				orderDeliveryDTO.setCustomerName(customerName);
				orderDeliveryDTO.setPhoneNumber(phoneNumber);
				orderDeliveryDTO.setTotalMoney(shopOrder.getOrderTotal());
				orderDeliveryList.add(orderDeliveryDTO);
			}
			System.out.println("Sizeee: " + orderDeliveryList.size());
			return orderDeliveryList;
		}
		return null;
	}
	
	public List<OrderDeliveryDTO> getAllOrderShipping() {
		List<OrderDelivery> listOrderForShipping = manageOrderDao.getAllOrderShipping();
		List<OrderDeliveryDTO> orderDeliveryList = new ArrayList<>();
		if (listOrderForShipping.size() > 0) {
			for (OrderDelivery orderDelivery: listOrderForShipping) {
				List<OrderLine> orderItemList = (List<OrderLine>) orderDelivery.getShopOrder().getOrderLines();
				OrderDeliveryDTO orderDeliveryDTO = new OrderDeliveryDTO();
				orderDeliveryDTO.setId(orderDelivery.getId());
				orderDeliveryDTO.setUserId(orderDelivery.getUser().getId());
				orderDeliveryDTO.setDeliveryDate(orderDelivery.getDeliveryDate());
				orderDeliveryDTO.setDeliveryReceived(orderDelivery.getReceivedDate());
				orderDeliveryDTO.setListOrderDelivery(orderItemList);
				orderDeliveryDTO.setOrderId(orderDelivery.getShopOrder().getId());
				orderDeliveryDTO.setUserName(orderDelivery.getUser().getUserProfile().getName());
				ShopOrder shopOrder = orderDelivery.getShopOrder();
				String address = "";
				if (shopOrder.getAddressDelivery() == null) {
					address = shopOrder.getCustomerAddress().getAddress().getDetailAddress() + ", "
							+ shopOrder.getCustomerAddress().getAddress().getWard().getName() + ", "
							+ shopOrder.getCustomerAddress().getAddress().getWard().getDistrict().getName() + ", "
							+ shopOrder.getCustomerAddress().getAddress().getWard().getProvince().getName();
					orderDeliveryDTO.setCustomerAddress(address); 
				}
				else {
					orderDeliveryDTO.setCustomerAddress(shopOrder.getAddressDelivery());
				}
				
				String customerName = shopOrder.getCustomerAddress().getCustomer().getCustomerProfile().getName();
				String phoneNumber = shopOrder.getCustomerAddress().getAddress().getPhoneNumber();
				orderDeliveryDTO.setCustomerName(customerName);
				orderDeliveryDTO.setPhoneNumber(phoneNumber);
				orderDeliveryDTO.setTotalMoney(shopOrder.getOrderTotal());
				orderDeliveryList.add(orderDeliveryDTO);
			}
			return orderDeliveryList;
		}
		return null;
	}
	
	public OrderDeliveryDTO getOrderDeliveryDTOById(int id, int userId) {
		for (OrderDeliveryDTO orderDeliveryDTO: getAllOrderShipping()) {
			if (orderDeliveryDTO.getId() == id) {
				return orderDeliveryDTO;
			}
		}
		return null;
	}
	
	public OrderDelivery getOrderDeliveryById(int orderId, int userId) {
		List<OrderDelivery> listOrderForShipping = manageOrderDao.getListOfOrderShippingByShipper(userId);
		for (OrderDelivery orderDelivery: listOrderForShipping) {
			if (orderDelivery.getId() == orderId) {
				return orderDelivery;
			}
		}
		return null;
	}
	
}
