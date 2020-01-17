package cn.com.newloading.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.CurriculumLog;
import cn.com.newloading.bean.FileBean;
import cn.com.newloading.bean.FileCurriculumLog;
import cn.com.newloading.bean.StudentAttend;
import cn.com.newloading.bean.StudentCurriculumLog;

public interface CurriculumLogService {

	/**
	 * 发布课程通知
	 * @param c
	 * @return
	 */
	Integer addCurriculumLog(CurriculumLog c);
	
	/**
	 * 根据课程ID查询教师ID
	 * @param cId
	 * @return
	 */
	String getTeacherIdBycId(String cId);
	
	/**
	 * 查看课程章节
	 * @param cId 教师ID
	 * @param tId 课程ID
	 * @param sId 学生ID
	 * @return
	 */
	List<CurriculumLog> queryCurriculumLog(String cId,String tId,String sId);
	
	/**
	 * 设置作业最晚提交时间
	 * @param time
	 * @param id 课程记录ID
	 * @return
	 */
	Integer editHomeworkTime(String time,String id);
	
	/**
	 * 学生端：考勤签到
	 * @param sId
	 * @param clId
	 * @param state
	 * @param time
	 * @return
	 */
	Integer addStudentCurriculumLog(String sId, String clId,String state,String time);
	
	
	
	/**
	 * 查询该节课程的所有学生
	 * @param clId
	 * @return
	 */
	List<StudentCurriculumLog> queryStudentOnCourse(String clId);
	
	/**
	 * 学生提交作业,未提交则为空
	 * @param state 作业状态：提交
	 * @param hwScore 作业评分
	 * @param sId  学生ID
	 * @param clId 课程记录ID
	 * @return
	 */
	Integer stuCommitHomework(@Param("state")String state,@Param("hwtime")String hwtime,@Param("sId")String sId,@Param("clId")String clId);
	
	/**
	 * 教师端：学生点名
	 * @param state 正常 迟到 旷课 请假
	 * @param sclId 学生-课程记录ID
	 * @return
	 */
	Integer studentAbsent(@Param("state")String state,@Param("sclId")String sclId);
	
	/**
	 * 教师端：作业线上评分
	 * @param score 分数
	 * @param sclId 学生-课程记录ID
	 * @return
	 */
	Integer studentScore(@Param("score")String score,@Param("sclId")String sclId);
	
	/**
	 * 教师端：统计学生考勤数据
	 * @param cId 课程ID
	 * @return
	 */
	List<StudentAttend> studentAttendCount(@Param("cId")String cId);
}
