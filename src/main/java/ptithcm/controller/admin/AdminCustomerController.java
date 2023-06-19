package ptithcm.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.constant.SystemConstant;
import ptithcm.dto.ProductDTO;
import ptithcm.model.address.District;
import ptithcm.model.address.Province;
import ptithcm.model.address.Ward;
import ptithcm.model.customer.Customer;
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductCategory;
import ptithcm.model.product.ProductItem;
import ptithcm.model.product.Variation;
import ptithcm.service.AddressService;
import ptithcm.service.CategoryService;
import ptithcm.service.CustomerService;
import ptithcm.service.VariationService;
import ptithcm.service.admin.AdminCustomerService;
import ptithcm.service.admin.ProductAdminService;

@Controller
@Transactional
@RequestMapping("/admin/customer/")
public class AdminCustomerController {

	@Autowired
	AdminCustomerService adminCustomerService;

	@Autowired
	CustomerService customerService;

	@Autowired
	AddressService addressService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String showListCustomer(ModelMap modelMap) {
		List<Customer> listCustomers = adminCustomerService.getAllCustomer();
		modelMap.addAttribute("listCustomers", listCustomers);
		return "admin/customerManage";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String showCustomer(ModelMap model, @PathVariable int id) {
		Customer thisCustomer = customerService.getCustomerById(id);
		model.addAttribute("test","aaa");
		if (thisCustomer.getCustomerProfile() != null) {
			model.addAttribute("name", thisCustomer.getCustomerProfile().getName());
			model.addAttribute("phone", thisCustomer.getCustomerProfile().getPhoneNumber());
			model.addAttribute("email", thisCustomer.getEmail());
			model.addAttribute("selectedWard",
					addressService.getWard(thisCustomer.getCustomerProfile().getAddress()).getId());
			model.addAttribute("selectedDistrict",
					addressService.getWard(thisCustomer.getCustomerProfile().getAddress()).getDistrict().getId());
			model.addAttribute("selectedProvince",
					addressService.getWard(thisCustomer.getCustomerProfile().getAddress()).getProvince().getId());
			List<Province> listPros = addressService.listProvinces();
			model.addAttribute("listPros", listPros);
			List<District> listDicts = addressService.listDistricts(
					addressService.getWard(thisCustomer.getCustomerProfile().getAddress()).getProvince().getId());
			model.addAttribute("listDicts", listDicts);
			List<Ward> listWards = addressService.listWards(
					addressService.getWard(thisCustomer.getCustomerProfile().getAddress()).getDistrict().getId());
			model.addAttribute("listWards", listWards);
			return "customer/profile";
		} else {
			model.addAttribute("email", thisCustomer.getEmail());
			return "customer/profile";

		}

	}
}
