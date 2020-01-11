package cn.com.newloading.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.FileBean;
import cn.com.newloading.bean.FileCurriculumLog;

public interface FileService {

	/**
	 * 文件浏览
	 * @param id
	 * @param name
	 * @param extend
	 * @param state
	 * @param type 课件 作业
	 * @return
	 */
	List<FileBean> queryFiles(String id,String name,String extend,String state,String type);
	
	
//	/**
//	 * 查看学生作业
//	 * @param id
//	 * @return
//	 */
//	List<FileBean> queryStudentWorks(String id);
	
	/**
	 * 查看该节课程的文件;查看学生作业
	 * @param cId
	 * @param state 待审核 、通过 、  驳回 、 学生
	 * @return
	 */
	List<FileBean> selectFileIdByclId(String cId,String state);
	
	/**
	 * 文件上传
	 * @param f
	 * @return
	 */
	Integer addFile(FileBean f);
	
	/**
	 * 上传的文件，插入文件课程日志关联
	 * @param clId
	 * @param fId
	 * @return
	 */
	Integer fileLinkCurriculum(String clId,String fId);
	
	/**
	 * 审核文件
	 * @param fId
	 * @param state
	 * @return
	 */
	Integer checkFile(String fId,String state);
	
	/**
	 * 删除文件
	 * @param fId
	 * @param type
	 * @return
	 */
	Integer delFile(String fId,String type);
	
	/**
	 * 根据uuId返回文件Id
	 * @param fuuid
	 * @return
	 */
	String getFileId(String fuuid);
}
