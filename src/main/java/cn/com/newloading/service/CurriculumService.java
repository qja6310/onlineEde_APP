package cn.com.newloading.service;

import java.util.List;

import cn.com.newloading.bean.Curriculum;
import cn.com.newloading.bean.dto.CurriculumDto;

public interface CurriculumService {

	/**
	 * 新增课程
	 * @param curriculum
	 * @return
	 */
	String addCurriculum(Curriculum curriculum);
	
	/**
	 * 老师查询课程
	 * @param curriculum
	 * @return
	 */
	List<Curriculum> queryCurriculumForTeacher(Curriculum curriculum);
	
	/**
	 * 学生查询未选择的课程
	 * @param curriculum
	 * @return
	 */
	List<CurriculumDto> queryCurriculumForStudent(String stuId);
	
	/**
	 * 学生已选择的课程
	 * @param curriculum
	 * @return
	 */
	List<CurriculumDto> queryCurriculumForS(String stuId);
	
	/**
	 * 学生确认课程
	 * @param cuId
	 * @param stuId
	 * @return
	 */
	String sureCurriculum(String cuId,String stuId);
	
	/**
	 * 管理员看到的课程
	 * @param curriculum
	 * @return
	 */
	List<CurriculumDto> queryCurriculumForA();
}
