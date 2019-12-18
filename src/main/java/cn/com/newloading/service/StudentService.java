package cn.com.newloading.service;

import cn.com.newloading.bean.Student;

public interface StudentService {

	/**
	 * 学生登录
	 * @param stuStudyNumber
	 * @param stuPassword
	 * @return
	 */
	Student studentLogin(String stuStudyNumber,String stuPassword);
	
	/**
	 * 找回学生账号
	 * @param stuStudyNumber
	 * @param stuPhone
	 * @param stuEmail
	 * @return
	 */
	Student findStudent(String stuStudyNumber,String stuPhone,String stuEmail);
	
}
