package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.ContectAdmin;

@Mapper
public interface ContectAdminDao {

	/**
	 * 新增联系管理员信息
	 * @param contectAdmin
	 * @return
	 */
	Integer addContectAdmin(ContectAdmin contectAdmin);
	
	/**
	 * 修改回复标记
	 * @param caId
	 * @param replayFlag
	 * @return
	 */
	Integer editContectAdmin(@Param("caId")String caId,@Param("replyFlag")String replyFlag);
	
//	/**
//	 * 通过类型(问题还是回复)查询联系管理员消息
//	 * @param type
//	 * @return
//	 */
//	List<ContectAdmin> queryContectAdminByType(@Param("type")String type);
//	
//	
//	/**
//	 * 通过外键类型查询联系管理员消息
//	 * @param foreignType
//	 * @return
//	 */
//	List<ContectAdmin> queryContectAdminByForeignType(@Param("foreignType")String foreignType);
	
	/**
	 * 查询联系管理员信息
	 * @param contectAdmin
	 * @return
	 */
	List<ContectAdmin> queryContectAdmin(ContectAdmin contectAdmin);
	
	/**
	 * 
	 * @param caId
	 * @return
	 */
	ContectAdmin queryContectAdminById(@Param("caId")String caId);
}
