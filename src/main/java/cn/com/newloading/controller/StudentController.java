package cn.com.newloading.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.StudentReg;
import cn.com.newloading.service.StudentRegService;
import cn.com.newloading.utils.StringUtil;
import cn.com.newloading.utils.TimeUtil;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRegService studentRegService;

	/**
	 * 学生注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/registerStu")
	@ResponseBody
	public JSONObject registerStu(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String stuName = request.getParameter("stuName");// 姓名
		if(StringUtil.isBlank(stuName)) {
			json.put("retCode", "REGSTU0004");
			json.put("retMsg", "姓名为空");
			return json;
		}
		String stuAccount = request.getParameter("stuAccount");// 账号
		if(StringUtil.isBlank(stuName)) {
			json.put("retCode", "REGSTU0005");
			json.put("retMsg", "账号为空");
			return json;
		}
		String stuPassword = request.getParameter("stuPassword");// 密码
		if(StringUtil.isBlank(stuName)) {
			json.put("retCode", "REGSTU0006");
			json.put("retMsg", "密码为空");
			return json;
		}
		String stuStudyNumber = request.getParameter("stuStudyNumber");// 学号
		if(StringUtil.isBlank(stuName)) {
			json.put("retCode", "REGSTU0007");
			json.put("retMsg", "学号为空");
			return json;
		}
		StudentReg studentReg = new StudentReg();
		studentReg.setStuName(stuName);
		studentReg.setStuAccount(stuAccount);
		studentReg.setStuPassword(stuPassword);
		studentReg.setStuStudyNumber(stuStudyNumber);
		String regTime = TimeUtil.dateToString(new Date());
		studentReg.setRegTime(regTime);
		String res = studentRegService.registerStu(studentReg);
		if ("REGSTU0002".equals(res)) {// 申请失败
			json.put("retCode", "REGSTU0002");// 提交失败提示码
			json.put("retMsg", "提交失败");
		} else if ("REGSTU0001".equals(res)) {// 账号重复
			json.put("retCode", "REGSTU0001");// 账号重复提示码
			json.put("retMsg", "登陆账号重复");
		} else if ("REGSTU0003".equals(res)) {
			json.put("retCode", "REGSTU0003");// 学号已注册过
			json.put("retMsg", "该学号已注册过");
		} else {// 注册成功
			json.put("retCode", "REGSTU0000");// 提交成功提示码
			json.put("retMsg", "提交成功");
		}
		return json;
	}

	/**
	 * 查询学生注册列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryStuReg")
	@ResponseBody
	public JSONObject queryStuReg(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		/*查询条件 */
		String stuName = request.getParameter("stuName");
		String stuStudyNumber = request.getParameter("stuStudyNumber");
		String stuAccount = request.getParameter("stuAccount");
		StudentReg studentReg = new StudentReg();
		
		//非空判断,不包括空格
		if(StringUtil.isNotBlank(stuName)) {
			studentReg.setStuName(stuName);
		}
		if(StringUtil.isNotBlank(stuAccount)) {
			studentReg.setStuAccount(stuAccount);
		}
		if(StringUtil.isNotBlank(stuStudyNumber)) {
			studentReg.setStuStudyNumber(stuStudyNumber);
		}
		
		List<StudentReg> stuRegList = studentRegService.queryStuReg(studentReg);
		json.put("stuRegList", stuRegList);
		return json;
	}
}
