package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.Discuss;

@Mapper
public interface DiscussDao {

	/**
	 * 新增评论
	 * @param discuss
	 * @return
	 */
	Integer addDiscuss(Discuss discuss);
	
	/**
	 * 根据课程id获取评论区内容
	 * @param cuId
	 * @return
	 */
	List<Discuss> queryDiscussByCuId(@Param("cuId")String cuId);
	
	/**
	 * 删除讨论区的内容
	 * @param disId
	 * @return
	 */
	Integer delDiscuss(@Param("disId") String disId);
	
}
