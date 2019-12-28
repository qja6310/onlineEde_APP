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

import cn.com.newloading.bean.Admin;
import cn.com.newloading.bean.ContectAdmin;
import cn.com.newloading.bean.Dict;
import cn.com.newloading.bean.Student;
import cn.com.newloading.bean.dto.ContectAdminDto;
//import cn.com.newloading.bean.Teacher;
import cn.com.newloading.enums.RoleType;
import cn.com.newloading.service.ContectAdminService;
import cn.com.newloading.service.DictService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/contectAdmin")
public class ContectAdminController {

	@Autowired
	private DictService dictService;
	@Autowired
	private ContectAdminService caService;

	/**
	 * 学生提出系统问题
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/stuSysQuestion")
	@ResponseBody
	public JSONObject stuSysQuestion(HttpServletRequest request, @RequestBody Map<String, Object> params) {
		ContectAdmin contectAdmin = new ContectAdmin();
		contectAdmin.setForeignType(RoleType.STU.getRole());
		Student student = (Student) request.getSession().getAttribute("student");
		if (null == student || "".equals(student.getId())) {
			return responseMsg("STU00006", "STUDENT");
		}
		contectAdmin.setForeignId(student.getId());
		// 消息内容
		String content = (String) params.get("content");
//		String content = request.getParameter("content");
		if (StringUtil.isBlank(content)) {
			return responseMsg("CONADM0004", "CONADM");
		}
		contectAdmin.setContent(content);

		String retcode = caService.addContectAdmin(contectAdmin);
		return responseMsg(retcode, "CONADM");

	}

	/**
	 * 学生回复系统问题
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/stuReplySysMsg")
	@ResponseBody
	public JSONObject stuReplySysMsg(HttpServletRequest request, @RequestBody Map<String, Object> params) {
		ContectAdmin contectAdmin = new ContectAdmin();
		contectAdmin.setForeignType(RoleType.STU.getRole());
		Student student = (Student) request.getSession().getAttribute("student");
		if (null == student || "".equals(student.getId())) {
			return responseMsg("STU00006", "STUDENT");
		}
		contectAdmin.setForeignId(student.getId());
		// 父级id，属于哪个问题下的，如果是问题说明为空
		String pid = (String) params.get("pid");
//		String pid = request.getParameter("pid");
		if (StringUtil.isBlank(pid)) {
			return responseMsg("CONADM0002", "CONADM");
		}
		contectAdmin.setPid(pid);
		// 消息内容
		String content = (String) params.get("content");
//		String content = request.getParameter("content");
		if (StringUtil.isBlank(content)) {
			return responseMsg("CONADM0004", "CONADM");
		}
		contectAdmin.setContent(content);

		String retcode = caService.addContectAdmin(contectAdmin);
		return responseMsg(retcode, "CONADM");
	}

	/**
	 * 老师提出系统问题
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/teaSysQuestion")
	@ResponseBody
	public JSONObject teaSysQuestion(HttpServletRequest request, @RequestBody Map<String, Object> params) {
		ContectAdmin contectAdmin = new ContectAdmin();
		contectAdmin.setForeignType(RoleType.TEA.getRole());
//		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
//		if(null == teacher || "".equals(teacher.getId())) {
//			return responseMsg("TEA00006", "TEACHER");
//		}
//		contectAdmin.setForeignId(teacher.getId());
		// 消息内容
		String content = (String) params.get("content");
		if (StringUtil.isBlank(content)) {
			return responseMsg("CONADM0004", "CONADM");
		}
		contectAdmin.setContent(content);

		String retcode = caService.addContectAdmin(contectAdmin);
		return responseMsg(retcode, "CONADM");

	}

	/**
	 * 老师回复系统问题
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/teaReplySysMsg")
	@ResponseBody
	public JSONObject teaReplySysMsg(HttpServletRequest request, @RequestBody Map<String, Object> params) {
		ContectAdmin contectAdmin = new ContectAdmin();
		contectAdmin.setForeignType(RoleType.TEA.getRole());
//		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
//		if(null == teacher || "".equals(teacher.getId())) {
//			return responseMsg("TEA00006", "TEACHER");
//		}
//		contectAdmin.setForeignId(teacher.getId());
		// 父级id，属于哪个问题下的，如果是问题说明为空
		String pid = (String) params.get("pid");
		if (StringUtil.isBlank(pid)) {
			return responseMsg("CONADM0002", "CONADM");
		}
		contectAdmin.setPid(pid);
		// 消息内容
		String content = (String) params.get("content");
		if (StringUtil.isBlank(content)) {
			return responseMsg("CONADM0004", "CONADM");
		}
		contectAdmin.setContent(content);

		String retcode = caService.addContectAdmin(contectAdmin);
		return responseMsg(retcode, "CONADM");
	}

	/**
	 * 管理员回复系统问题
	 * 
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("/admReplySysMsg")
	@ResponseBody
	public JSONObject admReplySysMsg(HttpServletRequest request, @RequestBody Map<String, Object> params) {
		ContectAdmin contectAdmin = new ContectAdmin();
		contectAdmin.setForeignType(RoleType.ADM.getRole());
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (null == admin || "".equals(admin.getId())) {
			return responseMsg("SYSMSG0004", "SYSMSG");
		}
		contectAdmin.setForeignId(admin.getId());
		// 父级id，属于哪个问题下的，如果是问题说明为空
		String pid = (String) params.get("pid");
//		String pid = request.getParameter("pid");
		if (StringUtil.isBlank(pid)) {
			return responseMsg("CONADM0002", "CONADM");
		}
		contectAdmin.setPid(pid);
		// 消息内容
		String content = (String) params.get("content");
//		String content = request.getParameter("content");
		if (StringUtil.isBlank(content)) {
			return responseMsg("CONADM0004", "CONADM");
		}
		contectAdmin.setContent(content);

		String retcode = caService.addContectAdmin(contectAdmin);
		return responseMsg(retcode, "CONADM");
	}

	/**
	 * 管理员查看系统问题
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qsqfa")
	@ResponseBody
	public JSONObject querySysQuestionForAdmin(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		JSONObject json = new JSONObject();
		String foreignType = (String) params.get("foreignType");
//		String foreignType = request.getParameter("foreignType");
		if (StringUtil.isBlank(foreignType)) {
			return responseMsg("CONADM0002", "CONADM");
		}
		if (!RoleType.STU.getRole().equals(foreignType) && !RoleType.TEA.getRole().equals(foreignType)) {
			return responseMsg("CONADM0003", "CONADM");
		}
		ContectAdmin contectAdmin = new ContectAdmin();
		contectAdmin.setForeignType(foreignType);
		// 内容提供模糊查询
		String content = (String) params.get("content");
//		String content = request.getParameter("content");
		if (StringUtil.isNotBlank(content)) {
			contectAdmin.setContent(content);
		}

		// 是否有没回复
		String replyFlag = (String) params.get("replyFlag");//可以不选择
//		String replyFlag = request.getParameter("replyFlag");// 可以不选择
		if (StringUtil.isBlank(replyFlag)) {
			contectAdmin.setReplyFlag(replyFlag);
		}
		contectAdmin.setType("1");
		List<ContectAdminDto> caList = caService.queryContectAdmin(contectAdmin);
		json.put("contectAdminList", caList);
		return json;
	}

	/**
	 * 系统问题的具体回复
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qsqr")
	@ResponseBody
	public JSONObject querySysQuestionReply(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		JSONObject json = new JSONObject();
		String pid = (String)params.get("pid");
//		String pid = request.getParameter("pid");
		if (StringUtil.isBlank(pid)) {
			return responseMsg("CONADM0002", "CONADM");
		}
		ContectAdmin contectAdmin = new ContectAdmin();
		contectAdmin.setPid(pid);
		List<ContectAdminDto> caList = caService.queryContectAdmin(contectAdmin);
		json.put("contectAdminList", caList);
		return json;
	}

	/**
	 * 学生查看自己提问的问题
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qsqfs")
	@ResponseBody
	public JSONObject querySysQuestionForStudent(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		ContectAdmin contectAdmin = new ContectAdmin();
		contectAdmin.setForeignType(RoleType.STU.getRole());
		contectAdmin.setType("1");
		Student student = (Student) request.getSession().getAttribute("student");
		if (null == student || "".equals(student.getId())) {
			return responseMsg("STU00006", "STUDENT");
		}
		contectAdmin.setForeignId(student.getId());
		List<ContectAdminDto> caList = caService.queryContectAdmin(contectAdmin);
		json.put("contectAdminList", caList);
		return json;
	}

	/**
	 * 老师查看自己提问的问题
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qsqft")
	@ResponseBody
	public JSONObject querySysQuestionForTeacher(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		ContectAdmin contectAdmin = new ContectAdmin();
		contectAdmin.setForeignType(RoleType.TEA.getRole());
		contectAdmin.setType("1");
//		Student student = (Student) request.getSession().getAttribute("student");
//		if (null == student || "".equals(student.getId())) {
//			return responseMsg("STU00006", "STUDENT");
//		}
//		contectAdmin.setForeignId(student.getId());
		List<ContectAdminDto> caList = caService.queryContectAdmin(contectAdmin);
		json.put("contectAdminList", caList);
		return json;
	}
	
	/* 错误码返回 */
	private JSONObject responseMsg(String code, String type) {
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
