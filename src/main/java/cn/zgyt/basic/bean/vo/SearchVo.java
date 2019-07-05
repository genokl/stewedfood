package cn.zgyt.basic.bean.vo;

import cn.zgyt.basic.bean.Order;
import cn.zgyt.basic.bean.Product;
import cn.zgyt.basic.bean.ProductType;

public class SearchVo {
	private Integer productTypeIndex;
	private Product product;
	private ProductType productType;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public Integer getProductTypeIndex() {
		return productTypeIndex;
	}
	public void setProductTypeIndex(Integer productTypeIndex) {
		productTypeIndex = productTypeIndex;
	}
	
	
}
