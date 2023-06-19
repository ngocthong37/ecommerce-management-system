package ptithcm.dao.ManageOrder;

import java.util.List;

import ptithcm.model.order.OrderDelivery;
import ptithcm.model.shop.ShopOrder;

public interface ManageOrderDao {
	List<ShopOrder> getListShopOrderByStatus();

	Long getQuantityOfOrder(int orderId);

	ShopOrder getShopOrderById(int orderId);

	List<OrderDelivery> getListOfOrderShippingByShipper(int userId);
	List<OrderDelivery> getAllOrderShipping();

}