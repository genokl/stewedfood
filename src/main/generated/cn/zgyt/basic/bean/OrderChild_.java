package cn.zgyt.basic.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderChild.class)
public abstract class OrderChild_ {

	public static volatile SingularAttribute<OrderChild, String> childAmount;
	public static volatile SingularAttribute<OrderChild, Date> cteateDate;
	public static volatile SingularAttribute<OrderChild, Product> product;
	public static volatile ListAttribute<OrderChild, Taste> tastes;
	public static volatile SingularAttribute<OrderChild, Integer> count;
	public static volatile SingularAttribute<OrderChild, Long> id;

}

