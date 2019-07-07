package cn.zgyt.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import cn.zgyt.basic.bean.Order;
import cn.zgyt.repo.OrderRepository;
import cn.zgyt.util.ConstantPoot;
import cn.zgyt.util.ResponseUtils;
import cn.zgyt.util.xcx.XcxConfig;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/xcx/order")
public class OrderController {
	
	SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private XcxConfig xcxConfig;
	
	@Autowired
	OrderRepository rep;
	


	@Autowired
	private PagedResourcesAssembler<Order> pagedResourcesAssembler;

	private Logger log = Logger.getLogger(OrderController.class);
	
	/**
	 * pages/user/dingdan?currentTab=0&otype=pay
	 * pages/user/dingdan?currentTab=2&otype=receive
	 * pages/user/dingdan?currentTab=3&otype=finish
	 * @param vo
	 * @param request
	 * @param response
	 */
	@ApiOperation(value = "订单列表", notes = "订单列表", code = 200, produces = "application/json")
	@RequestMapping(value="/datalist", method= {RequestMethod.POST})
	public void datalist(
			@RequestParam Map<String, Object> vo,
			@RequestBody(required = false) Order order,
			HttpServletRequest request, 
			HttpServletResponse response) {
//		Order order = new Order();
		//将匹配对象封装成Example对象
		JSONObject jo=new JSONObject();
		jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_INIT);
		try {
			Example<Order> ex =Example.of(order);
			List<Order> ll = rep.findAll(ex);
			jo.put(ConstantPoot.STATUS_INFO_STR, ll);
			jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.xcxRenderJSON(response,request, jo);
	}

	@RequestMapping(value="/self", method= {RequestMethod.GET})
	public void self(
			Long id,
			HttpServletRequest request, 
			HttpServletResponse response) {
		JSONObject jo=new JSONObject();
		jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_INIT);
		try {
			Order order = rep.findOne(id);
			jo.put(ConstantPoot.STATUS_INFO_STR, order);
			jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.xcxRenderJSON(response,request, jo);
	}

	@RequestMapping(value="/delete", method= {RequestMethod.GET})
	public Class<?> delete(Long id,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		JSONObject jo=new JSONObject();
		jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_INIT);
		try {
			rep.delete(id);
			jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.xcxRenderJSON(response,request, jo);
		return null;
	}

	public Class<?> update(Object object, Object object2, Object object3) {
		// TODO Auto-generated method stub
		return null;
	}

}
