package ptithcm.service.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.constant.SystemConstant;
import ptithcm.dao.admin.AnalyticsDao;
import ptithcm.model.order.OrderLine;
import ptithcm.model.shop.ShopOrder;

@Service
public class AnalyticsService {
	@Autowired
	AnalyticsDao analyticsDao;

	public List<ShopOrder> getShopOrdersByStatusAndYear(int year, String status) {
		List<ShopOrder> shopOrders = analyticsDao.getShopOrderByYear(year);
		return filterShopOrdersByStatus(shopOrders, status);
	}

	public List<OrderLine> getOrderLinesByStatusAndYear(int year, String status) {
		List<OrderLine> orderLines = analyticsDao.getOrderLineByYear(year);
		return filterOrderLinesByStatus(orderLines, status);
	}

	public List<OrderLine> getOrderLinesByStatusAndYearAndMonth(int year, int month, String status) {
		List<OrderLine> orderLines = analyticsDao.getOrderLineByYearAndMonth(year, month);
		return filterOrderLinesByStatus(orderLines, status);
	}

	public List<ShopOrder> getShopOrdersByStatusAndYearAndMonth(int year, int month, String status) {
		List<ShopOrder> shopOrders = analyticsDao.getShopOrderByMonthInYear(year, month);
		return filterShopOrdersByStatus(shopOrders, status);
	}

	private List<ShopOrder> filterShopOrdersByStatus(List<ShopOrder> shopOrders, String status) {
		List<ShopOrder> filteredShopOrders = new ArrayList<>();

		for (ShopOrder shopOrder : shopOrders) {
			if (shopOrder.getOrderStatus().getStatus().equals(status)) {
				filteredShopOrders.add(shopOrder);
			}
		}

		return filteredShopOrders;
	}

	public double getTotalSalesByYearAndMonth(int year, int month) {
		List<ShopOrder> listShopOrders = analyticsDao.getShopOrderByMonthInYear(month, year);
		List<ShopOrder> completedListShopOrders = filterShopOrdersByStatus(listShopOrders,
				SystemConstant.OrderStatus.COMPLETED);

		double totalSales = 0.0;
		for (ShopOrder shopOrder : completedListShopOrders) {
			totalSales += shopOrder.getOrderTotal();
		}

		return totalSales;
	}

	public int getTotalProductsSoldByYearAndMonth(int year, int month) {
		List<ShopOrder> listShopOrders = analyticsDao.getShopOrderByMonthInYear(month, year);
		List<ShopOrder> completedListShopOrders = filterShopOrdersByStatus(listShopOrders,
				SystemConstant.OrderStatus.COMPLETED);

		int totalProductsSold = 0;
		for (ShopOrder shopOrder : completedListShopOrders) {
			List<OrderLine> orderLines = new ArrayList<>(shopOrder.getOrderLines());
			for (OrderLine orderLine : orderLines) {
				totalProductsSold += orderLine.getQuantity();
			}
		}

		return totalProductsSold;
	}

	private List<OrderLine> filterOrderLinesByStatus(List<OrderLine> orderLines, String status) {
		List<OrderLine> filteredOrderLines = new ArrayList<>();

		for (OrderLine orderLine : orderLines) {
			if (orderLine.getShopOrder().getOrderStatus().getStatus().equals(status)) {
				filteredOrderLines.add(orderLine);
			}
		}

		return filteredOrderLines;
	}

	public Map<String, Integer> getShopOrderCountByStatusAndYear(int year) {
		List<ShopOrder> shopOrders = analyticsDao.getShopOrderByYear(year);
		return countShopOrdersByStatus(shopOrders);
	}

	public Map<String, Integer> getShopOrderCountByStatusAndYearAndMonth(int year, int month) {
		List<ShopOrder> shopOrders = analyticsDao.getShopOrderByMonthInYear(month, year);
		return countShopOrdersByStatus(shopOrders);
	}

	private Map<String, Integer> countShopOrdersByStatus(List<ShopOrder> shopOrders) {
		Map<String, Integer> orderCountByStatus = new HashMap<>();

		for (ShopOrder shopOrder : shopOrders) {
			String status = shopOrder.getOrderStatus().getStatus();
			orderCountByStatus.put(status, orderCountByStatus.getOrDefault(status, 0) + 1);
		}

		return orderCountByStatus;
	}
}
