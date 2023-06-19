package ptithcm.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.constant.SystemConstant;
import ptithcm.model.customer.Customer;
import ptithcm.model.pay.CustomerPaymentMethod;
import ptithcm.service.CustomerService;
import ptithcm.service.PaymentService;
import ptithcm.util.SessionUtil;

@Transactional
@Controller
@RequestMapping(value = "/e-commerce/")
public class EcommerceController {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	PaymentService paymentService;

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLogin() {
		return "e-commerce/login";
	}

	@RequestMapping(value = "signIn", method = RequestMethod.GET)
	public String showSignIn() {
		return "e-commerce/signIn";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String handleCustomerLoginRequest(@RequestParam String username, @RequestParam String password,
			HttpServletRequest request,ModelMap model) {
		Customer customer = customerService.getCustomerByUsernamePassword(username, password);
		if (customer != null) {
			SessionUtil.getInstance().putValue(request, SystemConstant.Model.CUSTOMER_MODEL, customer);
			SessionUtil.getInstance().removeValue(request,SystemConstant.Model.USER_MODEL);
			System.out.println(customer.getUserName());
			return "redirect:/e-commerce/shop.htm";
		}
		model.addAttribute("error", "Thông tin đăng nhập không đúng");
		return "e-commerce/login";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam Integer productId) {
		return "redirect:/";
	}

	@RequestMapping(value = "signIn", method = RequestMethod.POST)
	public String newCustomer(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("password1") String password1, ModelMap model) {
		if (password.equals(password1)) {
			Customer newCustomer = new Customer();
			newCustomer.setUserName(username);
			newCustomer.setPassword(password);
			newCustomer.setEmail(email);
			System.out.println(newCustomer.getUserName());
			System.out.println(newCustomer.getPassword());
			System.out.println(newCustomer.getEmail());

			Session session = sessionFactory.openSession();
			org.hibernate.Transaction t = session.beginTransaction();
			try {
				session.save(newCustomer);
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
			CustomerPaymentMethod newCPM = new CustomerPaymentMethod();
			newCPM.setCustomer(newCustomer);
			newCPM.setPaymentType(paymentService.gePaymentTypeById(1));
			newCPM.setExpiry(Date.valueOf("2123-01-01"));
			Session session1 = sessionFactory.openSession();
			org.hibernate.Transaction tr = session1.beginTransaction();
			try {
				session1.save(newCPM);
				tr.commit();
				model.addAttribute("message", "Thêm mới thành công! ");
				System.out.println("done");
			} catch (Exception e) {
				tr.rollback();
				model.addAttribute("message", "Thêm mới thất bại! ");
				System.out.println(e);
			} finally {
				session1.close();
			}
			return "redirect:/e-commerce/login.htm";
		}
		model.addAttribute("passwordError", "Mật khẩu không khớp");
		return "e-commerce/signIn";
	}

	@RequestMapping(value = "logOut",method = RequestMethod.GET)
	public String logOut(HttpServletRequest request) {
		SessionUtil.getInstance().removeValue(request,SystemConstant.Model.USER_MODEL);
		SessionUtil.getInstance().removeValue(request,SystemConstant.Model.CUSTOMER_MODEL);
		return "redirect:/e-commerce/login.htm";
	}
}
