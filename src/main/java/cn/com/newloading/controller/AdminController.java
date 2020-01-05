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
import cn.com.newloading.bean.StudentReg;
import cn.com.newloading.enums.AuditStatu;
import cn.com.newloading.service.AdminService;
import cn.com.newloading.service.DictService;
import cn.com.newloading.service.StudentRegService;
import cn.com.newloading.utils.StringUtil;
import cn.com.newloading.utils.TimeUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private StudentRegService studentRegService;
	@Autowired
	private DictService dictService;
	@Autowired
	private AdminService adminService;

	/**
	 * 管理员登陆
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminLogin")
	@ResponseBody
	public JSONObject adminLogin(HttpServletRequest request) {//,@RequestBody Map<String, Object> params
//		String code = (String) params.get("verificationCode");//验证码
		String code = request.getParameter("verificationCode");
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
//		String admAccount = (String) params.get("admAccount");
		String admAccount = request.getParameter("admAccount");
		if (StringUtil.isBlank(admAccount)) {
			return responseMsg("ADMIN0001","ADMIN");
		}
		String admPassword = request.getParameter("admPassword");
//		String admPassword = (String) params.get("admPassword");
		if (StringUtil.isBlank(admPassword)) {
			return responseMsg("ADMIN0002","ADMIN");
		}
		Admin admin = adminService.adminLogin(admAccount, admPassword);
		if(admin == null || StringUtil.isBlank(admin.getId())) {
			return responseMsg("ADMIN0003","ADMIN");
		}else {
			request.getSession().setAttribute("admin", admin);
			return responseMsg("ADMIN0000","ADMIN");
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
