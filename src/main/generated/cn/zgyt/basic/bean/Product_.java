package cn.zgyt.basic.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, String> keyWords;
	public static volatile ListAttribute<Product, Taste> tastes;
	public static volatile SingularAttribute<Product, String> productDetail;
	public static volatile ListAttribute<Product, ProductType> productTypes;
	public static volatile SingularAttribute<Product, String> title;
	public static volatile SingularAttribute<Product, String> productSynopsisImg;
	public static volatile SingularAttribute<Product, String> unit;
	public static volatile SingularAttribute<Product, Integer> price;
	public static volatile SingularAttribute<Product, Integer> isSale;
	public static volatile SingularAttribute<Product, String> productHtmlAddress;
	public static volatile SingularAttribute<Product, Integer> id;
	public static volatile SingularAttribute<Product, String> productSynopsis;
	public static volatile SingularAttribute<Product, Integer> isDel;
	public static volatile SingularAttribute<Product, Date> createDate;

}

