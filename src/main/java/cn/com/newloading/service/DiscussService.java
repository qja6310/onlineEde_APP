package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.Discuss;
import cn.com.newloading.bean.dto.DiscussDto;

public interface DiscussService {
	
	/**
	 * 新增评论
	 * @param discuss
	 * @return
	 */
	String addDiscuss(Discuss discuss);
	
	/**
	 * 根据课程id查询记录
	 * @param cuId
	 * @return
	 */
	List<DiscussDto> queryDiscussByCuId(String cuId);
	
	/**
	 * 删除评论
	 * @param disId
	 * @return
	 */
	String delDiscuss(String disId);
	
}
