package cn.zgyt.app;

import cn.zgyt.basic.bean.Member;

public class UserCacheInfo {
	
	private Member  m ;						//用户对象
	private String nikeName;				//微信昵称
	private Long lastRequestTime;			//最后请求交互时间
	private Long loginTime;					//登录时间
	private String loginIP;					//登录IP 
	private String loginKey;				//登录Key
	private String scanClientIds;			//申请查看的客户资料ID 集合  
	private String lastLoginTime;			//最后一次登录时间
	private String lastLoginIp;				//最后一个登录IP
	private String city;					//城市
	private String country;					//国家
	private String province;				//省
	
	
	
	/**
	 * 创建一个在现对象
	 * @param user					//登录对象
	 * @param lastRequestTime		//最后请求交互时间
	 * @param loginTime				//登录时间
	 * @param loginIP				//登录IP
	 * @param loginKey				//登录KEY
	 */
	public UserCacheInfo(Member m,String loginIP,String loginKey){
		this.loginTime=System.currentTimeMillis();
		this.lastRequestTime=System.currentTimeMillis();
		this.m=m;
		this.loginIP=loginIP;
		this.loginKey=loginKey;
	}
	
	public UserCacheInfo(){}
	
	
	public Member getM() {
		return m;
	}
	public void setM(Member m) {
		this.m = m;
	}

	public Long getLastRequestTime() {
		return lastRequestTime;
	}
	public void setLastRequestTime(Long lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
	}
	public Long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public String getLoginKey() {
		return loginKey;
	}
	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}
	public String getScanClientIds() {
		return scanClientIds;
	}
	public void setScanClientIds(String scanClientIds) {
		this.scanClientIds = scanClientIds;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getNikeName() {
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	
}
