package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.StudentReg;

@Mapper
public interface StudentRegDao {

	/**
	 * 学生注册表,返回id
	 * @param student
	 * @return
	 */
	Integer registerStuReg(StudentReg studentReg);
	
	/**
	 * 查询学生注册表
	 * @return
	 */
	List<StudentReg> queryStuReg(StudentReg studentReg);
	
	/**
	 * 查询学号是否有重复，号码是否有重复，邮箱是否有重复
	 * @param stuAccount
	 * @param stuStudyNumber
	 * @return
	 */
	List<StudentReg> queryStuRegByParms(@Param("stuPhone")String stuPhone,@Param("stuStudyNumber")String stuStudyNumber,@Param("stuEmail")String stuEmail);
	
	/**
	 * 编辑studentReg
	 * @param studentReg
	 * @return
	 */
	Integer editStudentReg(StudentReg studentReg);
	
}
