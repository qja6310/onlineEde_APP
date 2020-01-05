package cn.com.newloading.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.CurriculumLog;
import cn.com.newloading.bean.StudentAttend;
import cn.com.newloading.bean.StudentCurriculumLog;

@Mapper
public interface CurriculumLogDao {

	/**
	 * 发布课程通知
	 * @param c
	 * @return
	 */
	Integer addCurriculumLog(CurriculumLog c);
	
	/**
	 * 查看课程章节
	 * @param tId 教师ID
	 * @param cId 课程ID
	 * @return
	 */
	List<CurriculumLog> queryCurriculumLog(@Param("cId")String cId,@Param("tId")String tId);
	
	/**
	 * 设置作业提交时间
	 * @param c
	 * @return
	 */
	Integer editHomeworkTime(CurriculumLog c);
	
	/**
	 * 学生自主考勤签到
	 * @param scl
	 * @return
	 */
	Integer addStudentCurriculumLog(StudentCurriculumLog scl);
	
	
	
	
	/**
	 * 查询该节课程的所有学生
	 * @param clId
	 * @return
	 */
	List<StudentCurriculumLog> queryStudentOnCourse(@Param("clId")String clId);
	
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
