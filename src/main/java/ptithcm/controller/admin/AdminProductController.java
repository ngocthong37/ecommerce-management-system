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
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductCategory;
import ptithcm.model.product.ProductItem;
import ptithcm.model.product.Variation;
import ptithcm.service.CategoryService;
import ptithcm.service.VariationService;
import ptithcm.service.admin.ProductAdminService;

@Controller
@Transactional
@RequestMapping("/admin/product/")
public class AdminProductController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductAdminService productService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ServletContext context;

	@Autowired
	VariationService variationService;

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String showFormCreateNewProduct(ModelMap modelMap) {
		List<ProductCategory> allCategories = categoryService.getAllCategory();
		modelMap.addAttribute("listCategory", allCategories);
		return "product/manage-product/createNewProduct";
	}

	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String handleSubmitFormCreateProduct(
			@RequestParam(value = "productName", required = true) String productName,
			@RequestParam(value = "categoryId", required = true) String categoryId,
			@RequestParam(value = "description", required = true) String productDescription,
			@RequestParam(value = "thumbnail", required = true) MultipartFile thumbnail,
			@RequestParam("SKU") List<String> SKU, @RequestParam("qty_in_stock") List<Integer> qtyInStock,
			@RequestParam("product_price") List<Integer> productPrice,
			@RequestParam("warranty_time") List<Integer> warrantyTime,
			@RequestParam("product_image") List<MultipartFile> productImages, HttpServletRequest request)
			throws IOException {

		// Save thumbnail
		String thumbnailFileName = thumbnail.getOriginalFilename();
		String thumbnailFilePath = context.getRealPath("/uploads/") + thumbnailFileName;
		File thumbnailDest = new File(thumbnailFilePath);
		thumbnail.transferTo(thumbnailDest);
		String thumbnailUrl = request.getContextPath() + "/uploads/" + thumbnailFileName;

		System.out.println("thumbnailUrl: " + thumbnailUrl);

		// Create product object
		ProductCategory productCategory = categoryService.getProductCategoryById(Integer.parseInt(categoryId));
		Product product = new Product(productCategory, productName, productDescription, thumbnailUrl);

		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(product);
			session.getTransaction().commit();
			System.out.println("Insert product success!");
		} catch (Exception e) {
			System.out.println(e);
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		// Save product images and create list of ProductItem objects
		List<ProductItem> listProductItems = new ArrayList<ProductItem>();
		for (int i = 0; i < SKU.size(); i++) {
			String fileName = productImages.get(i).getOriginalFilename();
			String filePath = context.getRealPath("/WEB-INF/uploads/") + fileName;
			File dest = new File(filePath);
			productImages.get(i).transferTo(dest);
			String imageUrl = request.getContextPath() + "/uploads/" + fileName;
			System.out.println("imageUrl: " + imageUrl);
			System.out.println("file: " + dest.getName() + " " + dest.getPath());
			ProductItem item = new ProductItem(product, SKU.get(i), qtyInStock.get(i), imageUrl, productPrice.get(i),
					warrantyTime.get(i), SystemConstant.ProductStatus.ACTIVE);
			listProductItems.add(item);
		}

		productService.addNewProductItem(product, listProductItems);

		return "redirect:/admin/product/list.htm";
	}

	// Controller list product category
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String handleGetListCategory(ModelMap modelMap, @RequestParam(defaultValue = "5") int limit,
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
		return "product/manage-product/listProduct";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String showEditProduct(ModelMap modelMap, @PathVariable int id) {
		List<ProductCategory> allCategories = categoryService.getAllCategory();
		List<ProductItem> listProductItems = productService.getListProductItemByProductId(id);
		Product product = productService.getProductById(id);
		ProductDTO productDTO = new ProductDTO(product, null);

		modelMap.addAttribute("listProductItem", listProductItems);
		modelMap.addAttribute("listCategory", allCategories);
		modelMap.addAttribute("product", productDTO);
		return "product/manage-product/editProduct";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	public String handleEditProductAndProducItem(@RequestParam(value = "productId", required = true) Integer productId,
			@RequestParam(value = "productName", required = true) String productName,
			@RequestParam(value = "categoryId", required = true) String categoryId,
			@RequestParam(value = "description", required = true) String productDescription,
			@RequestParam(value = "thumbnail", required = true) MultipartFile thumbnail,
			@RequestParam("productItemId") List<Integer> productItemId, @RequestParam("SKU") List<String> SKU,
			@RequestParam("qty_in_stock") List<Integer> qtyInStock,
			@RequestParam("product_price") List<Integer> productPrice,
			@RequestParam("warranty_time") List<Integer> warrantyTime,
			@RequestParam("product_image") List<MultipartFile> productImages, HttpServletRequest request)
			throws IllegalStateException, IOException {

		Product existingProduct = productService.getProductById(productId);
		ProductCategory productCategory = categoryService.getProductCategoryById(Integer.parseInt(categoryId));
		if (existingProduct == null) {
			// Handle the case where the product is not found
			// Redirect to an appropriate error page or display a message
			return "redirect:/admin/product/list.htm";
		}
		String thumbnailUrl = existingProduct.getProductImage();
		if (!thumbnail.isEmpty()) {
			// Save thumbnail
			String thumbnailFileName = generateUniqueFileName(thumbnail.getOriginalFilename());
			String thumbnailFilePath = context.getRealPath("/uploads/") + thumbnailFileName;
			File thumbnailDest = new File(thumbnailFilePath);
			thumbnail.transferTo(thumbnailDest);
			thumbnailUrl = request.getContextPath() + "/uploads/" + thumbnailFileName;
		}
		// Update field existing product
		existingProduct.setName(productName);
		existingProduct.setProductCategory(productCategory);
		existingProduct.setDescription(productDescription);
		existingProduct.setProductImage(thumbnailUrl);

		// Update product
		productService.updateProduct(existingProduct);

		// Update existing product items
		for (int i = 0; i < productItemId.size(); i++) {
			int itemId = productItemId.get(i);
			ProductItem existingItem = productService.getProductItemById(itemId);
			if (existingItem == null) {
				// Handle the case where the product item is not found
				// Redirect to an appropriate error page or display a message
				continue;
			}

			// Update the existing product item
			existingItem.setSKU(SKU.get(i));
			existingItem.setQuantityInStock(qtyInStock.get(i));
			existingItem.setPrice(productPrice.get(i));
			existingItem.setWarrantyTime(warrantyTime.get(i));
			// Update other properties as needed

			// Check if a new product image is provided
			MultipartFile productImage = productImages.get(i);
			if (productImage != null && !productImage.isEmpty()) {
				// Save the new product image
				String fileName = productImage.getOriginalFilename();
				String filePath = context.getRealPath("/uploads/") + fileName;
				File dest = new File(filePath);
				productImage.transferTo(dest);
				String imageUrl = request.getContextPath() + "/uploads/" + fileName;

				// Update the product image URL
				existingItem.setProductImage(imageUrl);
			}

			// Update the product item
			productService.updateProductItem(existingItem);
		}

		// Save product images and create list of ProductItem objects
		List<ProductItem> listProductItems = new ArrayList<ProductItem>();
		for (int i = productItemId.size(); i < SKU.size(); i++) {
			MultipartFile productImage = productImages.get(i);
			String fileName = generateUniqueFileName(productImage.getOriginalFilename());
			String filePath = context.getRealPath("/uploads/") + fileName;
			File dest = new File(filePath);
			productImage.transferTo(dest);
			String imageUrl = request.getContextPath() + "/uploads/" + fileName;
			ProductItem item = new ProductItem(existingProduct, SKU.get(i), qtyInStock.get(i), imageUrl,
					productPrice.get(i), warrantyTime.get(i), SystemConstant.ProductStatus.ACTIVE);
			listProductItems.add(item);
		}

		// Update product items
		productService.addNewProductItem(existingProduct, listProductItems);
		return "redirect:/admin/product/list.htm";

	}

	@RequestMapping(value = "delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		Product product = productService.getProductById(id);
		productService.deleteProduct(product);
		return "redirect:/admin/product/list.htm";
	}

	private String generateUniqueFileName(String originalFilename) {
		String baseName = FilenameUtils.getBaseName(originalFilename);
		String extension = FilenameUtils.getExtension(originalFilename);
		String uniqueFileName = baseName + "_" + System.currentTimeMillis() + "." + extension;
		return uniqueFileName;
	}

	@RequestMapping(value = "{productId}/delete/product-item/{id}")
	public String deleteProductItem(@PathVariable int id, @PathVariable int productId) {
		System.out.print(id);
		productService.deleteProductItemById(id);
		return "redirect:/admin/product/edit/" + productId + ".htm";
	}

}
