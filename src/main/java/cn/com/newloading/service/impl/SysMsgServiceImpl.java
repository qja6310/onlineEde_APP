package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.SysMessage;
import cn.com.newloading.dao.SysMessageDao;
import cn.com.newloading.service.SysMsgService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class SysMsgServiceImpl implements SysMsgService{
	
	@Autowired
	private SysMessageDao sysMsgDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addSysMsg(String title, String content, String adminId) {
		//构建系统消息对象
		SysMessage sysMsg = new SysMessage();
		sysMsg.setTitle(title);
		sysMsg.setContent(content);
		sysMsg.setAdminId(adminId);
		sysMsg.setIssueTime(TimeUtil.dateToString(new Date()));
		Integer ret = sysMsgDao.addSysMessage(sysMsg);
		if(ret > 0) {
			return "SYSMSG0000";
		}else {
			return "SYSMSG0001";
		}
	}

	@Override
	public List<SysMessage> querySysMsg(String title, String content) {
		SysMessage sysMsg = new SysMessage();
		sysMsg.setTitle(title);
		sysMsg.setContent(content);
		return sysMsgDao.querySysMsg(sysMsg);
	}

	@Override
	public String delSysMsg(String sysMsgId) {
		Integer ret = sysMsgDao.delSysMsg(sysMsgId);
		if(ret > 0) {
			return "SYSMSG0005";
		}else {
			return "SYSMSG0006";
		}
	}

}
