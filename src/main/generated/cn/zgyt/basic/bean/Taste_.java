package cn.zgyt.basic.bean;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Taste.class)
public abstract class Taste_ {

	public static volatile SingularAttribute<Taste, String> tasteVal;
	public static volatile SingularAttribute<Taste, Integer> id;
	public static volatile SingularAttribute<Taste, String> tasteKey;
	public static volatile ListAttribute<Taste, Product> products;

}

