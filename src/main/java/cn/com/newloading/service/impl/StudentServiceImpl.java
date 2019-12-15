package cn.com.newloading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Student;
import cn.com.newloading.bean.StudentReg;
import cn.com.newloading.dao.StudentRegDao;
import cn.com.newloading.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRegDao studentRegDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public StudentReg registerStu(Student student) {
		StudentReg studentReg = (StudentReg) student;
		//查询注册登陆账号是否可用
		List<StudentReg> stuList = studentRegDao.queryStu(studentReg);
		if(stuList.size() > 0) {//登陆账号不可注册
			StudentReg s = new StudentReg();
			s.setId("REGSTU0002");//表示账号已被注册过
			return s;
		}
		Integer stuId = studentRegDao.registerStu(studentReg);//注册到学生注册表
		StudentReg sr = new StudentReg();
		sr.setId(String.valueOf(stuId));
		stuList.clear();
		stuList = studentRegDao.queryStu(sr);
		if(stuList == null || stuList.size() == 0) {//注册失败
			studentReg = null;
		}else {//注册提交成功
			studentReg = stuList.get(0);
		}
		return studentReg;
	}

	@Override
	public List<Student> queryStu(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer editStu(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delStu(Student student) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
