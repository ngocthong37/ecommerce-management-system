package ptithcm.controller.admin;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.constant.SystemConstant;
import ptithcm.model.order.OrderLine;
import ptithcm.model.shop.ShopOrder;
import ptithcm.service.admin.AnalyticsService;

@Controller
@Transactional
@RequestMapping("/admin/")
public class AnalyticsController {

	@Autowired
	AnalyticsService analyticsService;

	@RequestMapping(value = "analytics", method = RequestMethod.GET)
	public String showAnalytics(ModelMap modelMap) {
		int year = 2023; // Specify the year for which you want to fetch the analytics data

		// Get the current month
		YearMonth currentMonth = YearMonth.now();
		int month = currentMonth.getMonthValue();

		List<Double> salesData = new ArrayList<>();
		List<Integer> productSoldData = new ArrayList<>();
		for (int tempMonth = 1; tempMonth <= 12; tempMonth++) {
			double totalSales = analyticsService.getTotalSalesByYearAndMonth(year, tempMonth);
			int totalProductSold = analyticsService.getTotalProductsSoldByYearAndMonth(year, tempMonth);
			productSoldData.add(totalProductSold);
			if (totalSales == 0) {
				salesData.add(0.0);
			} else {
				salesData.add(totalSales);
			}
		}

		List<ShopOrder> completedShopOrders = analyticsService.getShopOrdersByStatusAndYearAndMonth(year,
				currentMonth.getMonthValue(), SystemConstant.OrderStatus.COMPLETED);

		Map<String, Integer> orderCountByStatus = analyticsService.getShopOrderCountByStatusAndYearAndMonth(year,
				month);

		modelMap.addAttribute("completedShopOrders", completedShopOrders);
		modelMap.addAttribute("orderCountByStatus", orderCountByStatus);
		modelMap.addAttribute("currentMonth", currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
		modelMap.addAttribute("salesData", salesData);
		modelMap.addAttribute("productSoldData", productSoldData);
		modelMap.addAttribute("currentYear", year);
		return "admin/analytics";
	}

}
