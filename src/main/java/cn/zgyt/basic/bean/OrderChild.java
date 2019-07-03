package cn.zgyt.basic.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "OrderChild", description = "订单子表")
@Entity
@Table(name = "am_order_child")
public class OrderChild  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 811996341897758844L;

	@ApiModelProperty(value = "ID")
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@ApiModelProperty(value = "子项支付金额,单位：分")
    private String childAmount;		   //支付金额
	
	@ApiModelProperty(value = "订单创建时间")
	private Date cteateDate;	

	@ApiModelProperty(value = "订单数量")
	private Integer count;
	
	@ApiModelProperty(value = "产品")
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Product product;
	
//	@ApiModelProperty(value = "订单主表")
//	@ManyToOne(targetEntity=Order.class,fetch=FetchType.LAZY,cascade = CascadeType.ALL)
//	private Order order;
	
	@ApiModelProperty(value = "产品口味 外键")
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Taste taste;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCteateDate() {
		return cteateDate;
	}

	public void setCteateDate(Date cteateDate) {
		this.cteateDate = cteateDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Taste getTaste() {
		return taste;
	}

	public void setTaste(Taste taste) {
		this.taste = taste;
	}

	public String getChildAmount() {
		return childAmount;
	}

	public void setChildAmount(String childAmount) {
		this.childAmount = childAmount;
	}
    
	
    												 
    
}