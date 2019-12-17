package cn.com.newloading.service;

import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.Admin;

public interface AdminService {

	/**
	 * 管理员登陆
	 * @param admin
	 * @return
	 */
	Admin adminLogin(String admAccount,String admPassword);
	
}
