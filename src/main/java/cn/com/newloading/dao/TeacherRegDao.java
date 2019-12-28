package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.TeacherReg;

@Mapper
public interface TeacherRegDao {

	/**
	 * 注册教师,返回id
	 * @param tReg
	 * @return
	 */
	Integer registerTeaReg(TeacherReg tReg);
	
	/**
	 * 查询教师申请信息
	 * @param tReg
	 * @return
	 */
	List<TeacherReg> queryTeaReg(TeacherReg tReg);
	
	/**
	 * 查询电话、职工号、邮箱是否有重复
	 * @param teaPhone
	 * @param teaNumber
	 * @param teaEmail
	 * @return
	 */
	List<TeacherReg> queryTeaRegByParms(@Param("teaPhone")String teaPhone,@Param("teaNumber")String teaNumber,@Param("teaEmail")String teaEmail);
	
	/**
	 * 编辑教师
	 * @param tReg
	 * @return
	 */
	Integer editTeacherReg(TeacherReg tReg);
	
}
