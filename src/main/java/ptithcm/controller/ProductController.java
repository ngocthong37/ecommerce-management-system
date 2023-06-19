package ptithcm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.logging.log4j.core.appender.rolling.action.IfFileName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.constant.SystemConstant;
import ptithcm.dto.ConfigProductDTO;
import ptithcm.dto.ProductDTO;
import ptithcm.model.customer.Customer;
import ptithcm.model.customer.CustomerReview;
import ptithcm.model.order.OrderLine;
import ptithcm.model.product.ConfigProduct;
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;
import ptithcm.model.promotion.Promotion;
import ptithcm.model.shoppingCart.ShoppingCart;
import ptithcm.model.shoppingCart.ShoppingCartItem;
import ptithcm.service.CustomerService;
import ptithcm.service.ProductService;
import ptithcm.service.ShoppingCartService;
import ptithcm.service.admin.ConfigProductService;
import ptithcm.service.admin.PromotionService;
import ptithcm.util.SessionUtil;

@Transactional
@Controller
@RequestMapping("/e-commerce/")
public class ProductController {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ProductService productService;
	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	CustomerService customerService;

	@Autowired
	PromotionService promotionService;

	@Autowired
	ConfigProductService configProductService;

	@RequestMapping("shop")
	public String shop(ModelMap model, HttpServletRequest request) {
		List<Product> listProducts = productService.getListProducts();
		List<ProductDTO> listProductDTOs = new ArrayList<ProductDTO>();

		for (int i = 0; i < listProducts.size(); i++) {
			ProductDTO productDTO = new ProductDTO(listProducts.get(i), promotionService);
			listProductDTOs.add(productDTO);
		}
		productService.checkPromotion();
		model.addAttribute("listProduct", listProductDTOs);
		return "e-commerce/shop";
	}

