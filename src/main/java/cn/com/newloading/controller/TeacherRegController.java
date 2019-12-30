package cn.com.newloading.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.Dict;
import cn.com.newloading.bean.TeacherReg;
import cn.com.newloading.enums.AuditStatu;
import cn.com.newloading.service.DictService;
import cn.com.newloading.service.TeacherRegService;
import cn.com.newloading.utils.StringUtil;
import cn.com.newloading.utils.TimeUtil;

@Controller
@RequestMapping("/teacherReg")
public class TeacherRegController {

	@Autowired
	private TeacherRegService tRegService;
	@Autowired
	private DictService dictService;

	/**
	 * 教师注册
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/registerTea")
	@ResponseBody
	public JSONObject registerTea(HttpServletRequest request) {//,@RequestBody Map<String, Object> params
//		String code = (String) params.get("verificationCode");//验证码
		String code = request.getParameter("verificationCode");//验证码
		if(StringUtil.isEmpty(code)) {
			return responseMsg("CODE0002","CODE");
		}
		String verificationCode = (String) request.getSession().getAttribute("verificationCode");//session中的验证码
		if(StringUtil.isEmpty(verificationCode)) {
			return responseMsg("CODE0003","CODE");
		}
		if(!code.equalsIgnoreCase(verificationCode)) {
			return responseMsg("CODE0001","CODE");
		}
//		String tPhone = (String) params.get("tPhone");// 手机号
//		String tEmail = (String) params.get("tEmail");// 邮箱
//		String tPassword = (String) params.get("tPassword");// 密码
//		String tNumber = (String) params.get("tNumber");// 职工号
//		String tName = (String) params.get("tName");//姓名
		String tPhone = request.getParameter("tPhone");
		String tEmail = request.getParameter("tEmail");
		String tPassword = request.getParameter("tPassword");
		String tNumber = request.getParameter("tNumber");
		String tName = request.getParameter("tName");
		if (StringUtil.isBlank(tEmail) && StringUtil.isBlank(tPhone)) {
			return responseMsg("REGTEA0005","REGTEA");
		}
		if (StringUtil.isBlank(tPassword)) {
			return responseMsg("REGTEA0006","REGTEA");
		}
		if (StringUtil.isBlank(tNumber)) {
			return responseMsg("REGTEA0007","REGTEA");
		}
		TeacherReg tReg = new TeacherReg();
		tReg.setName(tName);
		tReg.setTeaPhone(tPhone);
		tReg.setTeaEmail(tEmail);
		tReg.setTeaPassword(tPassword);
		tReg.setTeaNumber(tNumber);
		String regTime = TimeUtil.dateToString(new Date());
		tReg.setRegTime(regTime);
		String res = tRegService.registerTea(tReg);
		return responseMsg(res,"REGTEA");
	}

	/**
	 * 查询教师注册列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/queryTeaReg")
	@ResponseBody
	public JSONObject queryTeaReg(HttpServletRequest request) {//@RequestBody Map<String, Object> params
		JSONObject json = new JSONObject();
		/* 查询条件 */
//		String tPhone = (String) params.get("tPhone");// 手机号
//		String tEmail = (String) params.get("tEmail");// 邮箱
//		String tNumber = (String) params.get("tNumber");// 学号
//		String status = (String) params.get("status");//状态
		String tPhone = request.getParameter("tPhone");
		String tEmail = request.getParameter("tEmail");
		String tNumber = request.getParameter("tNumber");
		String status = request.getParameter("status");
		TeacherReg tReg = new TeacherReg();
		// 非空判断,不包括空格
		if (StringUtil.isNotBlank(tPhone)) {
			tReg.setTeaPhone(tPhone);
		}
		if (StringUtil.isNotBlank(tEmail)) {
			tReg.setTeaEmail(tEmail);
		}
		if (StringUtil.isNotBlank(tNumber)) {
			tReg.setTeaNumber(tNumber);
		}
		if(StringUtil.isNotBlank(status)) {
			if(!AuditStatu.PASS.getP().equals(status) && !AuditStatu.NOPASS.getP().equals(status) &&!AuditStatu.PENDING.getP().equals(status)) {
				return responseMsg("AUDIT0015","AUDIT");
			}
			tReg.setStatus(status);
		}

		List<TeacherReg> list = tRegService.queryTeaReg(tReg);
		json.put("teaRegList", list);
		return json;
	}

	@RequestMapping("/auditTeacherReg")
	@ResponseBody
	public JSONObject auditTeacherReg(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		/* 条件判断 */
		// session里面取adminId
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String adminId = admin.getId();
		if (StringUtil.isBlank(adminId)) {
			// session失效
			return responseMsg("AUDIT0012","AUDIT");
		}
		String tRegId = (String) params.get("tRegId");
		if (StringUtil.isBlank(tRegId)) {
			return responseMsg("AUDIT0013","AUDIT");
		}
		String auditResult = (String) params.get("auditResult");
//		if (StringUtil.isBlank(auditResult)) {
//			return responseMsg("AUDIT0013","AUDIT");
//		}
		String dealExplain = (String) params.get("dealExplain");
//		if (StringUtil.isBlank(dealExplain)) {
//			return responseMsg("AUDIT0013","AUDIT");
//		}
		String status = (String) params.get("status");
		if (StringUtil.isBlank(status)) {
			return responseMsg("AUDIT0013","AUDIT");
		}
		//判断处理类型
		if(!AuditStatu.PASS.getP().equals(status) && !AuditStatu.NOPASS.getP().equals(status)) {
			return responseMsg("AUDIT0014","AUDIT");
		}
		String retCode = tRegService.auditTeacherReg(adminId, tRegId, auditResult, dealExplain, status);
		return responseMsg(retCode,"AUDIT");
	}
	
	/*错误码返回*/
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
