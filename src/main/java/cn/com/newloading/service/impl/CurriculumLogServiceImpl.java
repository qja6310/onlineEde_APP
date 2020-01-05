package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.CurriculumLog;
import cn.com.newloading.bean.StudentAttend;
import cn.com.newloading.bean.StudentCurriculumLog;
import cn.com.newloading.dao.CurriculumLogDao;
import cn.com.newloading.service.CurriculumLogService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class CurriculumLogServiceImpl implements CurriculumLogService {

	@Autowired
	private CurriculumLogDao dao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer addCurriculumLog(CurriculumLog c) {
		return dao.addCurriculumLog(c);
	}

	@Override
	public List<CurriculumLog> queryCurriculumLog(String cId, String tId) {
		return dao.queryCurriculumLog(cId, tId);
	}

	@Override
	public Integer editHomeworkTime(String time, String id) {
		CurriculumLog c=new CurriculumLog();
		c.setTaskTime(time);
		c.setId(id);
		return dao.editHomeworkTime(c);
	}

	@Override
	public Integer addStudentCurriculumLog(String sId, String clId,String state,String time) {
		StudentCurriculumLog scl=new StudentCurriculumLog();
		scl.setsId(sId);
		scl.setClId(clId);
		scl.setState(state);
		scl.setTime(time);
		return dao.addStudentCurriculumLog(scl);
	}

	
	@Override
	public List<StudentCurriculumLog> queryStudentOnCourse(String clId) {
		return dao.queryStudentOnCourse(clId);
	}

	@Override
	public Integer stuCommitHomework(String state, String hwtime, String sId, String clId) {
		return dao.stuCommitHomework(state, hwtime, sId, clId);
	}

	@Override
	public Integer studentAbsent(String state, String sclId) {
		return dao.studentAbsent(state, sclId);
	}

	@Override
	public Integer studentScore(String score, String sclId) {
		return dao.studentScore(score, sclId);
	}

	@Override
	public List<StudentAttend> studentAttendCount(String cId) {
		return dao.studentAttendCount(cId);
	}

	
	
	
	
	
	
	
	




}
