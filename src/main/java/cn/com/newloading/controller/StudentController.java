package cn.com.newloading.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Dict;
import cn.com.newloading.bean.Student;
import cn.com.newloading.service.DictService;
import cn.com.newloading.service.StudentService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private DictService dictService;
	
	@RequestMapping("/studentLogin")
	@ResponseBody
	public JSONObject studentLogin(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		String code = (String) params.get("verificationCode");//验证码
//		String code = request.getParameter("verificationCode");//验证码
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
		String stuStudyNumber = (String) params.get("stuStudyNumber");//学号登录
//		String stuStudyNumber = request.getParameter("stuStudyNumber");
		if(StringUtil.isBlank(stuStudyNumber)) {
			return responseMsg("STU00002","STUDENT");
		}
		String stuPassword = (String) params.get("stuPassword");//密码
//		String stuPassword = request.getParameter("stuPassword");
		if(StringUtil.isBlank(stuPassword)) {
			return responseMsg("STU00003","STUDENT");
		}
		Student student = studentService.studentLogin(stuStudyNumber, stuPassword);
		if(student == null || StringUtil.isBlank(student.getId())) {
			return responseMsg("STU00001","STUDENT");
		}else {
			request.getSession().setAttribute("student", student);
			return responseMsg("STU00000","STUDENT");
		}
	}
	
	@RequestMapping("/findStudent")
	@ResponseBody
	public JSONObject findStudent(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		String code = (String) params.get("verificationCode");
//		String code = request.getParameter("verificationCode");//验证码
		if(StringUtil.isEmpty(code)) {
			return responseMsg("CODE0002","CODE");
		}
		String verificationCode = (String) request.getSession().getAttribute("verificationCode");//session中的验证码
		if(StringUtil.isEmpty(verificationCode)) {
			return responseMsg("CODE0003","CODE");
		}
		if(!code.equals(verificationCode)) {
			return responseMsg("CODE0001","CODE");
		}
		String stuStudyNumber = (String) params.get("stuStudyNumber");
//		String stuStudyNumber = request.getParameter("stuStudyNumber");//学号
		if(StringUtil.isBlank(stuStudyNumber)) {
			return responseMsg("STU00002","STUDENT");
		}
		String stuPhone = (String) params.get("stuPhone");
		String stuEmail = (String) params.get("stuEmail");
//		String stuPhone = request.getParameter("stuPhone");
//		String stuEmail = request.getParameter("stuEmail");
		if (StringUtil.isBlank(stuEmail) && StringUtil.isBlank(stuPhone)) {
			return responseMsg("REGSTU0005","REGSTU");
		}
		Student student = studentService.findStudent(stuStudyNumber, stuPhone, stuEmail);
		if(student == null || StringUtil.isBlank(student.getId())) {
			return responseMsg("STU00005","STUDENT");
		}else {
			JSONObject json = responseMsg("STU00004","STUDENT");
			json.put("student", student);
			return json;
		}
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
