package cn.zgyt.basic.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ProductType", description = "产品种类")
@Entity
@Table(name = "am_product_type")
public class ProductType  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4703628177604858246L;

	@ApiModelProperty(value = "ID")
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@ApiModelProperty(value = "文章类型名称")
    private String productTypeName;		   //文章类型题目
    
	@ApiModelProperty(value = "文章外键")
    @ManyToMany(mappedBy = "productTypes")
    private List<Product> products;          //1，公司概况  2,联系我们 3，公司要闻，
    							           //卤货单品， 套餐
	@ApiModelProperty(value = "文章类型预览")
    private String productTypeSynopsis;       //概述
	
	@ApiModelProperty(value = "文章类型预览图")
    private String productTypeSynopsisImg;   //预览图 路径 一张图片
	
	@ApiModelProperty(value = "是否删除")
    private Integer isDel;  			   //文章删除状态   0未删除  1已删除
    
	@ApiModelProperty(value = "创建时间")
    private Date createDate;    		   //文章创建时间
	
	@ApiModelProperty(value = "排序")
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
	public String getProductTypeSynopsis() {
		return productTypeSynopsis;
	}
	public void setProductTypeSynopsis(String productTypeSynopsis) {
		this.productTypeSynopsis = productTypeSynopsis;
	}
	public String getProductTypeSynopsisImg() {
		return productTypeSynopsisImg;
	}
	public void setProductTypeSynopsisImg(String productTypeSynopsisImg) {
		this.productTypeSynopsisImg = productTypeSynopsisImg;
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