package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.ContectAdmin;

public interface ContectAdminService {

	/**
	 * 新增管理信息
	 * @param contectAdmin
	 * @return
	 */
	String addContectAdmin(ContectAdmin contectAdmin);
	
	/**
	 * 查询联系管理员信息
	 * @param contectAdmin
	 * @return
	 */
	List<ContectAdmin> queryContectAdmin(ContectAdmin contectAdmin);
	
}
