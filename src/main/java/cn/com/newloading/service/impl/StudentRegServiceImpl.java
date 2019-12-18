package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Student;
import cn.com.newloading.bean.StudentReg;
import cn.com.newloading.dao.StudentDao;
import cn.com.newloading.dao.StudentRegDao;
import cn.com.newloading.enums.AuditStatu;
import cn.com.newloading.service.StudentRegService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class StudentRegServiceImpl implements StudentRegService {

	@Autowired
	private StudentRegDao studentRegDao;
	@Autowired
	private StudentDao studentDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String registerStu(Student student) {
		StudentReg studentReg = (StudentReg) student;
		// 查询注册登陆账号是否可用
		List<StudentReg> stuList = studentRegDao.queryStuRegByParms(student.getStuPhone(), null, null);
		if (stuList.size() > 0) {// 手机号已被注册
			return "REGSTU0001";// 表示账号已被注册过
		}
		// 查询该学号是否已被注册
		stuList.clear();
		stuList = studentRegDao.queryStuRegByParms(null, student.getStuStudyNumber(), null);
		if (stuList.size() > 0) {
			return "REGSTU0003";// 学号已被注册过
		}
		// 查询该邮箱是否已被注册
		stuList.clear();
		stuList = studentRegDao.queryStuRegByParms(null, null, student.getStuEmail());
		if (stuList.size() > 0) {
			return "REGSTU0008";// 邮箱已被注册过
		}
		studentReg.setStatus(AuditStatu.PENDING.getP());
		studentRegDao.registerStuReg(studentReg);// 注册到学生注册表,新增成功的id映射回对象
		StudentReg sr = new StudentReg();
		sr.setId(studentReg.getId());
		stuList.clear();
		stuList = studentRegDao.queryStuReg(sr);
		if (stuList == null || stuList.size() == 0) {// 注册失败
			return "REGSTU0002";
		} else {// 注册提交成功
			return "REGSTU0000";
		}
	}

	@Override
	public List<StudentReg> queryStuReg(StudentReg studentReg) {
		return studentRegDao.queryStuReg(studentReg);
	}

	@Override
	public Integer editStu(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delStu(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String auditStudentReg(String adminId, String stuRegId, String auditResult, String dealExplain,
			String status) {
		StudentReg studentReg = new StudentReg();
		studentReg.setId(stuRegId);
		// 查询出最新的注册信息,确认没被处理过
		List<StudentReg> stuRegList = studentRegDao.queryStuReg(studentReg);
		if (stuRegList == null || stuRegList.size() == 0) {
			return "AUDIT0008";
		}
		studentReg = stuRegList.get(0);

		// 判断是否审核通过
		if (AuditStatu.PASS.getP().equals(status)) {
			// 新增学生
			Student student = studentReg;
			Integer stuId = studentDao.addStu(student);
			if (stuId == null || stuId <= 0) {
				return "AUDIT0009";
			}
		}

		// 修改学生注册表
		studentReg.setId(stuRegId);
		studentReg.setAdminId(adminId);
		studentReg.setAuditResult(auditResult);
		studentReg.setDealExplain(dealExplain);
		studentReg.setAuditTime(TimeUtil.dateToString(new Date()));
		studentReg.setStatus(status);
		Integer res = studentRegDao.editStudentReg(studentReg);
		if (res > 0) {
			return "AUDIT0010";// 修改成功
		} else {
			return "AUDIT0011";// 修改失败
		}
	}

}
