package ptithcm.dao.order;


import org.springframework.stereotype.Service;

import ptithcm.model.order.OrderStatus;

@Service
public interface OrderStatusDao{
	public OrderStatus getOrderStatusById(int ID);
}
