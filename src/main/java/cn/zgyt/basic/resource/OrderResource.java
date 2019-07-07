package cn.zgyt.basic.resource;

import java.util.List;

import org.springframework.hateoas.Link;
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

	public OrderResource(Order content, List<Link> links) {
		super(content, links);
	}
}
