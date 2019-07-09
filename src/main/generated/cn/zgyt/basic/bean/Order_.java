package cn.zgyt.basic.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, Integer> orderKey;
	public static volatile SingularAttribute<Order, String> amount;
	public static volatile SingularAttribute<Order, Integer> isPay;
	public static volatile SingularAttribute<Order, String> address;
	public static volatile SingularAttribute<Order, String> orderID;
	public static volatile SingularAttribute<Order, String> receiverName;
	public static volatile ListAttribute<Order, OrderChild> orderChilds;
	public static volatile SingularAttribute<Order, Date> cteateDate;
	public static volatile SingularAttribute<Order, String> phone;
	public static volatile SingularAttribute<Order, Integer> isTake;
	public static volatile SingularAttribute<Order, Member> member;
	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, Date> payDate;

}

