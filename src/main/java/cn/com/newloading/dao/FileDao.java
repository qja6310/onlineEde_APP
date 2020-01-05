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
	
	/**
	 * 查看学生作业
	 * @param id
	 * @return
	 */
	List<FileBean> queryStudentWorks(@Param("id")String id);
	
	/**
	 * 根据课程记录ID获取文件ID
	 * @param cId
	 * @param type
	 * @return
	 */
	List<String> selectFileIdByclId(@Param("cId")String cId,@Param("type")String type);
	
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
