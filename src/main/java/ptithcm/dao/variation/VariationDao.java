package ptithcm.dao.variation;

import java.util.List;

import ptithcm.model.product.Variation;

public interface VariationDao {
	public void insert(Variation variation);

	public void deleteById(int variationId);

	public void updateById(Variation variation);

	public Variation getVariationById(int variationId);

	public List<Variation> getAllVariations();

	public List<Variation> listPaginatedProductVariation(int firstResult, int maxResults, String search);
	
	public List<Variation> getListVariationByCategoryId(int categoryId);
}
