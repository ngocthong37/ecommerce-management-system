package ptithcm.controller.cart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.constant.SystemConstant;
import ptithcm.model.customer.Customer;
import ptithcm.model.customer.CustomerAddress;
import ptithcm.model.pay.CustomerPaymentMethod;
import ptithcm.model.ship.ShippingMethod;
import ptithcm.service.AddressService;
import ptithcm.service.CartService;
import ptithcm.service.PaymentService;
import ptithcm.service.ProductService;
import ptithcm.util.SessionUtil;

@RequestMapping("/e-commerce/")
@Controller
@Transactional
public class AddressController {

	@Autowired
	AddressService addressService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	SessionFactory sessionFactory;

	@RequestMapping(value = "address")
	public String showAddress(ModelMap model, HttpServletRequest request) {
		int id = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
				.getId();
		List<CustomerAddress> addressList = addressService.getAddressListByID(id);
		model.addAttribute("customerAddress", addressList);
		return "e-commerce/address";
	}

	@RequestMapping(value = "address/delete/{id}")
	public String deleteAddress(@PathVariable int id) {
		int addressId = addressService.getAddressById(id).getAddress().getId();
		addressService.deleteCustomerAddress(id);
		addressService.deleteAddress(addressId);
		return "redirect:/e-commerce/address.htm";
	}

	@RequestMapping(value = "address/deliver/{id}")
	public String deliver(@PathVariable int id, ModelMap model, HttpServletRequest request, HttpSession session) {
		int ctmid = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
				.getId();
		System.out.println(id);
		session.setAttribute("addressId", id);
		List<CustomerPaymentMethod> payment = paymentService.getPaymentListById(ctmid);
		model.addAttribute("payment", payment);
		List<ShippingMethod> shipping = paymentService.getListShippingMethods();
		model.addAttribute("shipping", shipping);
		return "e-commerce/payment";
	}

}
