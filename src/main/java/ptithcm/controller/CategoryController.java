package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.dto.ProductCategoryDTO;
import ptithcm.model.product.ProductCategory;
import ptithcm.service.CategoryService;

@Controller
@RequestMapping(value = "/admin/product/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// Controller new product category
	@RequestMapping(value = "category/new", method = RequestMethod.GET)
	public String showFormNewCategory(ModelMap modelMap) {
		List<ProductCategory> allCategories = categoryService.getAllCategory();
		modelMap.addAttribute("listCategory", allCategories);
		return "product/category/newCategory";
	}

	@RequestMapping(value = "category/new", method = RequestMethod.POST)
	public String handleAddNewCategoryRequest(@RequestParam String categoryName, @RequestParam String parentCategoryId,
			ModelMap model, HttpServletRequest request) {
		ProductCategory newCategory = new ProductCategory();
		newCategory.setCategoryName(categoryName);
		List<ProductCategory> allCategories = categoryService.getAllCategory();
		try {
			for (int i = 0; i < allCategories.size(); i++) {
				if (allCategories.get(i).getId().equals(Integer.parseInt(parentCategoryId))) {
					newCategory.setParentCategoryId(Integer.parseInt(parentCategoryId));
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Convert loi!!");
		}
		if (newCategory.getCategoryName().trim().isEmpty()) {
			return "redirect:/admin/product/category/list.htm";
		}
		categoryService.insertCategory(newCategory);

		return "redirect:/admin/product/category/list.htm";
	}

	// Controller list product category
	@RequestMapping(value = "category/list", method = RequestMethod.GET)
	public String handleGetListCategory(ModelMap modelMap, @RequestParam(defaultValue = "5") int limit,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String search) {
		List<ProductCategory> listCategories = categoryService.getListPaginatedCategories(limit * (page - 1), limit,
				search);
		List<ProductCategoryDTO> listCategoryDTOs = new ArrayList<ProductCategoryDTO>();
		for (int i = 0; i < listCategories.size(); i++) {
			ProductCategoryDTO categoryDTO = new ProductCategoryDTO(categoryService, listCategories.get(i));
			listCategoryDTOs.add(categoryDTO);
		}
		modelMap.addAttribute("search", search);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("limit", limit);
		modelMap.addAttribute("listCategory", listCategoryDTOs);
		return "product/category/listCategory";
	}

	// Delete product category
	@RequestMapping(value = "category/delete/{id}")
	public String deleteProductCategoryById(@PathVariable int id) {
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/product/category/list.htm";
	}

	// Edit and view detail product category
	@RequestMapping(value = "category/edit/{id}", method = RequestMethod.GET)
	public String editProductCategoryById(@PathVariable int id, ModelMap modelMap) {
		ProductCategory productCategory = categoryService.getProductCategoryById(id);
		List<ProductCategory> allCategories = categoryService.getAllCategory();
		modelMap.addAttribute("listCategory", allCategories);
		modelMap.addAttribute("currentCategory", productCategory);
		return "product/category/editCategory";
	}

	@RequestMapping(value = "category/edit/{id}", method = RequestMethod.POST)
	public String handleEditProductCategoryById(@RequestParam String categoryName,
			@RequestParam String parentCategoryId, @PathVariable int id) {

		ProductCategory productCategory = categoryService.getProductCategoryById(id);
		productCategory.setCategoryName(categoryName);
		try {
			for (ProductCategory category : categoryService.getAllCategory()) {
				if (category.getId().equals(Integer.parseInt(parentCategoryId))) {
					productCategory.setParentCategoryId(Integer.parseInt(parentCategoryId));
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Convert loi!!");
		}

		// Save the updated product category
		categoryService.updateCategory(productCategory);

		return "redirect:/admin/product/category/list.htm";
	}
}
