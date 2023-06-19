package ptithcm.service.admin;
import java.util.List;

import org.apache.logging.log4j.core.appender.rolling.action.IfFileName;
import org.hibernate.engine.query.spi.ReturnMetadata;
import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.promotion.PromotionDao;
import ptithcm.model.promotion.Promotion;
import ptithcm.model.promotion.PromotionCategory;

@Service
public class PromotionService {
	
	@Autowired
	PromotionDao promotionDao;
	
	public long getPriceDiscount(int productItemId, long oldPrice) {
		long price = promotionDao.getPriceDiscount(productItemId, oldPrice);
		if (price >= 0) {
			System.out.println("percent: " + price);
			return price;
		}
		return -1;
	}
	
	public List<Promotion> getAllPromotions() {
		List<Promotion> list = promotionDao.getListPromotion();
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	public Promotion getPromotionById(int promotionId) {
		Promotion promotion = promotionDao.getPromotionById(promotionId);
		if (promotion != null) {
			return promotion;
		}
		return null;
	}
	
	public Integer getProductCategoryId(int promotionId) {
		Integer productCategory = promotionDao.getProductCategoryId(promotionId);
		if (productCategory > 0) {
			return productCategory;
		}
		return 0;
	}
	
	public void updatePromotion(Promotion promotion) {
		promotionDao.updatePromotionById(promotion);
	}
	
	public void updatePromotionCategory(PromotionCategory promotionCategory) {
		promotionDao.updatePromotionCategory(promotionCategory);
	}
	
	public PromotionCategory getPromotionCategoryById(int promotionId) {
		PromotionCategory promotionCategory = promotionDao.getPromotionCategoryById(promotionId);
		if (promotionCategory != null) {
			return promotionCategory;
		}
		return null;
	}
	
	public PromotionCategory getPromotionCategoryByCateId(int promotionId, int cateId) {
		PromotionCategory promotionCategory = promotionDao.getPromotionCategoryByCateId(promotionId, cateId);
		if (promotionCategory != null) {
			return promotionCategory;
		}
		return null;
	}
	
	public void deleteOldPromotionCategory(PromotionCategory promotionCategory) {
		promotionDao.deleteOldPromotionCategory(promotionCategory);
	}
	
}

