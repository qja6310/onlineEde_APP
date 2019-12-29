package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Curriculum;
import cn.com.newloading.bean.CurriculumLinkStudent;
import cn.com.newloading.dao.CurriculumDao;
import cn.com.newloading.service.CurriculumService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class CurriculumServiceImpl implements CurriculumService {

	@Autowired
	private CurriculumDao curriculumDao;
	
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
	public List<Curriculum> queryCurriculumForStudent(String stuId) {
		// TODO Auto-generated method stub
		return curriculumDao.queryCurriculumForStudent(stuId);
	}

	@Override
	public List<Curriculum> queryCurriculumForS(String stuId) {
		// TODO Auto-generated method stub
		return curriculumDao.queryCurriculumForS(stuId);
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
