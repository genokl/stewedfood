package cn.zgyt.basic.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户表entity
 * 
 * @author wxy
 */
@ApiModel(value = "Member", description = "微信用户")
@Entity
@Table(name = "sf_member")
public class Member implements Serializable {
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 11)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String openId;

	private String unionId;

	private String name;

	private String nickName;

	/**
	 * 头像外键Id
	 */
	private String headImg;

	/**
	 * 性别1男0女
	 */
	private Integer sex;

	private String phone;

	private String email;

	private String workAddress;

	/**
	 * 微信号
	 */
	private Integer weCatNo;

	private Integer currentNeeds;

	private Integer remake;

	
	@ApiModelProperty(value = "会员类型外键",example="1:普通用户，2管理员")
	private Integer memberType;

	
	private Integer isDisplayCartoon;
	
	@ApiModelProperty(value = "授权验证码",example="四位随机数")
	private Integer accessCode;
	
//	@OneToMany(targetEntity=Order.class, mappedBy="member", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<Order> orders;

//    @JsonFilter(format = "yyyy-MM-dd hh:mm:ss")   
	private Date createTime;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "work_address")
	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	@Column(name = "we_cat_no")
	public Integer getWeCatNo() {
		return weCatNo;
	}

	public void setWeCatNo(Integer weCatNo) {
		this.weCatNo = weCatNo;
	}

	@Column(name = "current_needs")
	public Integer getCurrentNeeds() {
		return currentNeeds;
	}

	public void setCurrentNeeds(Integer currentNeeds) {
		this.currentNeeds = currentNeeds;
	}

	public Integer getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(Integer accessCode) {
		this.accessCode = accessCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getRemake() {
		return remake;
	}

	public void setRemake(Integer remake) {
		this.remake = remake;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public Integer getIsDisplayCartoon() {
		return isDisplayCartoon;
	}

	public void setIsDisplayCartoon(Integer isDisplayCartoon) {
		this.isDisplayCartoon = isDisplayCartoon;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Member() {
		super();
	}

}