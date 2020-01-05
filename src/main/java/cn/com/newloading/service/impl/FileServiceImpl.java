package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.FileBean;
import cn.com.newloading.bean.FileCurriculumLog;
import cn.com.newloading.bean.Teacher;
import cn.com.newloading.bean.TeacherReg;
import cn.com.newloading.dao.FileDao;
import cn.com.newloading.dao.TeacherDao;
import cn.com.newloading.dao.TeacherRegDao;
import cn.com.newloading.enums.AuditStatu;
import cn.com.newloading.enums.RoleType;
import cn.com.newloading.service.FileService;
import cn.com.newloading.service.TeacherRegService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao fDao;

	@Override
	public List<FileBean> queryFiles(String id,String name,String extend,String state,String type) {
		FileBean f=new FileBean();//查询条件
		f.setId(id);
		f.setFname(name);
		f.setFextend(extend);
		f.setFstate(state);
		f.setFtype(type);
		return fDao.queryFile(f);
	}
	
	@Override
	public List<FileBean> selectFileIdByclId(String clId,String state) {
		return fDao.selectFileIdByclId(clId,state);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer addFile(FileBean f) {
		return fDao.addFile(f);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer fileLinkCurriculum(String clId, String fId) {
		FileCurriculumLog f=new FileCurriculumLog();
		f.setClId(clId);
		f.setfId(fId);
		return fDao.fileLinkCurriculum(f);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer checkFile(String fId,String state) {
		FileBean f=new FileBean();
		f.setId(fId);
		f.setFstate(state);//通过 驳回
		String time = TimeUtil.dateToString(new Date());
		f.setFcheckTime(time);
		return fDao.checkFile(f);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer delFile(String fId,String type) {
		FileBean f=new FileBean();
		f.setId(fId);
		f.setFtype(type);//F_TYPE 赋值为“删除”,伪删除
		return fDao.delTea(f);
	}



}
