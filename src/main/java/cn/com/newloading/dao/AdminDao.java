package cn.com.newloading.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.Admin;

@Mapper
public interface AdminDao {

	/**
	 * 管理员登陆
	 * @param admin
	 * @return
	 */
	Admin adminLogin(@Param("admAccount")String admAccount,@Param("admPassword")String admPassword);
	
}
