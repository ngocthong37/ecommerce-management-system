package ptithcm.dao.admin;

import java.util.List;

import ptithcm.model.order.OrderLine;
import ptithcm.model.shop.ShopOrder;

public interface AnalyticsDao {
	public List<ShopOrder> getShopOrderByYear(int year);

	public List<ShopOrder> getShopOrderByMonthInYear(int month, int year);

	public List<ShopOrder> getShopOrderByQuarter(int year, int quarter);

	public List<OrderLine> getOrderLineByYear(int year);

	public List<OrderLine> getOrderLineByYearAndMonth(int year, int month);
}
