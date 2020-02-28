package com.Utils;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.domain.Product;
import com.service.BookService;

public class UploadImg {
	public Map<String,String[]> getMap(HttpServletRequest request,HttpServlet ser){
		///创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建一个ServletFileUpload对象
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8");//解决上传文件的乱码
		//解析request对象，返回所有表单项
		List<FileItem> fileItems = new ArrayList<FileItem>(0);
		//用于封装普通表单项的数据
		Map<String, String[]> map = new HashMap<String, String[]>();
		//factory.setRepository(new File("e:\\"));// 指定临时文件的存储目录(文件一旦超过设定最大值是存放的位置)
		try {
			sfu.setFileSizeMax(1024*1024*3);//表示3M大小
			fileItems = sfu.parseRequest(request);
			
			//迭代fileItems表单项
			for (FileItem fileItem : fileItems) {
				if(fileItem.isFormField()){
					//普通表单项
					String name = fileItem.getFieldName();//得到字段的名
					String value = fileItem.getString("UTF-8");//得到字段值
					map.put(name, new String[]{value});//向map中赋值
					
				}else{
					//文件表单项
					InputStream inputStream = fileItem.getInputStream();
					String filename = fileItem.getName();//得到上传的文件名
					String extension = FilenameUtils.getExtension(filename);
					if(!("jsp".equals(extension)||"exe".equals(extension))){//上传的文件不能是jsp、exe
						//创建目录 
						File storeDirectory = new File(ser.getServletContext().getRealPath("/bookimg"));
						if(!storeDirectory.exists()){
							storeDirectory.mkdirs();//如何目录不存在，就创建
						}
					//处理文件名
					if(filename!=null){
						filename = FilenameUtils.getName(filename);
					}
					// 目录打散
					//String childDirectory = makeChildDirectory(storeDirectory); 
					
					//filename = childDirectory+File.separator+filename;
					//文件上传 
					fileItem.write(new File(storeDirectory,filename));
					fileItem.delete();	//删除临时文件
					
					}
					map.put(fileItem.getFieldName(),new String[]{filename});//将图片表单项的name和value保存到map中
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return map;
	}
			// 按日期打散
			
	 private String makeChildDirectory(File storeDirectory) {
			  
			  SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd"); String
			  dateDirectory = sdf.format(new Date()); //只管创建目录 
			  File file = new File(storeDirectory,dateDirectory); 
			 if(!file.exists()){ file.mkdirs(); }
			  
			  return dateDirectory; 
			  }
			 
	}
