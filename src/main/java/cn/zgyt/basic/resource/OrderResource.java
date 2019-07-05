package cn.zgyt.basic.resource;

import org.springframework.hateoas.Resource;

import cn.zgyt.basic.bean.Order;

/**
 * 
 * @author wxy
 * @date 2019年6月12日
 * 
 */
public class OrderResource extends Resource<Order> {

	
	public OrderResource(Order content) {
		super(content);
	}

}
