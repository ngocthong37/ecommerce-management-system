package ptithcm.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.constant.SystemConstant;
import ptithcm.model.address.Address;
import ptithcm.model.address.District;
import ptithcm.model.address.Province;
import ptithcm.model.address.Ward;
import ptithcm.model.customer.Customer;
import ptithcm.model.customer.CustomerAddress;
import ptithcm.model.customer.CustomerProfile;
import ptithcm.model.order.OrderLine;
import ptithcm.model.order.OrderStatus;
import ptithcm.model.shop.ShopOrder;
import ptithcm.service.AddressService;
import ptithcm.service.CustomerService;
import ptithcm.util.SessionUtil;

@Transactional
@Controller
@RequestMapping(value = "/customer/")
public class CustomerController {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	AddressService addressService;
	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "newAddress", method = RequestMethod.GET)
	public String newAddress(ModelMap model) {
		List<Province> listPros = addressService.listProvinces();
		model.addAttribute("listPros", listPros);
		return "customer/newAddress";
	}

	@RequestMapping(value = "newAddress", method = RequestMethod.POST)
	public String showNewAddress1(ModelMap model, @RequestParam("province") int provinceId,
			@RequestParam("district") int districtId, @RequestParam("ward") int wardId,
			@RequestParam("details") String details, @RequestParam("phone") String phone, HttpServletRequest request) {
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL) == null) {
			return "redirect:/e-commerce/login.htm";
		}
		Date sqlDate = new Date(System.currentTimeMillis());
		model.addAttribute("details", details);
		if (provinceId != 0) {
			if (districtId != 0) {
				if (wardId != 0) {
					Address newAddress = new Address();
					newAddress.setDetailAddress(details);
					newAddress.setWard(addressService.getWard(wardId));
					newAddress.setPhoneNumber(phone);
					newAddress.setCreateAt(sqlDate);
					Session session = sessionFactory.openSession();
					org.hibernate.Transaction t = session.beginTransaction();
					try {
						session.save(newAddress);
						t.commit();
						model.addAttribute("message", "Thêm mới thành công! ");
						System.out.println("done");
					} catch (Exception e) {
						t.rollback();
						model.addAttribute("message", "Thêm mới thất bại! ");
						System.out.println(e);
					} finally {
						session.close();
					}
					CustomerAddress newCA = new CustomerAddress();
					newCA.setAddress(newAddress);
					newCA.setCustomer((Customer) SessionUtil.getInstance().getValue(request,
							SystemConstant.Model.CUSTOMER_MODEL));
					newCA.setIsDefault(true);
					Session session1 = sessionFactory.openSession();
					org.hibernate.Transaction t1 = session1.beginTransaction();
					try {
						session1.save(newCA);
						t1.commit();
						model.addAttribute("message", "Thêm mới thành công! ");
						System.out.println("done11");
					} catch (Exception e) {
						t1.rollback();
						model.addAttribute("message", "Thêm mới thất bại! ");
						System.out.println(e);
					} finally {
						session1.close();
					}
					return "redirect:/e-commerce/address.htm";
				}
				List<Province> listPros = addressService.listProvinces();
				model.addAttribute("listPros", listPros);
				List<District> listDicts = addressService.listDistricts(provinceId);
				model.addAttribute("listDicts", listDicts);
				List<Ward> listWards = addressService.listWards(districtId);
				model.addAttribute("listWards", listWards);
				model.addAttribute("selectedProvince", provinceId);
				model.addAttribute("selectedDistrict", districtId);
				return "customer/newAddress";
			}
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			List<District> listDicts = addressService.listDistricts(provinceId);
			model.addAttribute("listDicts", listDicts);
			model.addAttribute("selectedProvince", provinceId);
			return "customer/newAddress";
		}
		return "customer/newAddress";
	}

	@RequestMapping(value = "orderManage")
	public String orderManage(HttpServletRequest request, ModelMap model) {
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL) == null) {
			return "redirect:/e-commerce/login.htm";
		}
		int id = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
				.getId();
		List<ShopOrder> listOrders = customerService.getShopOrdersById(id);
		model.addAttribute("listOrders", listOrders);
		return "customer/orderManage";
	}

	@RequestMapping(value = "orderManage/{id}")
	public String orderDetails(HttpServletRequest request, ModelMap model, @PathVariable int id) {
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
		// model.addAttribute("idAddress", shopOrder.getCustomerAddress());
		model.addAttribute("phone",shopOrder.getCustomerAddress().getAddress().getPhoneNumber());
		return "customer/orderDetails";
	}
	
	@RequestMapping(value = "orderManage/cancel/{orderId}")
	public String cancelOrder(@PathVariable int orderId) {
		ShopOrder shopOrder = customerService.getShopOrderById(orderId);
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
		return "redirect:/customer/orderManage.htm";
	}

	@RequestMapping(value = "orderManage/edit/{orderId}")
	public String editOrder(@PathVariable int orderId,ModelMap model, HttpServletRequest request,HttpSession httpSession) {
		httpSession.setAttribute("orderId", orderId);
		int id = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
				.getId();
		List<CustomerAddress> addressList = addressService.getAddressListByID(id);
		model.addAttribute("customerAddress", addressList);
		model.addAttribute("test","test");
		return "e-commerce/address";
	}
	
	@RequestMapping(value = "order/edit/{idAddress}")
	public String editAddressOrder(@PathVariable int idAddress,ModelMap model, HttpServletRequest request,HttpSession httpSession) {
		int orderId = (int) httpSession.getAttribute("orderId");
		ShopOrder shopOrder = customerService.getShopOrderById(orderId);
		CustomerAddress customerAddress = addressService.getAddressById(idAddress);
		shopOrder.setCustomerAddress(customerAddress);
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
		return "redirect:/customer/orderManage.htm";
	}
	
	
	@RequestMapping(value = "editAddress", method = RequestMethod.GET)
	public String editAddress(@RequestParam("id") int id, ModelMap model, HttpServletRequest request) {
		int provinceId = addressService.getAddressById(id).getAddress().getWard().getProvince().getId();
		int districtId = addressService.getAddressById(id).getAddress().getWard().getDistrict().getId();
		int wardId = addressService.getAddressById(id).getAddress().getWard().getId();
		String details = addressService.getAddressById(id).getAddress().getDetailAddress();
		String phone = addressService.getAddressById(id).getAddress().getPhoneNumber();
		List<Province> listPros = addressService.listProvinces();
		model.addAttribute("listPros", listPros);
		model.addAttribute("selectedProvince", provinceId);
		List<District> listDicts = addressService.listDistricts(provinceId);
		model.addAttribute("listDicts", listDicts);
		model.addAttribute("selectedDistrict", districtId);
		List<Ward> listWards = addressService.listWards(districtId);
		model.addAttribute("listWards", listWards);
		model.addAttribute("selectedWard", wardId);
		model.addAttribute("details", details);
		model.addAttribute("phone", phone);
		model.addAttribute("id", id);
		return "customer/editAddress";
	}

	@RequestMapping(value = "editAddress/done", method = RequestMethod.POST)
	public String doneEdit(@RequestParam("province") int provinceId, @RequestParam("district") int districtId,
			@RequestParam("ward") int wardId, @RequestParam("details") String details,
			@RequestParam("phone") String phone, @RequestParam("id") int id, ModelMap model) {
		Date sqlDate = new Date(System.currentTimeMillis());
		Address editAddress = addressService.getAddressById(id).getAddress();
		editAddress.setDetailAddress(details);
		editAddress.setWard(addressService.getWard(wardId));
		editAddress.setPhoneNumber(phone);
		editAddress.setModifiedAt(sqlDate);
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction t = session.beginTransaction();
		try {
			session.merge(editAddress);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công! ");
			System.out.println("done");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại! ");
			System.out.println(e);
		} finally {
			session.close();
		}
		return "redirect:/e-commerce/address.htm";
	}

	@RequestMapping(value = "editAddress", method = RequestMethod.POST)
	public String editAddress(ModelMap model, @RequestParam("province") int provinceId,
			@RequestParam("district") int districtId, @RequestParam("ward") int wardId,
			@RequestParam("details") String details, @RequestParam("phone") String phone, @RequestParam("id") int id,
			HttpServletRequest request) {
		model.addAttribute("details", details);
		model.addAttribute("phone", phone);
		model.addAttribute("id", id);
		if (provinceId != 0) {
			if (districtId != 0) {
				List<Province> listPros = addressService.listProvinces();
				model.addAttribute("listPros", listPros);
				List<District> listDicts = addressService.listDistricts(provinceId);
				model.addAttribute("listDicts", listDicts);
				List<Ward> listWards = addressService.listWards(districtId);
				model.addAttribute("listWards", listWards);
				model.addAttribute("selectedProvince", provinceId);
				model.addAttribute("selectedDistrict", districtId);
				return "customer/editAddress";
			}
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			List<District> listDicts = addressService.listDistricts(provinceId);
			model.addAttribute("listDicts", listDicts);
			model.addAttribute("selectedProvince", provinceId);
			return "customer/editAddress";
		}
		return "customer/editAddress";
	}

	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String userProfile(ModelMap model, HttpServletRequest request) {
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL) == null) {
			return "redirect:/e-commerce/login.htm";
		}
		Customer customer = ((Customer) SessionUtil.getInstance().getValue(request,
				SystemConstant.Model.CUSTOMER_MODEL));
		if (customer.getCustomerProfile() != null) {
			model.addAttribute("name", customer.getCustomerProfile().getName());
			model.addAttribute("phone", customer.getCustomerProfile().getPhoneNumber());
			model.addAttribute("email", customer.getEmail());
			model.addAttribute("selectedWard",
					addressService.getWard(customer.getCustomerProfile().getAddress()).getId());
			model.addAttribute("selectedDistrict",
					addressService.getWard(customer.getCustomerProfile().getAddress()).getDistrict().getId());
			model.addAttribute("selectedProvince",
					addressService.getWard(customer.getCustomerProfile().getAddress()).getProvince().getId());
			System.out.println("name");
			System.out.println("phone");
			System.out.println("email");

			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			List<District> listDicts = addressService.listDistricts(
					addressService.getWard(customer.getCustomerProfile().getAddress()).getProvince().getId());
			model.addAttribute("listDicts", listDicts);
			List<Ward> listWards = addressService.listWards(
					addressService.getWard(customer.getCustomerProfile().getAddress()).getDistrict().getId());
			model.addAttribute("listWards", listWards);
			return "customer/profile";
		} else {
			model.addAttribute("email", customer.getEmail());
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			return "customer/profile";
		}
	}

	@RequestMapping(value = "profile", method = RequestMethod.POST)
	public String userProfile1(ModelMap model, HttpServletRequest request, @RequestParam("province") int provinceId,
			@RequestParam("district") int districtId, @RequestParam("ward") int wardId,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("email") String email) {
		model.addAttribute("name", name);
		model.addAttribute("phone", phone);
		model.addAttribute("email", email);
		if (provinceId != 0) {
			if (districtId != 0) {
				List<Province> listPros = addressService.listProvinces();
				model.addAttribute("listPros", listPros);
				List<District> listDicts = addressService.listDistricts(provinceId);
				model.addAttribute("listDicts", listDicts);
				List<Ward> listWards = addressService.listWards(districtId);
				model.addAttribute("listWards", listWards);
				model.addAttribute("selectedProvince", provinceId);
				model.addAttribute("selectedDistrict", districtId);
				return "customer/profile";
			}
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			List<District> listDicts = addressService.listDistricts(provinceId);
			model.addAttribute("listDicts", listDicts);
			model.addAttribute("selectedProvince", provinceId);
			return "customer/profile";
		}
		return "customer/profile";
	}

	@RequestMapping(value = "profile/done.htm", method = RequestMethod.POST)
	public String addProfile(ModelMap model, HttpServletRequest request, @RequestParam("ward") int wardId,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("email") String email) {
		Date sqlDate = new Date(System.currentTimeMillis());
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL) == null) {
			return "redirect:/e-commerce/login.htm";
		}
		Customer customer = ((Customer) SessionUtil.getInstance().getValue(request,
				SystemConstant.Model.CUSTOMER_MODEL));
		if (customer.getCustomerProfile() != null) {
			CustomerProfile editProfile = customer.getCustomerProfile();
			editProfile.setName(name);
			editProfile.setPhoneNumber(phone);
			editProfile.setAddress(wardId);
			editProfile.setModifiedAt(sqlDate);
			customer.setEmail(email);
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t = session.beginTransaction();
			try {
				session.merge(editProfile);
				session.merge(customer);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công! ");
				System.out.println("done");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại! ");
				System.out.println(e);
			} finally {
				session.close();
			}

		} else {
			CustomerProfile newCtmProfile = new CustomerProfile();
			newCtmProfile.setCustomer(customer);
			newCtmProfile.setName(name);
			newCtmProfile.setPhoneNumber(phone);
			newCtmProfile.setAddress(wardId);
			newCtmProfile.setCreateAt(sqlDate);
			customer.setEmail(email);
			customer.setCustomerProfile(newCtmProfile);
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t = session.beginTransaction();
			try {
				session.merge(newCtmProfile);
				session.merge(customer);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công! ");
				System.out.println("done");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại! ");
				System.out.println(e);
			} finally {
				session.close();
			}
		}
		return "redirect:/customer/profile.htm";
	}

}
