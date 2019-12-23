package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.ContectAdmin;
import cn.com.newloading.dao.ContectAdminDao;
import cn.com.newloading.enums.RoleType;
import cn.com.newloading.service.ContectAdminService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class ContectAdminServiceImpl implements ContectAdminService {

	@Autowired
	private ContectAdminDao contectAdminDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addContectAdmin(ContectAdmin contectAdmin) {
		/*
		 * 判断联系管理员的是否为问题
		 * 如果是问题，那pid为空
		 * 如果不是问题，那就是回复，那pid就是不为空
		 */
		if(null == contectAdmin.getPid() || "".equals(contectAdmin.getPid())) {
			contectAdmin.setType("1");
			contectAdmin.setReplyFlag("0");
		}else {
			contectAdmin.setType("0");
		}
		contectAdmin.setIssueTime(TimeUtil.dateToString(new Date()));
		Integer res = contectAdminDao.addContectAdmin(contectAdmin);
		if(res > 0) {
			/**
			 * 如果是管理员新增消息，将id为pid的数据状态修改一下为有回复
			 */
			if(RoleType.ADM.getRole() .equals(contectAdmin.getForeignType())) {
				contectAdminDao.editContectAdmin(contectAdmin.getPid(), "1");
			}
			return "CONADM0000";
		}
		return "CONADM0001";
	}

	@Override
	public List<ContectAdmin> queryContectAdmin(ContectAdmin contectAdmin) {
		// TODO Auto-generated method stub
		return contectAdminDao.queryContectAdmin(contectAdmin);
	}

}
