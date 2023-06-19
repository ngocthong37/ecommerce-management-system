package ptithcm.dao.promotion;

import java.util.List;


import ptithcm.model.promotion.Promotion;
import ptithcm.model.promotion.PromotionCategory;

public interface PromotionDao {
	public long getPriceDiscount(int productItemId, long oldPrice);
	public List<Promotion> getListPromotion();
	public Promotion getPromotionById(int promotionId);
	public Integer getProductCategoryId(int promotionId);
	public void updatePromotionById(Promotion promotion);
	public PromotionCategory getPromotionCategoryById(int promotionId);
	public PromotionCategory getPromotionCategoryByCateId(int promotionId, int cateId);
	public void updatePromotionCategory(PromotionCategory promotionCategory);
	public void deleteOldPromotionCategory(PromotionCategory promotionCategory);
}
