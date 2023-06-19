package ptithcm.controller.cart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.constant.SystemConstant;
import ptithcm.model.customer.Customer;
import ptithcm.model.product.ProductItem;
import ptithcm.model.shoppingCart.ShoppingCartItem;
import ptithcm.service.AddressService;
import ptithcm.service.CartService;
import ptithcm.util.SessionUtil;

@RequestMapping("/e-commerce/")
@Controller
@Transactional
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	AddressService addressService;

	public static ShoppingCartItem shoppingCartItem;
	public static ProductItem productItem;

	@RequestMapping(value = "cart")
	public String showCart(ModelMap model, HttpSession ss, HttpServletRequest request) {
		long sum = 0;
		if (SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL) == null) {
			return "redirect:/e-commerce/login.htm";
		}
		int id = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
				.getId();
		List<ShoppingCartItem> listCart = cartService.getAllCartItemsById(id);
		if (listCart.size() == 0) {
			return "e-commerce/emptyCart";
		}
		List<Long> price = new ArrayList<>();
		for (ShoppingCartItem item : listCart) {
			System.out.println(item.getProductItem().getStatus());
			if (item.getProductItem().getStatus().equals("ON_SALE")) {
				System.out.println(item.getProductItem().getId());
				Long salePrice = cartService.getSalePrice(item.getProductItem().getId(), item.getProductItem().getPrice());
				price.add(salePrice);
				sum += salePrice * item.getQuantity();
			} else {
				price.add(item.getProductItem().getPrice());
				sum += item.getProductItem().getPrice() * item.getQuantity();
			}
		}
		System.out.println(listCart.size());
		model.addAttribute("shoppingCart", listCart);
		ss.setAttribute("price", price);
		ss.setAttribute("sum", sum);
		return "e-commerce/cart";
	}

	@RequestMapping(value = "emptyCart")
	public String showEmptyCart() {
		return "e-commerce/emptyCart";
	}

	@RequestMapping(params = "checkOut")
	public String showAddress(ModelMap model) {
		return "e-commerce/address";
	}

	@RequestMapping(value = "cart/increase", method = RequestMethod.POST)
	public String increaseCartItem(@RequestParam("productId") int productId) {
		cartService.increaseQty(productId);
		return "redirect:/e-commerce/cart.htm";
	}

	@RequestMapping(value = "cart/decrease", method = RequestMethod.POST)
	public String decreaseCartItem(@RequestParam("productId") int productId) {
		cartService.decreaseQty(productId);
		return "redirect:/e-commerce/cart.htm";
	}

	@RequestMapping(value = "cart/delete", method = RequestMethod.POST)
	public String deleteCartItem(@RequestParam("productId") int productId) {
		cartService.deleteCartItem(productId);
		return "redirect:/e-commerce/cart.htm";
	}

}