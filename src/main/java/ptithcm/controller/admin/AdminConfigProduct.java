package ptithcm.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.dto.ProductDTO;
import ptithcm.model.product.ConfigProduct;
import ptithcm.model.product.ConfigProductKey;
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;
import ptithcm.model.product.Variation;
import ptithcm.service.VariationService;
import ptithcm.service.admin.ConfigProductService;
import ptithcm.service.admin.ProductAdminService;

@Controller
@Transactional
@RequestMapping("/admin/product/")
public class AdminConfigProduct {

	@Autowired
	ProductAdminService productService;

	@Autowired
	VariationService variationService;

	@Autowired
	ConfigProductService configProductService;

	@RequestMapping(value = "product-config/list", method = RequestMethod.GET)
	public String handleGetListConfigProduct(ModelMap modelMap, @RequestParam(defaultValue = "5") int limit,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String search) {
		List<Product> listProducts = productService.getListPaginated(limit * (page - 1), limit, search);
		List<ProductDTO> productDTOs = new ArrayList<>();

		for (Product product : listProducts) {
			ProductDTO productDTO = new ProductDTO(product, null);
			productDTOs.add(productDTO);
		}

		modelMap.addAttribute("search", search);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("limit", limit);
		modelMap.addAttribute("listProduct", productDTOs);
		return "product/config-product/listConfigProduct";
	}

	@RequestMapping(value = "product-config/{id}", method = RequestMethod.GET)
	public String handleGetListConfigProductItem(ModelMap modelMap, @PathVariable int id) {

		List<ProductItem> listProductItems = productService.getListProductItemByProductId(id);
		Product product = productService.getProductById(id);
		ProductDTO productDTO = new ProductDTO(product, null);

		modelMap.addAttribute("listProductItem", listProductItems);
		modelMap.addAttribute("product", productDTO);

		return "product/config-product/listProductItem";
	}

	@RequestMapping(value = "product-config/{productId}/edit/{productItemId}", method = RequestMethod.GET)
	public String handleGetConfigProductItemI(ModelMap modelMap, @PathVariable int productId,
			@PathVariable int productItemId) {

		ProductItem productItem = productService.getProductItemById(productItemId);
		Product product = productService.getProductById(productId);

		List<Variation> listVariations = variationService
				.getListVariationByCategoryId(product.getProductCategory().getId());
		List<ConfigProduct> listConfigProducts = configProductService.getConfigProductsByProductItemId(productItemId);

		modelMap.addAttribute("listConfigProduct", listConfigProducts);
		modelMap.addAttribute("listVariations", listVariations);
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("productItem", productItem);
		return "product/config-product/formConfigProduct";
	}

	@RequestMapping(value = "product-config/{productId}/edit/{productItemId}", method = RequestMethod.POST)
	public String handlePostConfigProductItem(ModelMap modelMap, @PathVariable int productId,
			@PathVariable int productItemId, @RequestParam("variationConfigId") List<Integer> variationConfigIds,
			@RequestParam("variationConfigValue") List<String> variationConfigValues) {

		ProductItem productItem = productService.getProductItemById(productItemId);

		// Delete existing config products
		configProductService.deleteConfigProductsByProductItemId(productItemId);

		// Create new config products from the submitted form data
		for (int i = 0; i < variationConfigIds.size(); i++) {
			Variation variation = variationService.getVariationById(variationConfigIds.get(i));
			String value = variationConfigValues.get(i);

			ConfigProduct configProduct = new ConfigProduct();
			ConfigProductKey configProductKey = new ConfigProductKey();
			configProductKey.setProductItem(productItem);
			configProductKey.setVariation(variation);
			configProduct.setId(configProductKey);
			configProduct.setValue(value);

			configProductService.addConfigProduct(configProduct);
		}

		// Redirect to the appropriate page after the update
		return "redirect:/admin/product/product-config/" + productId + "/edit/" + productItemId + ".htm";
	}

}
