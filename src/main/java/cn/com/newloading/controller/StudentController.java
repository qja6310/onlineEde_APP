package cn.com.newloading.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.newloading.bean.StudentReg;
import cn.com.newloading.service.StudentService;
import cn.com.newloading.utils.TimeUtil;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/registerStu")
	@ResponseBody
	public String registerStu(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			String stuName = request.getParameter("stuName");// 姓名
			String stuAccount = request.getParameter("stuAccount");// 账号
			String stuPassword = request.getParameter("stuPassword");// 密码
			String stuStudyNumber = request.getParameter("stuStudyNumber");// 学号
			StudentReg studentReg = new StudentReg();
			studentReg.setStuName(stuName);
			studentReg.setStuAccount(stuAccount);
			studentReg.setStuPassword(stuPassword);
			studentReg.setStuStudyNumber(stuStudyNumber);
			String regTime = TimeUtil.dateToString(new Date());
			studentReg.setRegTime(regTime);
			studentReg = (StudentReg) studentService.registerStu(studentReg);
			if (studentReg == null) {// 申请失败
				json.put("retCode", "REGSTU0001");// 提交失败提示码
				json.put("retMsg", "提交失败");
			} else if ("REGSTU0002".equals(studentReg.getId())) {// 账号重复
				json.put("retCode", "REGSTU0002");// 账号重复提示码
				json.put("retMsg", "登陆账号重复");
			} else {// 注册成功
				json.put("retCode", "REGSTU0000");// 提交成功提示码
				json.put("retMsg", "提交成功");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

}
