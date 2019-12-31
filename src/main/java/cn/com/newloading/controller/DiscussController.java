package cn.com.newloading.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Dict;
import cn.com.newloading.bean.Discuss;
import cn.com.newloading.bean.Student;
import cn.com.newloading.bean.Teacher;
import cn.com.newloading.bean.dto.DiscussDto;
import cn.com.newloading.enums.RoleType;
import cn.com.newloading.service.DictService;
import cn.com.newloading.service.DiscussService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/discuss")
public class DiscussController {

	@Autowired
	private DictService dictService;
	@Autowired
	private DiscussService discussService;
	
	
	@RequestMapping("/addDiscussForS")
	@ResponseBody
	public JSONObject addDiscussForStudent(HttpServletRequest request) {
		String cuId = request.getParameter("cuId");
		if(StringUtil.isBlank(cuId)) {
			return responseMsg("DIS0002","DIS");
		}
		String content = request.getParameter("content");
		if(StringUtil.isBlank(content)) {
			return responseMsg("DIS0002","DIS");
		}
		Student student = (Student) request.getSession().getAttribute("student");
		if(student == null) {
			return responseMsg("STU00006","STUDENT");
		}
		Discuss discuss = new Discuss();
		discuss.setCuId(cuId);
		discuss.setContent(content);
		discuss.setForeignId(student.getId());
		discuss.setForeingType(RoleType.STU.getRole());
		String ret = discussService.addDiscuss(discuss);
		return responseMsg(ret,"DIS");
	}
	
	@RequestMapping("/addDiscussForT")
	@ResponseBody
	public JSONObject addDiscussForT(HttpServletRequest request) {
		String cuId = request.getParameter("cuId");
		if(StringUtil.isBlank(cuId)) {
			return responseMsg("DIS0002","DIS");
		}
		String content = request.getParameter("content");
		if(StringUtil.isBlank(cuId)) {
			return responseMsg("DIS0002","DIS");
		}
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		if(teacher == null) {
			return responseMsg("TEA00006","TEACHER");
		}
		Discuss discuss = new Discuss();
		discuss.setCuId(cuId);
		discuss.setContent(content);
		discuss.setForeignId(teacher.getId());
		discuss.setForeingType(RoleType.TEA.getRole());
		String ret = discussService.addDiscuss(discuss);
		return responseMsg(ret,"DIS");
	}
	
	@RequestMapping("/queryDiscuss")
	@ResponseBody
	public JSONObject queryDiscuss(HttpServletRequest request) {
		String cuId = request.getParameter("cuId");
		if(StringUtil.isBlank(cuId)) {
			return responseMsg("DIS0002","DIS");
		}
		List<DiscussDto> dtoList = discussService.queryDiscussByCuId(cuId);
		JSONObject json = new JSONObject();
		json.put("dtoList", dtoList);
		return json;
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
