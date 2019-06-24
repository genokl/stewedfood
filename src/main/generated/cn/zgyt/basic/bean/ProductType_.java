package cn.zgyt.basic.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductType.class)
public abstract class ProductType_ {

	public static volatile SingularAttribute<ProductType, Integer> orderVal;
	public static volatile SingularAttribute<ProductType, String> productTypeName;
	public static volatile SingularAttribute<ProductType, Integer> id;
	public static volatile SingularAttribute<ProductType, String> productSynopsis;
	public static volatile SingularAttribute<ProductType, Integer> isDel;
	public static volatile SingularAttribute<ProductType, String> productSynopsisImg;
	public static volatile ListAttribute<ProductType, Product> products;
	public static volatile SingularAttribute<ProductType, Date> createDate;

}

