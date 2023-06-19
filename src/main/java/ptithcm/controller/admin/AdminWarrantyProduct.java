package ptithcm.controller.admin;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
@RequestMapping("/admin/product/warranty/")
public class AdminWarrantyProduct {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getListWarranty(ModelMap modelMap) {
		return "product/warranty/warrantyList";
	}
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String getFormNewWarranty(ModelMap modelMap) {
		return "product/warranty/newWarranty";
	}
}
