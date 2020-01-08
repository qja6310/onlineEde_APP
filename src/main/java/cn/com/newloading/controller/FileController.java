package cn.com.newloading.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import cn.com.newloading.bean.FileBean;
import cn.com.newloading.bean.Student;
import cn.com.newloading.bean.Teacher;
import cn.com.newloading.service.CurriculumLogService;
import cn.com.newloading.service.FileService;
import cn.com.newloading.utils.TimeUtil;

@Controller
@RequestMapping("/fileStream")
public class FileController {

	@Autowired
	private FileService fService;

	@Autowired
	private CurriculumLogService cService;

	@RequestMapping("/queryFiles")
	@ResponseBody
	public JSONObject queryFiles(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		JSONObject json = new JSONObject();
//		String id = request.getParameter("fileIdin");
//		String name = request.getParameter("fileNamein");
//		String extend = request.getParameter("fileExtendin");
//		String state = request.getParameter("fileStatein"); // 待审核 通过 驳回 学生
//		String type = request.getParameter("fileTypein");// 课件 作业 删除
		String id = (String) params.get("fileIdin");
		String name = (String) params.get("fileNamein");
		String extend = (String) params.get("fileExtendin");
		String state = (String) params.get("fileStatein"); // 待审核 通过 驳回 学生
		String type = (String) params.get("fileTypein");// 课件 作业 删除
		List<FileBean> list = fService.queryFiles(id, name, extend, state, type);
		json.put("list", list);
		return json;
	}

	@RequestMapping("/selectFileIdByclId")
	@ResponseBody
	public JSONObject selectFileIdByclId(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		JSONObject json = new JSONObject();
//		String clId = request.getParameter("courseLogId");
//		String state = request.getParameter("fileState");//待审核 、通过 、  驳回 、 学生
		String clId = (String) params.get("courseLogId");
		String state = (String) params.get("fileState");//待审核 、通过 、  驳回 、 学生
		List<FileBean> list = fService.selectFileIdByclId(clId,state);
		json.put("list", list);
		return json;
	}

	@RequestMapping("/checkFile")
	@ResponseBody
	public JSONObject checkFile(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		JSONObject json = new JSONObject();
//		String fId = request.getParameter("hiddenFid");
//		String state = request.getParameter("fileStatein");// 入参:  通过  驳回
		String fId = (String) params.get("hiddenFid");
		String state = (String) params.get("fileStatein");// 入参:  通过  驳回
		int result = fService.checkFile(fId, state);
		if (result < 1) {
			json.put("code", "0");
			return json;
		}
		json.put("code", "1");
		return json;
	}

	@RequestMapping("/delFile")
	@ResponseBody
	public JSONObject delFile(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		JSONObject json = new JSONObject();
//		String fId = request.getParameter("hiddenFid");
//		String type = request.getParameter("fileTypein");// 入参: " 删除"
		String fId = (String) params.get("hiddenFid");
		String type = (String) params.get("fileTypein");// 入参: " 删除"
		int result = fService.delFile(fId, type);
		if (result < 1) {
			json.put("code", "0");
			return json;
		}
		json.put("code", "1");
		return json;
	}

