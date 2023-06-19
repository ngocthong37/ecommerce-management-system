package ptithcm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.promotion.PromotionDao;
import ptithcm.dao.shoppingCart.ShoppingCartDao;
import ptithcm.model.shoppingCart.ShoppingCartItem;


@Service
public class CartService {
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	@Autowired
	private PromotionDao promotionDao;

	
	public List<ShoppingCartItem> getAllCartItemsById(int ctmId){
		 return shoppingCartDao.getAllCartItemsById(ctmId);
	}
	
	public int deleteCartItem(int idCartItem) {
		int delete = shoppingCartDao.deleteCartItem(idCartItem);
		return delete;
	}
	
	public int increaseQty(int shoppingCartItemId) {
		int increase =shoppingCartDao.increaseQty(shoppingCartItemId);
		return increase;
	}
	
	public int decreaseQty(int shoppingCartItemId) {
		int decrease =shoppingCartDao.decreaseQty(shoppingCartItemId);
		return decrease;
	}

	public long getSalePrice(int productItemId, long OldPrice){
		return promotionDao.getPriceDiscount(productItemId, OldPrice);
	}

}
