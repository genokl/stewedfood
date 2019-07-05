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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Order", description = "订单主表")
@Entity
@Table(name = "am_order")
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 201947514377420162L;

	@ApiModelProperty(value = "ID")
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@ApiModelProperty(value = "订单编号")
	private String orderID;		   //支付金额
	
	@ApiModelProperty(value = "支付金额,单位：分")
    private String amount;		   //支付金额
	
	@ApiModelProperty(value = "订单创建时间")
	private Date cteateDate;	
	
	@ApiModelProperty(value = "订单支付时间")
	private Date payDate;	
	
	@ApiModelProperty(value = "是否完成支付")
	private Integer isPay;		  
	@ApiModelProperty(value = "是否收货")
	private Integer isTake;		  
	@ApiModelProperty(value = "取货码：五位数")
	private Integer orderKey;		  

	@ApiModelProperty(value = "收货地址")
	private String address;
	@ApiModelProperty(value = "收货人姓名")
	private String receiverName;//===不使用user表的username，因为收货人未必是下单的用户。
	@ApiModelProperty(value = "电话")
	private String phone;
	
	@ApiModelProperty(value = "订单所属用户")
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Member member;
	
	@ApiModelProperty(value = "订单产品子表")//mappedBy="order",
	@OneToMany(targetEntity=OrderChild.class,  cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private List<OrderChild> orderChilds;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getCteateDate() {
		return cteateDate;
	}

	public void setCteateDate(Date cteateDate) {
		this.cteateDate = cteateDate;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Integer getIsTake() {
		return isTake;
	}

	public void setIsTake(Integer isTake) {
		this.isTake = isTake;
	}

	public Integer getOrderKey() {
		return orderKey;
	}

	public void setOrderKey(Integer orderKey) {
		this.orderKey = orderKey;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public List<OrderChild> getOrderChilds() {
		return orderChilds;
	}

	public void setOrderChilds(List<OrderChild> orderChilds) {
		this.orderChilds = orderChilds;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

//	public List<OrderChild> getOrderChilds() {
//		return orderChilds;
//	}
//
//	public void setOrderChilds(List<OrderChild> orderChilds) {
//		this.orderChilds = orderChilds;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
    
	
    												 
    
}