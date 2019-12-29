package cn.com.newloading.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.Curriculum;
import cn.com.newloading.bean.Dict;
import cn.com.newloading.bean.Student;
import cn.com.newloading.bean.Teacher;
import cn.com.newloading.enums.SelectMenu;
import cn.com.newloading.service.CurriculumService;
import cn.com.newloading.service.DictService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController {

	@Autowired
	private DictService dictService;
	@Autowired
	private CurriculumService curriculumService;
	
	//教师新增课程
	@RequestMapping("/addCurriculum")
	@ResponseBody
	public JSONObject addCurriculum(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		String name = (String) params.get("name");
//		String name = request.getParameter("name");
		if(StringUtil.isBlank(name)) {
			return responseMsg("KC0002","KC");
		}
		String intro = (String) params.get("intro");
//		String intro = request.getParameter("intro");
		if(StringUtil.isBlank(intro)) {
			return responseMsg("KC0002","KC");
		}
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		if(teacher == null || StringUtil.isBlank(teacher.getId())) {
			return responseMsg("TEA00006","TEACHER");
		}
		Curriculum curriculum = new Curriculum();
		curriculum.setName(name);
		curriculum.setIntro(intro);
		curriculum.setTeacherId(teacher.getId());
//		curriculum.setTeacherId("1");
		String retCode = curriculumService.addCurriculum(curriculum);
		return responseMsg(retCode,"KC");
	}
	
	//教师展示课程
	@RequestMapping("/qcft")
	@ResponseBody
	public JSONObject queryCurriculumForTeacher(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		if(teacher == null || StringUtil.isBlank(teacher.getId())) {
			return responseMsg("TEA00006","TEACHER");
		}
		Curriculum curriculum = new Curriculum();
		curriculum.setTeacherId(teacher.getId());
//		curriculum.setTeacherId("1");
		List<Curriculum> cList = curriculumService.queryCurriculumForTeacher(curriculum);
		json.put("cList", cList);
		return json;
	}
	
	//学生展示课程
	@RequestMapping("/qcft")
	@ResponseBody
	public JSONObject queryCurriculumForStudent(HttpServletRequest request) {
		String select = request.getParameter("select");
		if(StringUtil.isBlank(select)) {
			return responseMsg("KC0002","KC");
		}
		if(!SelectMenu.SC.getP().equals(select) || !SelectMenu.MC.getP().equals(select)) {
			return responseMsg("KC0003","KC");
		}
		JSONObject json = new JSONObject();
		Student student = (Student) request.getSession().getAttribute("student");
		if(student == null) {
			return responseMsg("STU00006","STUDENT");
		}
		String stuId = student.getId();
		List<Curriculum> cList = new ArrayList<Curriculum>();
		if(SelectMenu.SC.getP().equals(select)) {
			cList = curriculumService.queryCurriculumForStudent(stuId);
		}else {
			cList = curriculumService.queryCurriculumForS(stuId);
		}
		json.put("cList", cList);
		return json;
	}
	
	//学生确认课程
	@RequestMapping("/qcft")
	@ResponseBody
	public JSONObject sureCurriculum(HttpServletRequest request) {
		String cuId = request.getParameter("cuId");//课程id
		if(StringUtil.isBlank(cuId)) {
			return responseMsg("KC0002","KC");
		}
		Student student = (Student) request.getSession().getAttribute("student");
		if(student == null) {
			return responseMsg("STU00006","STUDENT");
		}
		String stuId = student.getId();
		String retCode = curriculumService.sureCurriculum(cuId, stuId);
		return responseMsg(retCode,"KC");
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
