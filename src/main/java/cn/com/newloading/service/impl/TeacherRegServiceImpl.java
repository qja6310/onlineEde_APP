package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.Teacher;
import cn.com.newloading.bean.TeacherReg;
import cn.com.newloading.dao.TeacherDao;
import cn.com.newloading.dao.TeacherRegDao;
import cn.com.newloading.enums.AuditStatu;
import cn.com.newloading.enums.RoleType;
import cn.com.newloading.service.TeacherRegService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class TeacherRegServiceImpl implements TeacherRegService {

	@Autowired
	private TeacherRegDao tRegDao;
	@Autowired
	private TeacherDao tDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String registerTea(Teacher t) {
//		TeacherReg TeacherReg = (TeacherReg) teacher;
		// 查询注册登陆账号是否可用
		List<TeacherReg> list = tRegDao.queryTeaRegByParms(t.getTeaPhone(), null, null);
		if (list.size() > 0) {// 手机号已被注册
			return "REGTEA0001";// 表示账号已被注册过
		}
		// 查询该职工号是否已被注册
		list.clear();
		list = tRegDao.queryTeaRegByParms(null, t.getTeaNumber(), null);
		if (list.size() > 0) {
			return "REGTEA0003";// 职工号已被注册过
		}
		// 查询该邮箱是否已被注册
		list.clear();
		list = tRegDao.queryTeaRegByParms(null, null, t.getTeaEmail());
		if (list.size() > 0) {
			return "REGTEA0008";// 邮箱已被注册过
		}
		TeacherReg tReg = new TeacherReg();
		tReg.setStatus(AuditStatu.PENDING.getP());
		tRegDao.registerTeaReg(tReg);//TODO 注册到教师注册表,新增成功的id映射回对象
		TeacherReg sr = new TeacherReg();
		sr.setId(tReg.getId());
		list.clear();
		list = tRegDao.queryTeaReg(sr);
		if (list == null || list.size() == 0) {// 注册失败
			return "REGTEA0002";
		} else {// 注册提交成功
			return "REGTEA0000";
		}
	}

	@Override
	public List<TeacherReg> queryTeaReg(TeacherReg tReg) {
		return tRegDao.queryTeaReg(tReg);
	}

	@Override
	public Integer editTea(Teacher t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delTea(Teacher t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String auditTeacherReg(String adminId, String tRegId, String auditResult, String dealExplain,
			String status) {
		TeacherReg tReg = new TeacherReg();
		tReg.setId(tRegId);
		// 查询出最新的注册信息,确认没被处理过
		List<TeacherReg> list = tRegDao.queryTeaReg(tReg);
		if (list == null || list.size() == 0) {
			return "AUDIT0008";
		}
		tReg = list.get(0);

		// 判断是否审核通过
		if (AuditStatu.PASS.getP().equals(status)) {
			// 新增教师
			Teacher t = new Teacher();
			t.setRole(RoleType.TEA.getRole());
			Integer teaId = tDao.addTea(t);
			if (teaId == null || teaId <= 0) {
				return "AUDIT0009";
			}
		}

		// 修改教师注册表
		tReg.setId(tRegId);
		tReg.setAdminId(adminId);
		tReg.setAuditResult(auditResult);
		tReg.setDealExplain(dealExplain);
		tReg.setAuditTime(TimeUtil.dateToString(new Date()));
		tReg.setStatus(status);
		Integer res =tRegDao.editTeacherReg(tReg);
		if (res > 0) {
			return "AUDIT0010";// 修改成功
		} else {
			return "AUDIT0011";// 修改失败
		}
	}

}
