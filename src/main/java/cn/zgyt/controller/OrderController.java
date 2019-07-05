//package cn.zgyt.controller;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.data.web.PagedResourcesAssembler;
//import org.springframework.hateoas.PagedResources;
//import org.springframework.hateoas.core.EmbeddedWrapper;
//import org.springframework.hateoas.core.EmbeddedWrappers;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.epc.pm.entity.base.BaseArrangement;
//import com.epc.pm.entity.base.BaseBulletin;
//import com.epc.pm.services.base.assembler.BaseArrangementAssembler;
//import com.epc.pm.services.base.assembler.BasePackageUtil;
//
//import cn.zgyt.base.assembler.OrderAssembler;
//import cn.zgyt.basic.bean.Order;
//import cn.zgyt.basic.bean.Order_;
//import cn.zgyt.repo.OrderRepository;
//import cn.zgyt.util.ResponseUtils;
//import cn.zgyt.util.xcx.XcxConfig;
//import io.swagger.annotations.ApiOperation;
//
//@Controller
//@RequestMapping("/xcx/order")
//public class OrderController {
//	
//	SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
//	
//	@Autowired
//	private XcxConfig xcxConfig;
//	
//	@Autowired
//	OrderRepository rep;
//	
//	@Autowired
//	OrderAssembler assembler;
//
//
//	@Autowired
//	private PagedResourcesAssembler<Order> pagedResourcesAssembler;
//
//	private Logger log = Logger.getLogger(OrderController.class);
//	
//	/**
//	 * http://localhost/services/bulletin/datalist?page=1&size=20&_=1562316855654
//	 * @param vo
//	 * @param request
//	 * @param response
//	 */
//	@ApiOperation(value = "订单列表", notes = "订单列表", code = 200, produces = "application/json")
//	@RequestMapping(value="/datalist", method= {RequestMethod.GET})
//	public void datalist(
//			@RequestParam Map<String, Object> vo,
//			@PageableDefault Pageable pageable,
//			HttpServletRequest request, 
//			HttpServletResponse response) {
////		Order order = new Order();
//		//将匹配对象封装成Example对象
//		Specification<Order> spec = new Specification<Order>() {
//			@Override
//			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				List<Predicate> list = new ArrayList<Predicate>();
//				try {
//					if (vo.containsKey("payDate")) {
//						list.add(cb.equal(root.get("payDate"),sdfDay.parse(vo.get("payDate").toString())));
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				Predicate[] p = new Predicate[list.size()];
//				query.orderBy(cb.desc(root.get(Order_.payDate)));
//				return cb.and(list.toArray(p));
//			}
//		};
//		Page<Order> ll = rep.findAll(spec, pageable);
//		PagedResources pagedResources = pagedResourcesAssembler.toResource(arrangementpage, assembler);
//		if (arrangementpage.getContent() == null || arrangementpage.getContent().isEmpty()) {
//			EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
//			EmbeddedWrapper wrapper = wrappers.emptyCollectionOf(BaseBulletin.class);
//			List<EmbeddedWrapper> embedded = Collections.singletonList(wrapper);
//			pagedResources = new PagedResources(embedded, pagedResources.getMetadata(), pagedResources.getLinks());
//		}
//		ResponseUtils.xcxRenderJSON(response,request, jo);
//	}
//
//}
