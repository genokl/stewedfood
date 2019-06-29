package cn.zgyt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import cn.zgyt.basic.bean.Product;
import cn.zgyt.basic.bean.ProductType;
import cn.zgyt.basic.bean.vo.SearchVo;
import cn.zgyt.repo.ProductRepository;
import cn.zgyt.repo.ProductTypeRepository;
import cn.zgyt.util.ConstantPoot;
import cn.zgyt.util.ResponseUtils;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/xcx/productType")
public class ProductTypeController {

	@Autowired
	private ProductTypeRepository productTypeRepository;
	@Autowired
	private ProductRepository productRepository;

	private Logger log = Logger.getLogger(ProductTypeController.class);
	/**
	 * 登录controller
	 */
	@ApiOperation(value = "ProductType类型列表", notes = "参数描述", code = 200, produces = "application/json")
	@RequestMapping(value="/datalist", method= {RequestMethod.GET})
	public void datalist(
			HttpServletRequest request, 
			HttpServletResponse response,
			@RequestBody(required=false) SearchVo sv) throws Exception {
		Random random = new Random();
//		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		List<ProductType> ll=new ArrayList<>();
		JSONObject jo=new JSONObject();
		jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_INIT);
		if(sv==null) {
			List<Product> products = productRepository.findAll();
//			ProductType pt = sv.getProductType();
			ll = productTypeRepository.findByIsDelOrderByOrderValAsc(0);
//			if(ll.size()>0) {
//				Integer id = ll.get(0).getId();
//			}
			jo.put(ConstantPoot.STATUS_INFO_STR, ll);
			jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_SUCCESS);
		}
		ResponseUtils.xcxRenderJSON(response,request, jo);
	}
	
	@ApiOperation(value = "某个productType类型", notes = "参数描述", code = 200, produces = "application/json")
	@RequestMapping(value="/self/{id}",method= {RequestMethod.GET})
	public void self(HttpServletResponse response,HttpServletRequest request,Integer id) {
		ProductType one = productTypeRepository.getOne(id);
		JSONObject jo=new JSONObject();
		jo.put(ConstantPoot.STATUS_INFO_STR, one);
		jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_SUCCESS);
		ResponseUtils.xcxRenderJSON(response,request, jo);
	}

	public void update(Object object) {
		// TODO Auto-generated method stub
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
	}
}
