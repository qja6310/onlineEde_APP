package cn.com.newloading.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.ContectAdmin;
import cn.com.newloading.bean.Student;
import cn.com.newloading.bean.Teacher;
import cn.com.newloading.bean.dto.ContectAdminDto;
import cn.com.newloading.dao.AdminDao;
import cn.com.newloading.dao.ContectAdminDao;
import cn.com.newloading.dao.StudentDao;
import cn.com.newloading.dao.TeacherDao;
import cn.com.newloading.enums.RoleType;
import cn.com.newloading.service.ContectAdminService;
import cn.com.newloading.utils.StringUtil;
import cn.com.newloading.utils.TimeUtil;

@Service
public class ContectAdminServiceImpl implements ContectAdminService {

	@Autowired
	private ContectAdminDao contectAdminDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private AdminDao adminDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addContectAdmin(ContectAdmin contectAdmin) {
		/*
		 * 判断联系管理员的是否为问题
		 * 如果是问题，那pid为空
		 * 如果不是问题，那就是回复，那pid就是不为空
		 */
		if(null == contectAdmin.getPid() || "".equals(contectAdmin.getPid())) {
			contectAdmin.setType("1");
			contectAdmin.setReplyFlag("0");
		}else {
			contectAdmin.setType("0");
		}
		contectAdmin.setIssueTime(TimeUtil.dateToString(new Date()));
		Integer res = contectAdminDao.addContectAdmin(contectAdmin);
		if(res > 0) {
			/**
			 * 如果是管理员新增消息，将id为pid的数据状态修改一下为有回复
			 */
			if(RoleType.ADM.getRole() .equals(contectAdmin.getForeignType())) {
				contectAdminDao.editContectAdmin(contectAdmin.getPid(), "1");
			}
			return "CONADM0000";
		}
		return "CONADM0001";
	}

	@Override
	public List<ContectAdminDto> queryContectAdmin(ContectAdmin contectAdmin) {
		// TODO Auto-generated method stub
		if(StringUtil.isNotBlank(contectAdmin.getType())) {//系统问题查看
			List<ContectAdmin> contectAdminList = contectAdminDao.queryContectAdmin(contectAdmin);
			List<ContectAdminDto> dtoList = new ArrayList<ContectAdminDto>();
			for (int i = 0; i < contectAdminList.size(); i++) {
				ContectAdmin ca = contectAdminList.get(i);
				ContectAdminDto dto = new ContectAdminDto();
				if(RoleType.STU.getRole().equals(ca.getForeignType())) {
					Student student = studentDao.queryStuById(ca.getForeignId());
					dto.setContectAdmin(ca);
					dto.setRole(student);
					dtoList.add(dto);
				}else if(RoleType.ADM.getRole().equals(ca.getForeignType())){
					Admin admin = adminDao.queryAdminById(ca.getForeignId());
					dto.setContectAdmin(ca);
					dto.setRole(admin);
					dtoList.add(dto);
				}else if(RoleType.TEA.getRole().equals(ca.getForeignType())){
					Teacher teacher = teacherDao.queryTeacherById(ca.getForeignId());
					dto.setContectAdmin(ca);
					dto.setRole(teacher);
					dtoList.add(dto);
				}
			}
			return dtoList;
		}else {//系统问题的记录
			List<ContectAdminDto> dtoList = new ArrayList<ContectAdminDto>();
			ContectAdmin cadm = contectAdminDao.queryContectAdminById(contectAdmin.getPid());
			ContectAdminDto cad = new ContectAdminDto();
			if(RoleType.STU.getRole().equals(cadm.getForeignType())) {
				Student student = studentDao.queryStuById(cadm.getForeignId());
				cad.setContectAdmin(cadm);
				cad.setRole(student);
				dtoList.add(cad);
			}else if(RoleType.ADM.getRole().equals(cadm.getForeignType())){
				Admin admin = adminDao.queryAdminById(cadm.getForeignId());
				cad.setContectAdmin(cadm);
				cad.setRole(admin);
				dtoList.add(cad);
			}else if(RoleType.TEA.getRole().equals(cadm.getForeignType())){
				Teacher teacher = teacherDao.queryTeacherById((cadm.getForeignId()));
				cad.setContectAdmin(cadm);
				cad.setRole(teacher);
				dtoList.add(cad);
			}
			
			List<ContectAdmin> contectAdminList = contectAdminDao.queryContectAdmin(contectAdmin);
			for (int i = 0; i < contectAdminList.size(); i++) {
				ContectAdmin ca = contectAdminList.get(i);
				ContectAdminDto dto = new ContectAdminDto();
				if(RoleType.STU.getRole().equals(ca.getForeignType())) {
					Student student = studentDao.queryStuById(ca.getForeignId());
					dto.setContectAdmin(ca);
					dto.setRole(student);
					dtoList.add(dto);
				}else if(RoleType.ADM.getRole().equals(ca.getForeignType())){
					Admin admin = adminDao.queryAdminById(ca.getForeignId());
					dto.setContectAdmin(ca);
					dto.setRole(admin);
					dtoList.add(dto);
				}else if(RoleType.TEA.getRole().equals(ca.getForeignType())){
					Teacher teacher = teacherDao.queryTeacherById((cadm.getForeignId()));
					cad.setContectAdmin(ca);
					cad.setRole(teacher);
					dtoList.add(cad);
				}
			}
			return dtoList;
		}
		
	}

}
