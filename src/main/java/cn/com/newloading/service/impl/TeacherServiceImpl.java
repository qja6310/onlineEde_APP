package cn.com.newloading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.newloading.bean.Teacher;
import cn.com.newloading.dao.TeacherDao;
import cn.com.newloading.service.TeacherService;
import cn.com.newloading.utils.StringUtil;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao tDao;
	
	@Override
	public Teacher TeacherLogin(String teaNumber, String teaPassword) {
		Teacher teacher = new Teacher();
		teacher.setTeaNumber(teaNumber);
		teacher.setTeaPassword(teaPassword);
		List<Teacher> list = tDao.queryTea(teacher);
		
		if(list != null && list.size() > 0) {
			teacher = list.get(0);
			if(!teaNumber.equals(teacher.getTeaNumber()) || !teaPassword.equals(teacher.getTeaPassword())) {
				return null;
			}
			return teacher;
		}
		return null;
	}

	@Override
	public Teacher findTeacher(String tNumber, String tPhone, String tEmail) {
		Teacher t = new Teacher();
		t.setTeaNumber(tNumber);
		if(StringUtil.isBlank(tPhone)) {
			t.setTeaPhone(tPhone);
		}
		if(StringUtil.isBlank(tEmail)) {
			t.setTeaEmail(tEmail);
		}
		List<Teacher> stuList = tDao.queryTea(t);
		if(stuList != null && stuList.size() > 0) {
			t = stuList.get(0);
			if(!tNumber.equals(t.getTeaNumber())) {
				return null;
			}
			if(StringUtil.isNotBlank(tPhone) && !tPhone.equals(t.getTeaPhone())) {
				return null;
			}
			if(StringUtil.isNotBlank(tEmail) && !tEmail.equals(t.getTeaEmail())) {
				return null;
			}
			return t;
		}
		return null;
	}

}
