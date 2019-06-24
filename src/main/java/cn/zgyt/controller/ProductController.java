//package cn.zgyt.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.web.PagedResourcesAssembler;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.alibaba.fastjson.JSONObject;
//
//import cn.zgyt.basic.assembler.ProductTypeAssembler;
//import cn.zgyt.basic.bean.ProductType;
//import cn.zgyt.basic.bean.vo.SearchVo;
//import cn.zgyt.core.entities.http.BaseBean;
//import cn.zgyt.repo.ProductTypeRepository;
//import cn.zgyt.util.ConstantPoot;
//import cn.zgyt.util.EntityUtils;
//import cn.zgyt.util.ResponseUtils;
//
//@Controller
//@RequestMapping("/xcx/product")
//public class ProductController {
//
//	@Autowired
//	private ProductTypeRepository productTypeRepository;
//	@Autowired
//	ProductTypeAssembler assembler;
//	
//	@Autowired
//    private PagedResourcesAssembler<ProductType> pagedResourcesAssembler;
//
//	private Logger log = Logger.getLogger(ProductController.class);
//
//	/**
//	 * 登录controller
//	 */
//	@RequestMapping("/datalist")
//	public void datalist(
//			HttpServletRequest request, 
//			HttpServletResponse response,
//			SearchVo sv) throws Exception {
//		ResponseEntity<?> re=null;
//		ProductType pt = sv.getProductType();
//		List<BaseBean> ll = productTypeRepository.findByIsDelOrderByOrderValAsc(0);
//		List<BaseBean> genSelfUrl = EntityUtils.genSelfUrl(ll, "");
//		JSONObject jo=new JSONObject();
//		jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_STR);
//		jo.put(ConstantPoot.STATUS_INFO_STR, re);
//		ResponseUtils.xcxRenderJSON(response,request, jo);
//	}
//
//	public Class<?> self(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Class<?> update(Object object) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Class<?> delete(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
