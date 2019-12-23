package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.SysMessage;

@Mapper
public interface SysMessageDao {

	/**
	 * 新增系统消息
	 * @param sysMessage
	 * @return
	 */
	Integer addSysMessage(SysMessage sysMessage);
	
	/**
	 * 查询系统消息
	 * @param sysMessage
	 * @return
	 */
	List<SysMessage> querySysMsg(SysMessage sysMessage);
	
	/**
	 * 删除系统消息
	 * @param sysMsgId
	 * @return
	 */
	Integer delSysMsg(@Param("sysMsgId")String sysMsgId);
	
}
