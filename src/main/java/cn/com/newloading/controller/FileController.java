package cn.com.newloading.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fileStream")
public class FileController {

	@RequestMapping("/fileUpload")
	@ResponseBody
	public String FileUpload(HttpServletRequest req) {
		//路径位置是在tomcat的里的工程名下，适用SSM
		//springboot,在src/main/resources文件夹下新建一个文件夹名为：/public或者/static，其路径下
		String root=req.getServletContext().getRealPath("/upload");
		
		File file=new File(root);
		if(file.exists()) {
			file.mkdir();
		}
		
		DiskFileItemFactory factory=new DiskFileItemFactory();//磁盘工厂
		factory.setSizeThreshold(2048*1024);//缓存 2M
		factory.setRepository(file);//缓存路径
		
		ServletFileUpload upload=new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = (List<FileItem>) upload.parseParameterMap(req);
			for(FileItem item:items) {
				if(item.isFormField()) {//正常的表单数据
					System.out.println(item.getFieldName()+"\t"+item.getString());
				}else{//文件表单 io流
					//文件拷贝,将临时缓存文件拷贝到自己上传的目标路径下
					InputStream input =item.getInputStream();//临时缓存文件
					String name=item.getName();//源文件名
					String extendName=name.substring(name.lastIndexOf("."));
					//UUID:全局唯一码,随机生成
					String fileName=root+"\\"+UUID.randomUUID()+extendName;
					//目标文件：文件夹+文件名(root+文件名（不重复）.扩展名)
					
					FileOutputStream fout=new FileOutputStream(fileName);
					byte[] buff=new byte[1024];
					int length=0;
					while((length=input.read(buff))>0) {
						fout.write(buff,0,length);
					}
					input.close();
					fout.close();
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "文件上传测试";
	}
	
}
