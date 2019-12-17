package cn.com.newloading.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.Dict;
import cn.com.newloading.bean.StudentReg;
import cn.com.newloading.enums.AuditStatu;
import cn.com.newloading.service.DictService;
import cn.com.newloading.service.StudentRegService;
import cn.com.newloading.utils.StringUtil;
import cn.com.newloading.utils.TimeUtil;

@Controller
@RequestMapping("/studentReg")
public class StudentRegController {

	@Autowired
	private StudentRegService studentRegService;
	@Autowired
	private DictService dictService;

	/**
	 * 学生注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/registerStu")
	@ResponseBody
	public JSONObject registerStu(HttpServletRequest request) {
		String stuName = request.getParameter("stuName");// 姓名
		if (StringUtil.isBlank(stuName)) {
			return responseMsg("REGSTU0004","REGSTU");
		}
		String stuAccount = request.getParameter("stuAccount");// 账号
		if (StringUtil.isBlank(stuName)) {
			return responseMsg("REGSTU0005","REGSTU");
		}
		String stuPassword = request.getParameter("stuPassword");// 密码
		if (StringUtil.isBlank(stuName)) {
			return responseMsg("REGSTU0006","REGSTU");
		}
		String stuStudyNumber = request.getParameter("stuStudyNumber");// 学号
		if (StringUtil.isBlank(stuName)) {
			return responseMsg("REGSTU0007","REGSTU");
		}
		StudentReg studentReg = new StudentReg();
		studentReg.setStuName(stuName);
		studentReg.setStuAccount(stuAccount);
		studentReg.setStuPassword(stuPassword);
		studentReg.setStuStudyNumber(stuStudyNumber);
		String regTime = TimeUtil.dateToString(new Date());
		studentReg.setRegTime(regTime);
		String res = studentRegService.registerStu(studentReg);
		return responseMsg(res,"REGSTU");
	}

	/**
	 * 查询学生注册列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryStuReg")
	@ResponseBody
	public JSONObject queryStuReg(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		/* 查询条件 */
		String stuName = request.getParameter("stuName");
		String stuStudyNumber = request.getParameter("stuStudyNumber");
		String stuAccount = request.getParameter("stuAccount");
		StudentReg studentReg = new StudentReg();

		// 非空判断,不包括空格
		if (StringUtil.isNotBlank(stuName)) {
			studentReg.setStuName(stuName);
		}
		if (StringUtil.isNotBlank(stuAccount)) {
			studentReg.setStuAccount(stuAccount);
		}
		if (StringUtil.isNotBlank(stuStudyNumber)) {
			studentReg.setStuStudyNumber(stuStudyNumber);
		}

		List<StudentReg> stuRegList = studentRegService.queryStuReg(studentReg);
		json.put("stuRegList", stuRegList);
		return json;
	}

	@RequestMapping("/auditStudentReg")
	@ResponseBody
	public JSONObject auditStudentReg(HttpServletRequest request) {
		/* 条件判断 */
		// session里面取adminId
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String adminId = admin.getId();
		if (StringUtil.isBlank(adminId)) {
			// session失效
			return responseMsg("AUDIT0012","AUDIT");
		}
		String stuRegId = request.getParameter("stuRegId");
		if (StringUtil.isBlank(stuRegId)) {
			return responseMsg("AUDIT0013","AUDIT");
		}
		String auditResult = request.getParameter("auditResult");
		if (StringUtil.isBlank(auditResult)) {
			return responseMsg("AUDIT0013","AUDIT");
		}
		String dealExplain = request.getParameter("dealExplain");
		if (StringUtil.isBlank(dealExplain)) {
			return responseMsg("AUDIT0013","AUDIT");
		}
		String status = request.getParameter("status");
		if (StringUtil.isBlank(status)) {
			return responseMsg("AUDIT0013","AUDIT");
		}
		//判断处理类型
		if(!AuditStatu.PASS.getP().equals(status) && !AuditStatu.NOPASS.getP().equals(status)) {
			return responseMsg("AUDIT0014","AUDIT");
		}
		String retCode = studentRegService.auditStudentReg(adminId, stuRegId, auditResult, dealExplain, status);
		return responseMsg(retCode,"AUDIT");
	}
	
	private JSONObject responseMsg(String code,String type) {
		JSONObject json = new JSONObject();
		Dict dict = new Dict();
		dict.setCode(code);
		dict.setType(type);
		List<Dict> dictList = dictService.queryDict(dict);
		dict = dictList.get(0);
		json.put("retCode", dict.getCode());
		json.put("retMsg", dict.getValue());
		return json;
	}
}
