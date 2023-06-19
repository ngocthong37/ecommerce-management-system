package ptithcm.controller.promotion;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.constant.SystemConstant;
import ptithcm.model.product.ProductCategory;
import ptithcm.model.promotion.Promotion;
import ptithcm.model.promotion.PromotionCategory;
import ptithcm.model.user.User;
import ptithcm.service.ProductCategoryService;
import ptithcm.service.ProductService;
import ptithcm.service.admin.PromotionService;
import ptithcm.util.SessionUtil;

@Controller
@RequestMapping(value = "/admin/product/promotion/")
public class PromotionController {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ProductCategoryService productCategoryService;

	@Autowired
	ProductService productService;

	@Autowired
	PromotionService promotionService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String showPromotion(ModelMap model, HttpServletRequest request) {
		List<ProductCategory> listCategories = productCategoryService.getAllProductCategory();
		model.addAttribute("categories", listCategories);
		return "product/promotion/createPromotion";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String createPromotion(HttpServletRequest request, ModelMap model,
			@RequestParam("promotion-name") String promotionName,
			@RequestParam("promotion-description") String promotionDescription, @RequestParam("brand") Integer cateId,
			@RequestParam("discount-percentage") int discountPercentage,
			@RequestParam("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		String isAdmin = "";
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.USER_MODEL) != null) {
			isAdmin = (String) ((User) SessionUtil.getInstance().getValue(request,
					SystemConstant.Model.USER_MODEL)).getUserPermission().getValue();
		}
		if (isAdmin.equals(SystemConstant.Authorization.ADMIN)) {
			return "redirect:/admin/product/promotion/list.htm";
		}

		LocalDate currentDate = LocalDate.now();
		User user = (User) SessionUtil.getInstance().getValue(request, SystemConstant.Model.USER_MODEL);
		Promotion promotion = new Promotion(promotionName, promotionDescription, discountPercentage,
				Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(currentDate), user);

		ProductCategory productCategory = productCategoryService.getProductCategory(cateId);
		PromotionCategory promotionCategory = new PromotionCategory();
		promotionCategory.setProductCategory(productCategory);
		promotionCategory.setPromotion(promotion);
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(promotion);
			session.merge(promotionCategory);
			tx.commit();
			System.out.println("Them khuyen mai Thanh cong");
			model.addAttribute("message", "Success");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
				model.addAttribute("message", "Fail");
				System.out.println("That bai");
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return "redirect:/admin/product/promotion/list.htm";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String listPromotion(ModelMap model, HttpServletRequest request) {
		System.out.println("I came here");
		List<Promotion> listPromotions = promotionService.getAllPromotions();
		String isAdmin = "";
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.USER_MODEL) != null) {
			isAdmin = (String) ((User) SessionUtil.getInstance().getValue(request,
					SystemConstant.Model.USER_MODEL)).getUserPermission().getValue();
		}
		if (isAdmin.equals(SystemConstant.Authorization.ADMIN)) {
			model.addAttribute("isAdmin", true);
		}
		else {
			model.addAttribute("isAdmin", false);
		}
		model.addAttribute("listPromotions", listPromotions);
		return "product/promotion/listPromotion";
	}

	@RequestMapping(value = "edit/{promotionId}", method = RequestMethod.GET)
	public String editPromotion(ModelMap model, @PathVariable Integer promotionId, HttpServletRequest request) {
		Promotion promotion = promotionService.getPromotionById(promotionId);
		Integer cateId = promotionService.getProductCategoryId(promotionId) - 1;
		List<ProductCategory> listCategories = productCategoryService.getAllProductCategory();
		model.addAttribute("selectedItem", cateId);
		model.addAttribute("categories", listCategories);
		model.addAttribute("promotion", promotion);
		return "product/promotion/editPromotion";
	}

	@RequestMapping(value = "edit/{promotionId}", method = RequestMethod.POST)
	public String updatePromotion(HttpServletRequest request, @PathVariable Integer promotionId,
			@RequestParam("promotion-name") String promotionName,
			@RequestParam("promotion-description") String promotionDescription, @RequestParam("brand") Integer cateId,
			@RequestParam("discount-percentage") int discountPercentage,
			@RequestParam("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		Promotion promotion = promotionService.getPromotionById(promotionId);
		LocalDate currentDate = LocalDate.now();
		User user = (User) SessionUtil.getInstance().getValue(request, SystemConstant.Model.USER_MODEL);
		promotion.setName(promotionName);
		promotion.setDescription(promotionDescription);
		promotion.setDiscountRate(discountPercentage);
		promotion.setStartDate(Date.valueOf(startDate));
		promotion.setEndDate(Date.valueOf(endDate));
		promotion.setUser(user);
		promotion.setCreateAt(Date.valueOf(currentDate));
		// update promotion category
		Integer oldCateId = promotionService.getProductCategoryId(promotionId);
		if (cateId == null) {
			cateId = oldCateId;
		}
		PromotionCategory promotionCategory = promotionService.getPromotionCategoryById(promotionId);
		promotionCategory.setProductCategory(productCategoryService.getProductCategory(cateId));
		promotionCategory.setPromotion(promotion);
		System.out.println("ID: " + promotionCategory.getProductCategory().getId());
		System.out.println("Hãng đang được chỉnh sửa: " + promotionCategory.getProductCategory().getCategoryName());
		promotionService.updatePromotion(promotion);
		promotionService.updatePromotionCategory(promotionCategory);
		return "redirect:/admin/product/promotion/list.htm";
	}

}
