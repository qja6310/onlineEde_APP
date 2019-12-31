package cn.com.newloading.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Curriculum;
import cn.com.newloading.bean.CurriculumLinkStudent;
import cn.com.newloading.bean.Teacher;
import cn.com.newloading.bean.dto.CurriculumDto;
import cn.com.newloading.dao.CurriculumDao;
import cn.com.newloading.dao.TeacherDao;
import cn.com.newloading.service.CurriculumService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class CurriculumServiceImpl implements CurriculumService {

	@Autowired
	private CurriculumDao curriculumDao;
	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addCurriculum(Curriculum curriculum) {
		// TODO Auto-generated method stub
		curriculum.setCreateTime(TimeUtil.dateToString(new Date()));
		Integer ret = curriculumDao.addCurriculum(curriculum);
		if(ret > 0) {
			return "KC0000";
		}else {
			return "KC0001";
		}
	}

	@Override
	public List<Curriculum> queryCurriculumForTeacher(Curriculum curriculum) {
		// TODO Auto-generated method stub
		return curriculumDao.queryCurriculumForTeacher(curriculum.getTeacherId());
	}

	@Override
	public List<CurriculumDto> queryCurriculumForStudent(String stuId) {
		// TODO Auto-generated method stub
		List<Curriculum> cuList = curriculumDao.queryCurriculumForStudent(stuId);
		List<CurriculumDto> dtoList = new ArrayList<CurriculumDto>();
		if(cuList != null) {
			for (int i = 0; i < cuList.size(); i++) {
				//课程
				Curriculum cu = cuList.get(i);
				//教师
				Teacher teacher = teacherDao.queryTeacherById(cu.getTeacherId());
				CurriculumDto dto = new CurriculumDto();
				dto.setCurriculum(cu);
				dto.setTeacher(teacher);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Override
	public List<CurriculumDto> queryCurriculumForS(String stuId) {
		// TODO Auto-generated method stub
		List<Curriculum> cuList = curriculumDao.queryCurriculumForS(stuId);
		List<CurriculumDto> dtoList = new ArrayList<CurriculumDto>();
		if(cuList != null) {
			for (int i = 0; i < cuList.size(); i++) {
				//课程
				Curriculum cu = cuList.get(i);
				//教师
				Teacher teacher = teacherDao.queryTeacherById(cu.getTeacherId());
				CurriculumDto dto = new CurriculumDto();
				dto.setCurriculum(cu);
				dto.setTeacher(teacher);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Override
	public String sureCurriculum(String cuId, String stuId) {
		CurriculumLinkStudent cls = new CurriculumLinkStudent();
		cls.setCuId(cuId);
		cls.setStuId(stuId);
		cls.setTime(TimeUtil.dateToString(new Date()));
		Integer res = curriculumDao.sureCurriculum(cls);
		if(res > 0) {
			return "KC0000";
		}else {
			return "KC0001";
		}
	}
}
