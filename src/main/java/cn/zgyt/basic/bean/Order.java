package cn.zgyt.basic.bean;

import java.util.Date;
import java.util.List;

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
@Table(name = "am_Order")
public class Order {
	
	@ApiModelProperty(value = "ID")
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@ApiModelProperty(value = "支付金额")
    private String amount;		   //文章题目

    
    												  //4公司产品 5，科研技术 6，人力资源 
    
}