package cn.com.newloading.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.SysMessage;

public interface SysMsgService {

	/**
	 * 新增系统消息
	 * @param title
	 * @param content
	 * @param adminId
	 * @return
	 */
	String addSysMsg(String title,String content,String adminId);
	
	/**
	 * 查询系统消息
	 * @param title
	 * @param content
	 * @return
	 */
	List<SysMessage> querySysMsg(String title,String content);
	
	/**
	 * 删除系统消息
	 * @param sysMsgId
	 * @return
	 */
	String delSysMsg(String sysMsgId);
	
	
}
