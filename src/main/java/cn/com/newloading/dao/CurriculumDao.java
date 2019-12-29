package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.Curriculum;
import cn.com.newloading.bean.CurriculumLinkStudent;

@Mapper
public interface CurriculumDao {

	/**
	 * 新增课程
	 * @param curriculum
	 * @return
	 */
	Integer addCurriculum(Curriculum curriculum);
	
	/**
	 * 查询课程
	 * @param tId
	 * @return
	 */
	List<Curriculum> queryCurriculumForTeacher(@Param("tId")String tId);
	
	/**
	 * 学生查询课程，不包括自己已选择好的课程
	 * @param stuId
	 * @return
	 */
	List<Curriculum> queryCurriculumForStudent(@Param("stuId")String stuId);
	
	/**
	 * 学生查询自己已选择好的课程
	 * @param stuId
	 * @return
	 */
	List<Curriculum> queryCurriculumForS(@Param("stuId")String stuId);
	
	/**
	 * 学生确认课程
	 * @param cls
	 * @return
	 */
	Integer sureCurriculum(CurriculumLinkStudent cls);
}
