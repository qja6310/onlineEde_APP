package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.Teacher;
import cn.com.newloading.bean.TeacherReg;

public interface TeacherRegService {

	/**
	 * 教师注册表,返回结果标识
	 * @param Teacher
	 * @return
	 */
	String registerTea(TeacherReg t);
	
	/**
	 * 审核教师注册
	 * @param adminId
	 * @param TeaRegId
	 * @param auditResult
	 * @param dealExplain
	 * @return
	 */
	String auditTeacherReg(String adminId,String TeaRegId,String auditResult,String dealExplain,String status);
	
	/**
	 * 查询教师表,可用于登录
	 * @return
	 */
	List<TeacherReg> queryTeaReg(TeacherReg tReg);
	
	/**
	 * 修改教师的信息,返回修改成功的条数
	 * @param t
	 * @return
	 */
	Integer editTea(Teacher t);
	
	/**
	 * 删除教师信息，返回删除成功的条数
	 * @param t
	 * @return
	 */
	Integer delTea(Teacher t);
	
}
