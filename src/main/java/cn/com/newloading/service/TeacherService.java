package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.Teacher;

public interface TeacherService {

	/**
	 * 教师登录
	 * @param teaNumber
	 * @param teaPassword
	 * @return
	 */
	Teacher TeacherLogin(String teaNumber,String teaPassword);
	
	/**
	 * 找回教师账号
	 * @param teaNumber
	 * @param teaPhone
	 * @param teaEmail
	 * @return
	 */
	Teacher findTeacher(String teaNumber,String teaPhone,String teaEmail);
	
}
