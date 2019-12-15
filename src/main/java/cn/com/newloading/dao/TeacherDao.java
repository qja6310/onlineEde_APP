package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.com.newloading.bean.Teacher;

@Mapper
public interface TeacherDao {

	/**
	 * 注册老师信息，返回id
	 * @param teacher
	 * @return
	 */
	String registerTea(Teacher teacher);
	
	/**
	 * 查询老师信息
	 * @param teacher
	 * @return
	 */
	List<Teacher> queryTea(Teacher teacher);
	
	/**
	 * 修改老师信息，返回成功的数量
	 * @param teacher
	 * @return
	 */
	Integer editTea(Teacher teacher);
	
	/**
	 * 删除老师信息，返回删除成功的数量
	 * @param teacher
	 * @return
	 */
	Integer delTea(Teacher teacher);
}
