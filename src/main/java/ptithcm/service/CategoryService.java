package ptithcm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.category.ICategoryDao;
import ptithcm.model.product.ProductCategory;

@Service
@Transactional
public class CategoryService {
	@Autowired
	private ICategoryDao categoryDao;

	public void insertCategory(ProductCategory category) {
		categoryDao.insert(category);
	}

	public List<ProductCategory> getAllCategory() {
		return categoryDao.getAllCategory();
	}

	public List<ProductCategory> getListPaginatedCategories(int firstResult, int maxResults, String search) {
		return categoryDao.listPaginatedProductCategory(firstResult, maxResults, search);
	}

	public void deleteCategoryById(int categoryId) {
		categoryDao.deleteById(categoryId);
	}

	public ProductCategory getProductCategoryById(int categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	public void updateCategory(ProductCategory productCategory) {
		categoryDao.updateById(productCategory);
	}

	public boolean checkCategoryExist(int categoryId) {
		List<ProductCategory> listCategories = categoryDao.getAllCategory();
		for (int i = 0; i < listCategories.size(); i++) {
			if (listCategories.get(i).getId().equals(categoryId)) {
				return true;
			}
		}
		return true;
	}
}