	@RequestMapping(value = "product/{productId}/detail/{productItemId}", method = RequestMethod.GET)
	public String product(HttpServletRequest request, ModelMap model, @PathVariable("productId") int productId,
			@PathVariable("productItemId") int productItemId) {
		int id = 0;
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL) != null) {
			id = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
					.getId();
		}

		List<ProductItem> listProductItem = productService.getProductItemByProductId(productId);
		Product product = productService.getProductById(productId);
		ProductItem productItem = productService.getProductItemById(productItemId);
		List<ConfigProductDTO> listConfigProductDTOs = new ArrayList<>();

		for (int i = 0; i < listProductItem.size(); i++) {
			List<ConfigProduct> configProductItem = configProductService
					.getConfigProductsByProductItemId(listProductItem.get(i).getId());
			ConfigProductDTO configProductDTO = new ConfigProductDTO(configProductItem, listProductItem.get(i).getId());
			listConfigProductDTOs.add(configProductDTO);
		}
		Boolean onSale = false;
		if (productItem.getStatus().equals(SystemConstant.ProductStatus.ON_SALE)) {
			System.out.println("Price: " + productItem.getPrice());
			long salePrice = promotionService.getPriceDiscount(productItemId, productItem.getPrice());
			System.out.println("sale price: " + salePrice);
			model.addAttribute("salePrice", salePrice);
			onSale = true;
		}
		model.addAttribute("onSale", onSale);

		int quantityOrdered = 0;
		int cartId = 0;
		if (id > 0) {
			Customer customer = customerService.getCustomerById(id);
			if (shoppingCartService.checkExistCartId(id) == 0) {
				System.out.println("Them gio hang cho khach hang");
				System.out.println("Customer id: " + id);
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart.setCustomer(customer);
				Session session = sessionFactory.openSession();
				Transaction t = session.beginTransaction();
				try {
					session.save(shoppingCart);
					t.commit();
					model.addAttribute("message", "Thêm mới thành công! ");
				} catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Thêm mới thất bại! ");
				} finally {
					session.close();
				}

			} else {
				cartId = shoppingCartService.checkExistCartId(id);
			}
			if (cartId > 0) {
				quantityOrdered = shoppingCartService.getTotalQuantityOrdered(id);
				model.addAttribute("quantityOrdered", quantityOrdered);
			}
		}
		OrderLine orderLine = productService.isBoughtThisProduct(id, productItemId);
		boolean isBought;
		if (orderLine == null) {
			isBought = false;
		} else {
			isBought = true;
		}

		System.out.println("is bought: " + isBought);
		model.addAttribute("isBought", isBought);

		List<CustomerReview> comments = productService.getAllCommentsById(productItemId);
		if (comments != null) {
			Collections.reverse(comments);
			boolean isReview = productService.isReviewed(id, productItemId, comments);
			model.addAttribute("isReview", isReview);
			for (CustomerReview customerReview : comments) {
				model.addAttribute("ratingValue", customerReview.getRatingValue());
			}
			model.addAttribute("comments", comments);
		}

		model.addAttribute("listConfigProduct", listConfigProductDTOs);
		model.addAttribute("quantityOrdered", quantityOrdered);
		model.addAttribute("product", product);
		model.addAttribute("currentProductItem", productItem);
		model.addAttribute("listProductItem", listProductItem);
		return "e-commerce/product";
	}

	@RequestMapping(value = "product/{productId}/detail/{productItemId}", method = RequestMethod.POST, params = "addToCart")
	public String addToCart(ModelMap model, @PathVariable("productId") int productId, HttpServletRequest request,
			@PathVariable("productItemId") int productItemId) {
		int id = 0;

		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL) != null) {
			id = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
					.getId();
		}
		int quantityOrdered = 0;
		if (id > 0) {
			ProductItem productItem = productService.getProductItemById(productItemId);
			Integer quantity = Integer.valueOf(request.getParameter("quantity"));
			int cartId = shoppingCartService.checkExistCartId(id);
			int bonusQuantity = shoppingCartService.getQuantityOfProductAdded(productItemId, id);
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
			shoppingCartItem.setProductItem(productItem);
			productService.addToCart(shoppingCartItem, cartId, id, bonusQuantity, quantity);
			quantityOrdered = shoppingCartService.getTotalQuantityOrdered(id);
			model.addAttribute("quantityOrdered", quantityOrdered);
		} else {
			return "redirect:/e-commerce/login.htm";
		}
		List<CustomerReview> comments = productService.getAllCommentsById(productId);
		if (comments != null) {
			model.addAttribute("comments", comments);
		}

		return "redirect:/e-commerce/product/" + productId + "/detail/" + productItemId + ".htm";
	}

	@RequestMapping(value = "product/{productId}/detail/{productItemId}", method = RequestMethod.POST, params = "buyNow")
	public String buyNow(ModelMap model, @PathVariable("productId") int productId, HttpServletRequest request,
			@PathVariable("productItemId") int productItemId) {
		int id = 0;
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL) != null) {
			id = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
					.getId();
		}
		int quantityOrdered = 0;
		if (id > 0) {
			ProductItem productItem = productService.getProductItemById(productItemId);
			model.addAttribute("product", productItem);
			Integer quantity = Integer.valueOf(request.getParameter("quantity"));
			int cartId = shoppingCartService.checkExistCartId(id);
			int bonusQuantity = shoppingCartService.getQuantityOfProductAdded(productItemId, id);
			System.out.println("bonus quantity: " + bonusQuantity);
			if (quantity == 1 & bonusQuantity > 0) {
				return "redirect:/e-commerce/cart.htm";
			}
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
			shoppingCartItem.setProductItem(productItem);
			productService.addToCart(shoppingCartItem, cartId, id, bonusQuantity, quantity);
			quantityOrdered = shoppingCartService.getTotalQuantityOrdered(id);
			model.addAttribute("quantityOrdered", quantityOrdered);
		} else {
			return "redirect:/e-commerce/login.htm";
		}
		List<CustomerReview> comments = productService.getAllCommentsById(productId);
		if (comments != null) {
			model.addAttribute("comments", comments);
		}

		return "redirect:/e-commerce/cart.htm";
	}

	@RequestMapping(value = "product/{productId}/detail/{productItemId}", method = RequestMethod.POST, params = "addComment")
	public String addComment(ModelMap model, @PathVariable("productId") int productId,
			@ModelAttribute("CustomerReview") CustomerReview customerReview, HttpServletRequest request,
			@PathVariable("productItemId") int productItemId) {
		int id = 0;
		System.out.println("Đang vào bình luận");
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL) != null) {
			id = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
					.getId();
		}
		System.out.println("ID customer: " + id);
		OrderLine orderLine = productService.isBoughtThisProduct(id, productItemId);
		if (orderLine == null) {
			System.out.println("Order line null");
		}
		if (id > 0) {
			System.out.println("Come here");
			Customer customer = customerService.getCustomerById(id);
			customerReview.setCustomer(customer);
			String comment = request.getParameter("commentInput").trim();
			customerReview.setComment(comment);
			customerReview.setOrderLine(orderLine);
			String ratingValueString = request.getParameter("ratingValue");
			customerReview.setRatingValue(Integer.valueOf(Integer.valueOf(ratingValueString)));
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			if (customerReview.getComment() != "") {
				try {
					session.save(customerReview);
					t.commit();
					System.out.println("Binh luan thanh cong");
					model.addAttribute("message", "Success");
				} catch (Exception e) {
					t.rollback();
					System.out.println("Binh luan that bai: " + e.toString());
					model.addAttribute("message", "Fail");
				} finally {
					session.close();
				}
			}
		} else {
			return "redirect:/e-commerce/login.htm";
		}
		return "redirect:/e-commerce/product/" + productId + "/detail/" + productItemId + ".htm";
	}

}
