package cn.zgyt.basic.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Product", description = "食品")
@Entity
@Table(name = "am_product")
public class Product implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2757253055452627240L;

	@ApiModelProperty(value = "ID")
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@ApiModelProperty(value = "名称")
    private String title;		   //文章题目

	
	@ApiModelProperty(value = "口味")
    @ManyToMany(mappedBy = "products",fetch = FetchType.LAZY)
    private List<Taste> tastes; 
    
	@JSONField(serialize=false)
	@ApiModelProperty(value = "种类")
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
    joinColumns = {@JoinColumn(name = "product_id")},
    inverseJoinColumns = {@JoinColumn(name = "product_type_id")})
    private List<ProductType> productTypes;          //1，公司概况  2,联系我们 3，公司要闻，
    												  //4公司产品 5，科研技术 6，人力资源 
	@ApiModelProperty(value = "概述")
    private String productSynopsis;       //概述
	@ApiModelProperty(value = "预览图")
    private String productSynopsisImg;   //预览图 路径 一张图片
	@ApiModelProperty(value = "是否删除")
    private Integer isDel;  			   //文章删除状态   0未删除  1已删除
	@ApiModelProperty(value = "是否上架")
    private Integer isSale;			       //文章展示状态   0草稿状态 1发表状态
	@ApiModelProperty(value = "产品详情")
    private String productDetail;    	   //文章正文
	
    private String productHtmlAddress;    //文章静态页面路径
    @ApiModelProperty(value = "创建时间")
    private Date createDate;    		   //文章创建时间
    private String keyWords;    		   //关键词
    @ApiModelProperty(value = "价格")
    private Integer price;    		       //单价
    @ApiModelProperty(value = "价格单位")
    private String unit;    		       //单位
    
    @Transient
    private String aroundUrl;    		   //详情页显示，上一条下一条
    
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Integer getIsSale() {
		return isSale;
	}
	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public String getProductHtmlAddress() {
		return productHtmlAddress;
	}
	public void setProductHtmlAddress(String productHtmlAddress) {
		this.productHtmlAddress = productHtmlAddress;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public List<ProductType> getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}
	public String getAroundUrl() {
		return aroundUrl;
	}
	public void setAroundUrl(String aroundUrl) {
		this.aroundUrl = aroundUrl;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public List<Taste> getTastes() {
		return tastes;
	}
	public void setTastes(List<Taste> tastes) {
		this.tastes = tastes;
	}
    
    
}