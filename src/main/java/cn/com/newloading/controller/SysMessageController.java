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
import cn.com.newloading.bean.Dict;
import cn.com.newloading.bean.SysMessage;
import cn.com.newloading.service.DictService;
import cn.com.newloading.service.SysMsgService;
import cn.com.newloading.utils.StringUtil;

@Controller
@RequestMapping("/sysMsg")
public class SysMessageController {

	@Autowired
	private DictService dictService;
	@Autowired
	private SysMsgService sysMsgService;
	
	@RequestMapping("/addSysMsg")
	@ResponseBody
	public JSONObject addSysMsg(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		String title = (String) params.get("title");
		if(StringUtil.isBlank(title)) {
			return responseMsg("SYSMSG0002","SYSMSG");
		}
		String content = (String) params.get("content");
		if(StringUtil.isBlank(content)) {
			return responseMsg("SYSMSG0003","SYSMSG");
		}
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(StringUtil.isBlank(content)) {
			return responseMsg("SYSMSG0004","SYSMSG");
		}
		String adminId = admin.getId();
		String retCode = sysMsgService.addSysMsg(title, content, adminId);
		return responseMsg(retCode,"SYSMSG");
	}
	
	@RequestMapping("/querySysMsg")
	@ResponseBody
	public JSONObject querySysMsg(@RequestBody Map<String, Object> params) {
		JSONObject json = new JSONObject();
		String title = (String) params.get("title");
		String content = (String) params.get("content");
		List<SysMessage> sysMsgList = sysMsgService.querySysMsg(title, content);
		json.put("sysMsgList", sysMsgList);
		return json;
	}
	
	@RequestMapping("/delSysMsg")
	@ResponseBody
	public JSONObject delSysMsg(@RequestBody Map<String, Object> params) {
		String sysMsgId = (String) params.get("sysMsgId");
		if(StringUtil.isBlank(sysMsgId)) {
			return responseMsg("SYSMSG0007","SYSMSG");
		}
		String retCode = sysMsgService.delSysMsg(sysMsgId);
		return responseMsg(retCode,"SYSMSG");
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
