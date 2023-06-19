package ptithcm.dto;

import java.util.List;

import ptithcm.constant.SystemConstant;
import ptithcm.model.product.Product;
import ptithcm.model.product.ProductItem;
import ptithcm.service.admin.PromotionService;

public class ProductDTO {
	private Integer id;
	private Integer productCategoryId;
	private String name;
	private String description;
	private String productImage;
	private String productCategoryName;
	private int defaultProductItem = 0;
	private long defaultPrice = 0;
	private long defaultSalePrice = 0;

	public ProductDTO(Product product, PromotionService promotionService) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.productImage = product.getProductImage();
		this.productCategoryId = product.getProductCategory().getId();
		this.productCategoryName = product.getProductCategory().getCategoryName();
		List<ProductItem> listProductItems = (List<ProductItem>) product.getProductItems();
		if (listProductItems.size() >= 1) {
			defaultProductItem = listProductItems.get(0).getId();
			defaultPrice = listProductItems.get(0).getPrice();
			if (promotionService != null && listProductItems.get(0).getStatus().equals(SystemConstant.ProductStatus.ON_SALE)) {
				System.out.println("Price: " + listProductItems.get(0).getPrice());
				long salePrice = promotionService.getPriceDiscount(listProductItems.get(0).getId(),
						listProductItems.get(0).getPrice());
				System.out.println("sale price: " + salePrice);
				this.defaultSalePrice = salePrice;
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public int getDefaultProductItem() {
		return defaultProductItem;
	}

	public void setDefaultProductItem(int defaultProductItem) {
		this.defaultProductItem = defaultProductItem;
	}

	public long getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(int defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public long getDefaultSalePrice() {
		return defaultSalePrice;
	}

	public void setDefaultSalePrice(long defaultSalePrice) {
		this.defaultSalePrice = defaultSalePrice;
	}

	public void setDefaultPrice(long defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

}