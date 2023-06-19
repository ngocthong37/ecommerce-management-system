package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.shoppingCart.ShoppingCartDao;
import ptithcm.model.shoppingCart.ShoppingCart;
import ptithcm.model.shoppingCart.ShoppingCartItem;

@Service
public class ShoppingCartService {
	@Autowired
	private ShoppingCartDao shoppingCartDao;

	public List<ShoppingCart> getAllShoppingCart() {
		List<ShoppingCart> shoppingCarts = null;
		shoppingCarts = shoppingCartDao.getAllShoppingCart();
		if (shoppingCarts.size() > 0) {
			return shoppingCarts;
		}
		return null;
	}

	public int checkExistCartId(int customerId) {
		return shoppingCartDao.checkExistCart(customerId);
	}

	public ShoppingCart getShoppingCart(int cartId, int customerId) {
		System.out.println("size getAllShoppingCart: " + getAllShoppingCart().size());
		if (getAllShoppingCart().size() > 0) {
			for (ShoppingCart s : getAllShoppingCart()) {
				if (s.getId() == cartId) {
					return s;
				}
			}
		}
		return null;
	}

	public List<ShoppingCartItem> getAllCartItemsById(int customerId) {
		return shoppingCartDao.getAllCartItemsById(customerId);
	}

	public int getTotalQuantityOrdered(int customerId) {
		int sum = 0;
		List<ShoppingCartItem> listCartItem = shoppingCartDao.getAllCartItemsById(customerId);
		if (listCartItem.size() == 0) {
			return 0;
		} else {
			for (ShoppingCartItem s : listCartItem) {
				sum += s.getQuantity();
			}
		}
		return sum;
	}

	public ShoppingCartItem getExistItemCart(int productItemId, int customerId) {
		for (ShoppingCartItem productItem : getAllCartItemsById(customerId)) {
			if (productItemId == productItem.getProductItem().getId()) {
				return productItem;
			}
		}
		return null;
	}

	public Integer getQuantityOfProductAdded(int productItemId, int customerId) {
		for (ShoppingCartItem item : getAllCartItemsById(customerId)) {
			if (item.getProductItem().getId() == productItemId) {
				return item.getQuantity();
			}
		}
		return 0;
	}

}