	@RequestMapping("/fileUpload")
	@ResponseBody
	public JSONObject fileUpload(HttpServletRequest request,@RequestBody Map<String, Object> params) {
		JSONObject json = new JSONObject();
		// 教师ID
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
//		String fileType = request.getParameter("fileType");// TODO 课件/作业
//		String cId = request.getParameter("currimlumId");
		String fileType = (String) params.get("fileType");// TODO 课件/作业
		String clId = (String) params.get("currimlumLogId");
		String root = "D:\\Games\\upload\\";
		File file1 = new File(root);
		if (!file1.exists()) {
			file1.mkdir();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();// 磁盘工厂
		factory.setSizeThreshold(2048 * 1024);// 缓存 2M
		factory.setRepository(file1);// 缓存路径

		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
			// 遍历普通参数（即formData的fileName和fileSize）
			Enumeration<String> names = req.getParameterNames();
			while (names.hasMoreElements()) {
				String key = names.nextElement();
				String val = req.getParameter(key);
				System.out.println("FormField：k=" + key + "v=" + val);
			}
			// 遍历文件参数（即formData的file）
			Iterator<String> iterator = req.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile file = req.getFile(iterator.next());
				String fileNames = file.getOriginalFilename();
				// 文件名
				fileNames = new String(fileNames.getBytes("UTF-8"));
				String frontName = fileNames.substring(0, fileNames.lastIndexOf("."));
				String extendName = fileNames.substring(fileNames.lastIndexOf("."));
				// UUID:全局唯一码,随机生成
				String uuidStr = String.valueOf(UUID.randomUUID());
				String fileName = root + uuidStr + extendName;
				byte[] content = file.getBytes();
				// 文件内容
				FileBean f = new FileBean();
				f.setFname(fileNames);
				f.setFuuidName(uuidStr + extendName);
				f.setFextend(extendName);
				f.setFpath(root);
				f.setFstate("通过");
				f.setFtype(fileType);
				String fTime = TimeUtil.dateToString(new Date());
				f.setFtime(fTime);
				f.setCommitId(teacher.getId());
				int result = fService.addFile(f);// 插入文件表
				if (result > 0) {
					int result2 = fService.fileLinkCurriculum(clId, uuidStr + extendName);
					if (result2 > 0) {// 插入文件-课程关联表
						FileUtils.writeByteArrayToFile(new File(fileName), content);
						json.put("code", "1");
						return json;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json.put("code", "0");
		return json;
	}

	@RequestMapping("/fileStuCommit")
	@ResponseBody
	public JSONObject fileStuCommit(HttpServletRequest request,@RequestBody Map<String, Object> params) {// ,@RequestBody Map<String, Object> params
		JSONObject json = new JSONObject();

		Student student = (Student) request.getSession().getAttribute("student");
//		String clId = request.getParameter("currimlumLogId");
		String clId = (String) params.get("currimlumLogId");
		String root = "D:\\Games\\studentWork\\";// 作业提交路径
		File file1 = new File(root);
		if (!file1.exists()) {
			file1.mkdir();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();// 磁盘工厂
		factory.setSizeThreshold(2048 * 1024);// 缓存 2M
		factory.setRepository(file1);// 缓存路径

		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
			// 遍历普通参数（即formData的fileName和fileSize）
			Enumeration<String> names = req.getParameterNames();
			while (names.hasMoreElements()) {
				String key = names.nextElement();
				String val = req.getParameter(key);
				System.out.println("FormField：k=" + key + "v=" + val);
			}
			// 遍历文件参数（即formData的file）
			Iterator<String> iterator = req.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile file = req.getFile(iterator.next());
				String fileNames = file.getOriginalFilename();
				// 文件名
				fileNames = new String(fileNames.getBytes("UTF-8"));
				String frontName = fileNames.substring(0, fileNames.lastIndexOf("."));
				String extendName = fileNames.substring(fileNames.lastIndexOf("."));
				// UUID:全局唯一码,随机生成
				String uuidStr = String.valueOf(UUID.randomUUID());
				String fileName = root + uuidStr + extendName;
				byte[] content = file.getBytes();
				// 文件内容
				FileBean f = new FileBean();
				f.setFname(fileNames);
				f.setFuuidName(uuidStr + extendName);
				f.setFextend(extendName);
				f.setFpath(root);
				f.setFstate("学生");
				String fTime = TimeUtil.dateToString(new Date());
				f.setFtime(fTime);
				int result = fService.addFile(f);// 插入文件表
				if (result > 0) {
					int result2 = fService.fileLinkCurriculum(clId, uuidStr + extendName);
					if (result2 > 0) {// 插入文件-课程关联表
						int resultCommit = cService.stuCommitHomework("提交", fTime, student.getId(), clId);
						if (resultCommit > 0) {
							FileUtils.writeByteArrayToFile(new File(fileName), content);
							json.put("code", "1");
							return json;
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json.put("code", "0");
		return json;
	}

	@RequestMapping("/fileDownload")
	@ResponseBody
	public JSONObject downLoadFile(HttpServletRequest request, HttpServletResponse response,@RequestBody Map<String, Object> params) {
		JSONObject json = new JSONObject();

//		String testTxt = request.getParameter("filename");
		String testTxt = (String) params.get("filename");
		String path = "D:\\Games\\upload\\" + testTxt;
		String fileName = path.substring(path.lastIndexOf("\\") + 1);
		try {
			// 修改应答的header信息,让浏览器下载这个链接
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			InputStream in = new FileInputStream(path);
			byte[] buff = new byte[1024];
			int len = 0;
			ServletOutputStream out = response.getOutputStream();
			while ((len = in.read(buff)) > 0) {
				out.write(buff, 0, len);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
