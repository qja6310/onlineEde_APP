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
import cn.com.newloading.bean.Teacher;
import cn.com.newloading.service.DictService;
import cn.com.newloading.service.TeacherService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService tService;
	@Autowired
	private DictService dictService;
	
	@RequestMapping("/teacherLogin")
	@ResponseBody
	public JSONObject teacherLogin(HttpServletRequest request) {//,@RequestBody Map<String, Object> params
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
//		String tNumber = (String) params.get("tNumber");
		String tNumber = request.getParameter("tNumber");
		if(StringUtil.isBlank(tNumber)) {
			return responseMsg("TEA00002","TEACHER");
		}
//		String tPassword = (String) params.get("tPassword");
		String tPassword = request.getParameter("tPassword");
		if(StringUtil.isBlank(tPassword)) {
			return responseMsg("TEA00003","TEACHER");
		}
		Teacher t = tService.TeacherLogin(tNumber, tPassword);
		
		if(t == null || StringUtil.isBlank(t.getId())) {
			return responseMsg("TEA00001","TEACHER");
		}else {
			request.getSession().setAttribute("teacher", t);
			return responseMsg("TEA00000","TEACHER");
		}
	}
	
	@RequestMapping("/findTeacher")
	@ResponseBody
	public JSONObject findTeacher(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		String code = (String) params.get("verificationCode");//验证码
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
		String tNumber = (String) params.get("tNumber");//职工号
		String tPhone = (String) params.get("tPhone");
		String tEmail = (String) params.get("tEmail");
//		String tNumber = request.getParameter("tNumber");//职工号
//		String tPhone = request.getParameter("tPhone");
//		String tEmail = request.getParameter("tEmail");
		if(StringUtil.isBlank(tNumber)) {
			return responseMsg("TEA00002","TEACHER");
		}
		if (StringUtil.isBlank(tEmail) && StringUtil.isBlank(tPhone)) {
			return responseMsg("REGTEA0005","REGTEA");
		}
		Teacher t = tService.findTeacher(tNumber, tPhone, tEmail);
		if(t == null || StringUtil.isBlank(t.getId())) {
			return responseMsg("TEA00005","TEACHER");
		}else {
			JSONObject json = responseMsg("TEA00004","TEACHER");
			json.put("TEACHER", t);
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
