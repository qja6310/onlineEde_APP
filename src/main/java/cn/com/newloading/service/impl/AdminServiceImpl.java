package cn.com.newloading.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.dao.AdminDao;
import cn.com.newloading.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Admin adminLogin(String admAccount, String admPassword) {
		// TODO Auto-generated method stub
		return adminDao.adminLogin(admAccount, admPassword);
	}

}
