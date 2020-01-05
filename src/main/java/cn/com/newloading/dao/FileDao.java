package cn.com.newloading.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.FileBean;
import cn.com.newloading.bean.FileCurriculumLog;

@Mapper
public interface FileDao {

	/**
	 * 文件浏览
	 * @param f
	 * @return
	 */
	List<FileBean> queryFile(FileBean f);
	
//	/**
//	 * 查看学生作业
//	 * @param id
//	 * @return
//	 */
//	List<FileBean> queryStudentWorks(@Param("id")String id);

	/**
	 * 查看该节课程的文件;查看学生作业
	 * @param clId
	 * @param fstate 待审核 、通过 、  驳回 、 学生
	 * @return
	 */
	List<FileBean> selectFileIdByclId(@Param("clId")String clId,@Param("fstate")String fstate);
	
	/**
	 * 教师发布文件
	 * @param f
	 * @return
	 */
	Integer addFile(FileBean f);

	/**
	 * 上传的文件，插入文件课程关联
	 * @param f
	 * @return
	 */
	Integer fileLinkCurriculum(FileCurriculumLog f);
	
	/**
	 * 审核文件
	 * @param f
	 * @return
	 */
	Integer checkFile(FileBean f);
	
	/**
	 * 删除文件,逻辑删除
	 * @param type
	 * @return
	 */
	Integer delTea(FileBean f);
	
}
