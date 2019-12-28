package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.Student;

@Mapper
public interface StudentDao {

	/**
	 * 查询学生表
	 * @return
	 */
	List<Student> queryStu(Student student);
	
	/**
	 * 通过id查找学生
	 * @param stuId
	 * @return
	 */
	Student queryStuById(@Param("stuId") String stuId);
	
	/**
	 * 修改学生的信息,返回修改成功的条数
	 * @param student
	 * @return
	 */
	Integer editStu(Student student);
	
	/**
	 * 删除学生信息，返回删除成功的条数
	 * @param student
	 * @return
	 */
	Integer delStu(Student student);
	
	/**
	 * 新增学生
	 * @param student
	 * @return
	 */
	Integer addStu(Student student);
}
