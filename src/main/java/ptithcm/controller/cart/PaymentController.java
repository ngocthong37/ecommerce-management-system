package ptithcm.controller.cart;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.constant.SystemConstant;
import ptithcm.model.customer.Customer;
import ptithcm.model.order.OrderLine;
import ptithcm.model.shop.ShopOrder;
import ptithcm.model.shoppingCart.ShoppingCartItem;
import ptithcm.service.AddressService;
import ptithcm.service.CartService;
import ptithcm.service.PaymentService;
import ptithcm.service.ProductService;
import ptithcm.util.SessionUtil;

@RequestMapping("/e-commerce/")
@Controller
@Transactional
public class PaymentController {

	@Autowired
	AddressService addressService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	SessionFactory sessionFactory;

	@RequestMapping(value = "address/deliver/checkout", method = RequestMethod.POST)
	public String checkout(@RequestParam int PaymentMethod, @RequestParam int ShippingMethod, ModelMap model,
			HttpSession session, HttpServletRequest request) {
		int id = (int) ((Customer) SessionUtil.getInstance().getValue(request, SystemConstant.Model.CUSTOMER_MODEL))
				.getId();
		int addressId = (int) session.getAttribute("addressId");
		Long sum = (Long) session.getAttribute("sum") + paymentService.getShippingById(ShippingMethod).getPrice();
		List<Long> price = (List<Long>) session.getAttribute("price");
		System.out.println(addressId);
		System.out.println("zoday");

		Date sqlDate = new Date(System.currentTimeMillis());
		ShopOrder shopOrder = new ShopOrder();
		shopOrder.setShippingMethod(paymentService.getShippingById(ShippingMethod));
		shopOrder.setCustomerAddress(addressService.getAddressById(addressId));
		shopOrder.setOrderStatus(addressService.getOrderStatusById(1));
		shopOrder.setCustomerPaymentMethod(paymentService.getPaymentById(PaymentMethod));
		shopOrder.setOrderDate(sqlDate);
		shopOrder.setOrderTotal(sum);
		Session session1 = sessionFactory.openSession();
		org.hibernate.Transaction t = session1.beginTransaction();

		try {
			session1.save(shopOrder);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công! ");
			System.out.println("done");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại! ");
			System.out.println(e);
		} finally {
			session1.close();
		}
		List<ShoppingCartItem> listCartItems = cartService.getAllCartItemsById(id);
		for (int i = 0; i < listCartItems.size(); i++) {
			System.out.println(listCartItems.size());
			OrderLine orderLine = new OrderLine();
			orderLine.setProductItem(listCartItems.get(i).getProductItem());
			orderLine.setShopOrder(shopOrder);
			orderLine.setQuantity(listCartItems.get(i).getQuantity());
			orderLine.setPrice(price.get(i));
			Session session2 = sessionFactory.openSession();
			org.hibernate.Transaction t1 = session2.beginTransaction();
			try {
				session2.save(orderLine);
				t1.commit();
				System.out.println("done");
			} catch (Exception e) {
				t1.rollback();
				System.out.println(e);
			} finally {
				session2.close();
			}
			productService.updateQty(listCartItems.get(i).getId(), listCartItems.get(i).getQuantity() * (-1));
			cartService.deleteCartItem(listCartItems.get(i).getId());
		}
		return "e-commerce/checkout";
	}

	@RequestMapping(value = "checkout")
	public String showOrderSuccessPage() {
		return "e-commerce/checkout";
	}
}
