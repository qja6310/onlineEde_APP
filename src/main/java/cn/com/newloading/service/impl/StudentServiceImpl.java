package cn.com.newloading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.newloading.bean.Student;
import cn.com.newloading.dao.StudentDao;
import cn.com.newloading.service.StudentService;
import cn.com.newloading.utils.StringUtil;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Student studentLogin(String stuStudyNumber, String stuPassword) {
		Student student = new Student();
		student.setStuStudyNumber(stuStudyNumber);
		student.setStuPassword(stuPassword);
		List<Student> stuList = studentDao.queryStu(student);
		if(stuList != null && stuList.size() > 0) {
			student = stuList.get(0);
			if(!stuStudyNumber.equals(student.getStuStudyNumber()) || !stuPassword.equals(student.getStuPassword())) {
				return null;
			}
			return student;
		}
		return null;
	}

	@Override
	public Student findStudent(String stuStudyNumber, String stuPhone, String stuEmail) {
		Student student = new Student();
		student.setStuStudyNumber(stuStudyNumber);
		if(StringUtil.isBlank(stuPhone)) {
			student.setStuPhone(stuPhone);
		}
		if(StringUtil.isBlank(stuEmail)) {
			student.setStuEmail(stuEmail);
		}
		List<Student> stuList = studentDao.queryStu(student);
		if(stuList != null && stuList.size() > 0) {
			student = stuList.get(0);
			if(!stuStudyNumber.equals(student.getStuStudyNumber())) {
				return null;
			}
			if(StringUtil.isNotBlank(stuPhone) && !stuPhone.equals(student.getStuPhone())) {
				return null;
			}
			if(StringUtil.isNotBlank(stuEmail) && !stuEmail.equals(student.getStuEmail())) {
				return null;
			}
			return student;
		}
		return null;
	}

}
