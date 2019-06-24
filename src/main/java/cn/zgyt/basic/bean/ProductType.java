package cn.zgyt.basic.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.zgyt.core.entities.http.BaseBean;

@Entity
@Table(name = "am_product_type")
public class ProductType extends BaseBean{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String productTypeName;		   //文章题目
    
    @ManyToMany(mappedBy = "productTypes")
    private List<Product> products;          //1，公司概况  2,联系我们 3，公司要闻，
    							           //卤货单品， 套餐
    private String productSynopsis;       //概述
    private String productSynopsisImg;   //预览图 路径 一张图片
    private Integer isDel;  			   //文章删除状态   0未删除  1已删除
    
    private Date createDate;    		   //文章创建时间
    private Integer orderVal;    		  	   //排序
    
    @Transient
    private String aroundUrl;    		   //详情页显示，上一条下一条
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductSynopsis() {
		return productSynopsis;
	}
	public void setProductSynopsis(String productSynopsis) {
		this.productSynopsis = productSynopsis;
	}
	public String getProductSynopsisImg() {
		return productSynopsisImg;
	}
	public void setProductSynopsisImg(String productSynopsisImg) {
		this.productSynopsisImg = productSynopsisImg;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getOrderVal() {
		return orderVal;
	}
	public void setOrderVal(Integer orderVal) {
		this.orderVal = orderVal;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getAroundUrl() {
		return aroundUrl;
	}
	public void setAroundUrl(String aroundUrl) {
		this.aroundUrl = aroundUrl;
	}
	
    
    
    
}