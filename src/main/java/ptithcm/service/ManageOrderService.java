package ptithcm.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.ManageOrder.ManageOrderDao;
import ptithcm.dto.CustomerOrderDTO;
import ptithcm.model.order.OrderLine;
import ptithcm.model.product.ProductItem;
import ptithcm.model.shop.ShopOrder;

@Service
public class ManageOrderService {
	@Autowired
	ManageOrderDao manageOrderDao;

	@Autowired
	ProductService productService;

	public List<ShopOrder> shopOrderList() {
		return manageOrderDao.getListShopOrderByStatus();
	}

	public ProductItem getProductItemById(int productItemId) {
		return productService.getProductItemById(productItemId);
	}

	public ShopOrder getShopOrderById(int orderId) {
		return manageOrderDao.getShopOrderById(orderId);
	}

	public List<CustomerOrderDTO> getOderCustomerDTOList() {
		List<ShopOrder> orderLineList = shopOrderList();
		List<CustomerOrderDTO> customerOrderDTOList = new ArrayList<CustomerOrderDTO>();

		if (orderLineList.size() > 0) {
			for (ShopOrder so : orderLineList) {
				CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();
				customerOrderDTO.setOrderId(so.getId());
				customerOrderDTO.setId(so.getId().toString());
				customerOrderDTO.setPrice(so.getOrderTotal());
				Date orderDate = so.getOrderDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				customerOrderDTO.setQuantity(manageOrderDao.getQuantityOfOrder(so.getId()));
				String dateString = dateFormat.format(orderDate);
				customerOrderDTO.setDateOrdered(dateString);
				customerOrderDTOList.add(customerOrderDTO);
			}
		}
		return customerOrderDTOList;
	}

}