package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.Student;
import cn.com.newloading.bean.StudentReg;

public interface StudentService {

	/**
	 * 学生注册表,返回id
	 * @param student
	 * @return
	 */
	StudentReg registerStu(Student student);
	
	/**
	 * 查询学生表,可用于登录
	 * @return
	 */
	List<Student> queryStu(Student student);
	
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
	
}
