
package ptithcm.dao.shoppingCart;

import java.util.List;

import org.springframework.stereotype.Service;

import ptithcm.model.shoppingCart.ShoppingCart;
import ptithcm.model.shoppingCart.ShoppingCartItem;

@Service
public interface ShoppingCartDao {
	public List<ShoppingCartItem> getAllCartItemsById(int cmtId);

	public List<ShoppingCart> getAllShoppingCart();

	public int deleteCartItem(int idCartItem);

	public int increaseQty(int shoppingCartItemId);

	public int decreaseQty(int shoppingCartItemId);

	public Integer checkExistCart(int ctmId);
}