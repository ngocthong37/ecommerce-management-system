package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.ProductCategory.productCategoryDao;
import ptithcm.model.product.ProductCategory;


@Service
public class ProductCategoryService {
	
	
	
	@Autowired
	productCategoryDao productCategoryDao;
	
	public List<ProductCategory> getAllProductCategory() {
		List<ProductCategory> list = productCategoryDao.getAllProductCategory();
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}
	
	public ProductCategory getProductCategory(int cateId) {
		ProductCategory productCategory = productCategoryDao.getProductCategory(cateId);
		if (productCategory != null) {
			return productCategory;
		}
		return null;
	}
	
	
}
