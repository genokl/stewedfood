package cn.zgyt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import cn.zgyt.basic.bean.Logistic;
import cn.zgyt.repo.LogisticRepository;
import cn.zgyt.util.ConstantPoot;
import cn.zgyt.util.ResponseUtils;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/xcx/logistic")
public class LogisticController {


	@Autowired
	private LogisticRepository rep;

	@Autowired
	private LogisticRepository trRep;

	@ApiOperation(value = "修改物流详情", notes = "参数描述", code = 200, produces = "application/json")
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	public Class<?> update(
			@RequestBody(required = false) Logistic entity, 
			HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_INIT);
		try {
			if (null != entity) {
				rep.save(entity);
			}
			jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.xcxRenderJSON(response, request, jo);
		return null;
	}

	@ApiOperation(value = "查询物流详情", notes = "参数描述", code = 200, produces = "application/json")
	@RequestMapping(value = "/self", method = { RequestMethod.GET })
	public Class<?> self(Integer memberId, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_INIT);
		try {
			Logistic res = rep.findOneByMemberId(memberId);
			jo.put(ConstantPoot.STATUS_INFO_STR, res);
			jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.xcxRenderJSON(response, request, jo);
		return null;
	}

	@ApiOperation(value = "添加物流详情", notes = "参数描述", code = 200, produces = "application/json")
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public Class<?> add(Logistic entity, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_INIT);
		try {
			rep.save(entity);
			jo.put(ConstantPoot.STATUS_STR, ConstantPoot.STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.xcxRenderJSON(response, request, jo);
		return null;
	}

}
