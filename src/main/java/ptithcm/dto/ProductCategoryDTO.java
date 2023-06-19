package ptithcm.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ptithcm.model.product.ProductCategory;
import ptithcm.service.CategoryService;

@Repository
public class ProductCategoryDTO {

	private String categoryName;
	private Integer id;
	private String parentCategoryName;
	private Integer parentId;

	@Autowired
	public ProductCategoryDTO(CategoryService categoryService, ProductCategory productCategory) {
		List<ProductCategory> allCategories = categoryService.getAllCategory();
		this.categoryName = productCategory.getCategoryName();
		this.id = productCategory.getId();
		this.parentId = productCategory.getParentCategoryId();
		try {
			for (int i = 0; i < allCategories.size(); i++) {
				if (productCategory.getParentCategoryId() == null)
					break;
				if (productCategory.getParentCategoryId() == allCategories.get(i).getId()) {
					this.parentCategoryName = allCategories.get(i).getCategoryName();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Loi trans du lieu category");
		}
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}
