package cn.zgyt.service;

import java.net.URLEncoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.zgyt.basic.bean.Member;
import cn.zgyt.repo.MemberRepository;
import cn.zgyt.util.CommonUtils;
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	

	public Member getInstanceByID(int memberId) {
		//  根据会员ID 查询一个会员
		return memberRepository.findOne(memberId);
	}

	public Member mergeMembersByWebJSONAndXcxJSON(Member mm, String appsecret) {
		Member m=memberRepository.findByOpenId(mm.getOpenId());
		if(m==null){
			//根据openId或者unionId 没有查询人任何对象
			// 会员管理系统小程序
//			m.setUnionId(tUnionId);
			m=new Member();
//			m.setHeadImg(userInfo.getString("avatarUrl"));
//			m.setSex(userInfo.getInteger("gender"));
//			m.setNickName(URLEncoder.encode(userInfo.getString("nickName"),"utf-8"));
			m.setIsDisplayCartoon(0);
			m.setMemberType(1);
			//添加最新的 会员对象
			m.setSessionKey(mm.getSessionKey());
			m.setOpenId(mm.getOpenId());
			m.setCreateTime(new Date());
			m.setMemberType(1);//默认第一次登录为普通用户
			m.setAccessCode(CommonUtils.random(5));
			memberRepository.save(m);
//			m.setUnionId(tUnionId);
			return m;
		}else{
			Member rm = mm;
			// 会员管理系统小程序
//			rm.setHeadImg(userInfo.getString("avatarUrl"));
//			rm.setSex(userInfo.getInteger("gender"));
//			rm.setNickName(URLEncoder.encode(userInfo.getString("nickName"),"utf-8"));
			return rm;
		}
	}

}
