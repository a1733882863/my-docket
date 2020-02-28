package com.domain;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.ParseException;

import com.service.UserService;

public class UserForm {
	
	int id;
	String name;
	String password;
	String repassword;
	String gender;
	String ckcode1;
	String ckcode2;
	String telephone;
	String email;
	UserService us = null;
//	String birthday;
	Map<String,String> msg=new HashMap<String,String>();
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public boolean findNameByName(String name){
		us=new UserService();
		return us.findNameByName(name);
	}
	public boolean validate(){
		if("".equals(ckcode1)){
			msg.put("ckcode1", "��֤�벻��Ϊ��!");
		}
		else if(!ckcode1.equals(ckcode2)){
			msg.put("ckcide1", "��֤�����");
		}
		if("".equals(name)){
			msg.put("name", "�û�������Ϊ��!");
		}else{
			if(!name.matches("\\w{6,15}")){
				msg.put("name", "�û���6~15λ��ĸ!");	
			}else{
				if(findNameByName(name))
				msg.put("name", "�û����Ѵ���!");
			}			
		}
		
		if("".equals(password)){
			msg.put("password", "���벻��Ϊ��!");
		}
		else if(!password.matches("\\w{6,15}")){
			msg.put("password", "����Ϊ6~15λ����!");
		}
		if(!password.equals(repassword)){
			msg.put("repassword", "�������벻һ��!");
		}
		if("".equals(email)){
			msg.put("email", "���䲻��Ϊ��!");
		}
		else {
			if(!email.matches("\\b^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)$\\b")){
			msg.put("email", "�����ʽ����");
			}else{
				if(findEmailByEmail(email)){
					msg.put("email", "�����Ѿ�����");
				}
			}
		}
		if("".equals(telephone)){
			msg.put("telephone", "�ֻ����벻��Ϊ��!");
		}
		else if(!telephone.matches("\\w{11}")){
			msg.put("telephone", "�ֻ��������Ϊ11λ");
		}
		/*if("".equals(birthday)){
			msg.put("birthday", "���䲻��Ϊ��!");
		}
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(birthday);
			} catch (java.text.ParseException e) {
				// TODO �Զ����ɵ� catch ��
				msg.put("birthday", "���ո�ʽ����ȷ");
				e.printStackTrace();
			}
		}*/
		return msg.isEmpty();//map��û�м���ʱ������true
	}
	private boolean findEmailByEmail(String email) {
		us=new UserService();
		return us.findEmailByEmail(email);
	}
	public Map<String, String> getMsg() {
		return msg;
	}
	public void setMsg(Map<String, String> msg) {
		this.msg = msg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return name;
	}
	public void setUsername(String username) {
		this.name = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCkcode1() {
		return ckcode1;
	}
	public void setCkcode1(String ckcode1) {
		this.ckcode1 = ckcode1;
	}
	public String getCkcode2() {
		return ckcode2;
	}
	public void setCkcode2(String ckcode2) {
		this.ckcode2 = ckcode2;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
/*	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}*/
}
