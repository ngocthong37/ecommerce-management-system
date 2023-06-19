package ptithcm.controller.admin;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.constant.SystemConstant;
import ptithcm.dto.CustomerOrderDTO;
import ptithcm.model.customer.Customer;
import ptithcm.model.order.DeliveryStatus;
import ptithcm.model.order.OrderDelivery;
import ptithcm.model.order.OrderLine;
import ptithcm.model.order.OrderStatus;
import ptithcm.model.shop.ShopOrder;
import ptithcm.model.user.User;
import ptithcm.service.CustomerService;
import ptithcm.service.ManageOrderService;
import ptithcm.service.OrderDeliveryService;
import ptithcm.service.UserService;
import ptithcm.util.SessionUtil;

@Transactional
@Controller
@RequestMapping(value = "/admin/manage-ordered/")
public class ManageOrderController {
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	UserService userService;
	@Autowired
	ManageOrderService manageOrderService;

	@Autowired
	OrderDeliveryService orderDeliveryService;

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String showOrderedList(ModelMap model, HttpServletRequest request) {
		String roleAdmin = "";
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.USER_MODEL) != null) {
			roleAdmin = (String) ((User) SessionUtil.getInstance().getValue(request,
					SystemConstant.Model.USER_MODEL)).getUserPermission().getValue();
		}
		if (roleAdmin.equals(SystemConstant.Authorization.ADMIN)) {
			model.addAttribute("isAdmin", true);
		}
		else {
			model.addAttribute("isAdmin", false);
		}

		List<CustomerOrderDTO> customerOrderList = manageOrderService.getOderCustomerDTOList();
		List<User> shipperList = userService.getAllShipperList();
		model.addAttribute("shipperList", shipperList);
		model.addAttribute("listOrdered", customerOrderList);
		return "admin/manage-ordered/list";
	}

	@RequestMapping(value = "list/{id}", method = RequestMethod.GET)
	public String orderDetail(ModelMap model, @PathVariable Integer id) {
		List<OrderLine> listLines = customerService.getLines(id);
		model.addAttribute("listLines", listLines);
		ShopOrder shopOrder = customerService.getShopOrderById(id);
		String address = shopOrder.getCustomerAddress().getAddress().getDetailAddress() + ", "
				+ shopOrder.getCustomerAddress().getAddress().getWard().getName() + ", "
				+ shopOrder.getCustomerAddress().getAddress().getWard().getDistrict().getName() + ", "
				+ shopOrder.getCustomerAddress().getAddress().getWard().getProvince().getName();
		model.addAttribute("address", address);
		System.out.println(shopOrder.getOrderStatus().getStatus());
		if (shopOrder.getOrderStatus().getStatus().equals("ON_HOLD")) {
			model.addAttribute("test", true);
		} else {
			model.addAttribute("test", false);
		}
		model.addAttribute("sum", shopOrder.getOrderTotal());
		model.addAttribute("id", id);
		return "admin/manage-ordered/detail";
	}

	@RequestMapping(value = "list/confirmed/{orderId}")
	public String confirmOrder(@PathVariable int orderId, HttpServletRequest request,
			@RequestParam("userId") Integer userId) {
		ShopOrder shopOrder = manageOrderService.getShopOrderById(orderId);
		System.out.println("userId: " + userId);
		User user = userService.findUserById(userId);
		System.out.println("User name: " + user.getUsername());
		java.util.Date now = new java.util.Date();
		java.sql.Date deliveryDate = new java.sql.Date(now.getTime());
		Calendar calendar = java.util.Calendar.getInstance();
		calendar.add(java.util.Calendar.DAY_OF_YEAR, 1);
		OrderDelivery orderDelivery = new OrderDelivery();
		orderDelivery.setShopOrder(shopOrder);
		orderDelivery.setUser(user);
		orderDelivery.setCreatedAt(new java.sql.Date(now.getTime()));
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(new java.util.Date());
		int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
		if (hour < 12) {
		    System.out.println("Đang là buổi sáng");
		    orderDelivery.setDeliveryDate(deliveryDate);
		} else {
			deliveryDate = new java.sql.Date(deliveryDate.getTime());
		    orderDelivery.setDeliveryDate(deliveryDate);
		}
		
		DeliveryStatus deliveryStatus = new DeliveryStatus();
		deliveryStatus.setId(1);
		orderDelivery.setDeliveryStatus(deliveryStatus);
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setId(2);
		shopOrder.setOrderStatus(orderStatus);
		String address = "";
		if (shopOrder.getAddressDelivery() == null) {
			address = shopOrder.getCustomerAddress().getAddress().getDetailAddress() + ", "
					+ shopOrder.getCustomerAddress().getAddress().getWard().getName() + ", "
					+ shopOrder.getCustomerAddress().getAddress().getWard().getDistrict().getName() + ", "
					+ shopOrder.getCustomerAddress().getAddress().getWard().getProvince().getName();
			shopOrder.setAddressDelivery(address);
		} else
			shopOrder.setAddressDelivery(shopOrder.getAddressDelivery());
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		if (shopOrder != null) {
			try {
				System.out.println("Luu order delivery");
				session.saveOrUpdate(orderDelivery);
				session.merge(shopOrder);
				t.commit();
			} catch (Exception e) {
				System.out.println("Error: " + e.toString());
				t.rollback();
			} finally {
				session.close();
			}
		}
		return "redirect:/admin/manage-ordered/list.htm";
	}

	@RequestMapping(value = "list/cancel/{orderId}")
	public String cancelOrder(@PathVariable int orderId) {
		ShopOrder shopOrder = manageOrderService.getShopOrderById(orderId);
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setId(4);
		shopOrder.setOrderStatus(orderStatus);
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		if (shopOrder != null) {
			try {
				session.merge(shopOrder);
				t.commit();
				System.out.println("Cap nhat don hang thanh cong");
			} catch (Exception e) {
				System.out.println("Error: " + e.toString());
				t.rollback();
			} finally {
				session.close();
			}
		}
		return "redirect:/admin/manage-ordered/list.htm";
	}
}
