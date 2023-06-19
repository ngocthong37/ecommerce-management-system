package ptithcm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.variation.VariationDao;
import ptithcm.model.product.Variation;

@Service
@Transactional
public class VariationService {
	@Autowired
	VariationDao variationDao;

	public void insertVariation(Variation variation) {
		variationDao.insert(variation);
	}

	public List<Variation> getAllVariations() {
		return variationDao.getAllVariations();
	}

	public List<Variation> getListPaginatedVariations(int firstResult, int maxResults, String search) {
		return variationDao.listPaginatedProductVariation(firstResult, maxResults, search);
	}

	public void deleteVariationById(int variationId) {
		variationDao.deleteById(variationId);
	}

	public Variation getVariationById(int variationId) {
		return variationDao.getVariationById(variationId);
	}

	public void updateVariation(Variation variation) {
		Variation existingVariation = variationDao.getVariationById(variation.getId());
		if (existingVariation != null) {
			existingVariation.setCategory(variation.getCategory());
			existingVariation.setName(variation.getName());
			variationDao.updateById(existingVariation);
		}
	}

	public boolean checkVariationExist(int variationId) {
		List<Variation> allVariations = variationDao.getAllVariations();
		for (int i = 0; i < allVariations.size(); i++) {
			if (allVariations.get(i).getId().equals(variationId)) {
				return true;
			}
		}
		return false;
	}

	public List<Variation> getListVariationByCategoryId(int categoryId) {
		return variationDao.getListVariationByCategoryId(categoryId);
	}
}
