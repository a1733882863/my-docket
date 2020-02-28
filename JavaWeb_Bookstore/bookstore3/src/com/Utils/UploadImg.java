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
		///����һ��DiskFileItemFactory����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//����һ��ServletFileUpload����
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8");//����ϴ��ļ�������
		//����request���󣬷������б���
		List<FileItem> fileItems = new ArrayList<FileItem>(0);
		//���ڷ�װ��ͨ���������
		Map<String, String[]> map = new HashMap<String, String[]>();
		//factory.setRepository(new File("e:\\"));// ָ����ʱ�ļ��Ĵ洢Ŀ¼(�ļ�һ�������趨���ֵ�Ǵ�ŵ�λ��)
		try {
			sfu.setFileSizeMax(1024*1024*3);//��ʾ3M��С
			fileItems = sfu.parseRequest(request);
			
			//����fileItems����
			for (FileItem fileItem : fileItems) {
				if(fileItem.isFormField()){
					//��ͨ����
					String name = fileItem.getFieldName();//�õ��ֶε���
					String value = fileItem.getString("UTF-8");//�õ��ֶ�ֵ
					map.put(name, new String[]{value});//��map�и�ֵ
					
				}else{
					//�ļ�����
					InputStream inputStream = fileItem.getInputStream();
					String filename = fileItem.getName();//�õ��ϴ����ļ���
					String extension = FilenameUtils.getExtension(filename);
					if(!("jsp".equals(extension)||"exe".equals(extension))){//�ϴ����ļ�������jsp��exe
						//����Ŀ¼ 
						File storeDirectory = new File(ser.getServletContext().getRealPath("/bookimg"));
						if(!storeDirectory.exists()){
							storeDirectory.mkdirs();//���Ŀ¼�����ڣ��ʹ���
						}
					//�����ļ���
					if(filename!=null){
						filename = FilenameUtils.getName(filename);
					}
					// Ŀ¼��ɢ
					//String childDirectory = makeChildDirectory(storeDirectory); 
					
					//filename = childDirectory+File.separator+filename;
					//�ļ��ϴ� 
					fileItem.write(new File(storeDirectory,filename));
					fileItem.delete();	//ɾ����ʱ�ļ�
					
					}
					map.put(fileItem.getFieldName(),new String[]{filename});//��ͼƬ�����name��value���浽map��
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	return map;
	}
			// �����ڴ�ɢ
			
	 private String makeChildDirectory(File storeDirectory) {
			  
			  SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd"); String
			  dateDirectory = sdf.format(new Date()); //ֻ�ܴ���Ŀ¼ 
			  File file = new File(storeDirectory,dateDirectory); 
			 if(!file.exists()){ file.mkdirs(); }
			  
			  return dateDirectory; 
			  }
			 
	}
