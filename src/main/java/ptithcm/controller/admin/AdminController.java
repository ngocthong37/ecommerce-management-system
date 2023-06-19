package ptithcm.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.constant.SystemConstant;
import ptithcm.model.user.User;
import ptithcm.service.ProductService;
import ptithcm.service.UserService;
import ptithcm.service.admin.LoginService;
import ptithcm.util.SessionUtil;

@Transactional
@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	LoginService service;

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showAdminLogin() {
		return "admin/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String handleAdminLoginRequest(@RequestParam String username, @RequestParam String password, ModelMap model,
			HttpServletRequest request) {
		User user = userService.findByUserNameAndPasswordAndStatus(username, password, true);
		if (user != null) {
			SessionUtil.getInstance().putValue(request, SystemConstant.Model.USER_MODEL, user);
			SessionUtil.getInstance().removeValue(request,SystemConstant.Model.CUSTOMER_MODEL);
			if (user.getUserPermission().getValue().equals(SystemConstant.Authorization.SHIPPER)) {
				return "redirect:/delivery/listDeliveryOrder.htm";
			}
			return "redirect:/admin/product/list.htm";
		}
		model.addAttribute("error", "Thông tin đăng nhập không đúng");
		return "admin/login";
	}
}
