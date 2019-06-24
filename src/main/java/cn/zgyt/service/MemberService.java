package cn.zgyt.service;

import java.net.URLEncoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.zgyt.basic.bean.Member;
import cn.zgyt.repo.MemberRepository;
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public Member mergeMembersByWebJSONAndXcxJSON(String userInfos, String wxAppId) throws Exception {
		JSONObject userInfo = JSONObject.parseObject(userInfos);
//		String tUnionId=jo.getString("unionId");
		
//		hql="from ms.basic.bean.Member where openId= '"+tOpenId+"' or unionId ='"+tUnionId+"'";
		String tOpenId=userInfo.getString("openId");
		Member m=memberRepository.findByOpenId(tOpenId);
		if(m==null){
			//根据openId或者unionId 没有查询人任何对象
			// 会员管理系统小程序
//			m.setUnionId(tUnionId);
			m=new Member();
			m.setHeadImg(userInfo.getString("avatarUrl"));
			m.setSex(userInfo.getInteger("gender"));
			m.setNickName(URLEncoder.encode(userInfo.getString("nickName"),"utf-8"));
			m.setIsDisplayCartoon(0);
			m.setMemberType(1);
			//添加最新的 会员对象
			m.setOpenId(tOpenId);
			m.setCreateTime(new Date());
			memberRepository.save(m);
//			m.setUnionId(tUnionId);
			return m;
		}else{
			Member rm = m;
			// 会员管理系统小程序
			rm.setHeadImg(userInfo.getString("avatarUrl"));
			rm.setSex(userInfo.getInteger("gender"));
			rm.setNickName(URLEncoder.encode(userInfo.getString("nickName"),"utf-8"));
			return rm;
		}
		
	}

	public Member getInstanceByID(int memberId) {
		//  根据会员ID 查询一个会员
		return memberRepository.findOne(memberId);
	}

}
