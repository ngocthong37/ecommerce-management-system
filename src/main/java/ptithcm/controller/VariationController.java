package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.dto.ProductVariationDTO;
import ptithcm.model.product.ProductCategory;
import ptithcm.model.product.Variation;
import ptithcm.service.CategoryService;
import ptithcm.service.VariationService;

@Controller
@RequestMapping(value = "admin/product/")
public class VariationController {

	@Autowired
	VariationService variationService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "variation/new", method = RequestMethod.GET)
	public String showFormNewVariation(ModelMap modelMap) {
		List<ProductCategory> allCategories = categoryService.getAllCategory();
		modelMap.addAttribute("listCategory", allCategories);
		return "product/variation/newVariation";
	}

	@RequestMapping(value = "variation/new", method = RequestMethod.POST)
	public String handleNewVariation(@RequestParam String variationName, @RequestParam String categoryId) {
		if (categoryService.checkCategoryExist(Integer.parseInt(categoryId)) && !variationName.trim().isEmpty()) {
			Variation variation = new Variation();
			ProductCategory productCategory = categoryService.getProductCategoryById(Integer.parseInt(categoryId));
			variation.setName(variationName);
			variation.setCategory(productCategory);
			variationService.insertVariation(variation);
		}
		return "redirect:/admin/product/variation/list.htm";
	}

	@RequestMapping(value = "variation/list", method = RequestMethod.GET)
	public String showListVariation(ModelMap modelMap, @RequestParam(defaultValue = "5") int limit,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String search) {
		List<Variation> listVariations = variationService.getListPaginatedVariations(limit * (page - 1), limit, search);
		List<ProductVariationDTO> listVariationDTOs = new ArrayList<ProductVariationDTO>();
		for (int i = 0; i < listVariations.size(); i++) {
			ProductVariationDTO productVariationDTO = new ProductVariationDTO(listVariations.get(i));
			listVariationDTOs.add(productVariationDTO);
		}
		modelMap.addAttribute("search", search);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("limit", limit);
		modelMap.addAttribute("listVariations", listVariationDTOs);
		return "product/variation/listVariation";
	}

	@RequestMapping(value = "variation/delete/{id}")
	public String deleteVariationById(@PathVariable int id) {
		variationService.deleteVariationById(id);
		return "redirect:/admin/product/variation/list.htm";
	}

	@RequestMapping(value = "variation/edit/{id}", method = RequestMethod.GET)
	public String editVariationById(@PathVariable int id, ModelMap modelMap) {
		Variation variation = variationService.getVariationById(id);
		ProductVariationDTO productVariationDTO = new ProductVariationDTO(variation);
		List<ProductCategory> allCategories = categoryService.getAllCategory();
		modelMap.addAttribute("listCategory", allCategories);
		modelMap.addAttribute("currentVariation", productVariationDTO);
		return "product/variation/editVariation";
	}

	@RequestMapping(value = "variation/edit/{id}", method = RequestMethod.POST)
	public String handleEditVariationById(@RequestParam String variationName, @RequestParam String categoryId,
			@RequestParam String variationId) {
		Variation variation = variationService.getVariationById(Integer.parseInt(variationId));
		ProductCategory productCategory = categoryService.getProductCategoryById(Integer.parseInt(categoryId));
		variation.setName(variationName);
		variation.setCategory(productCategory);
		variationService.updateVariation(variation);
		return "redirect:/admin/product/variation/list.htm";
	}
}
