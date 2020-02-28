package com.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import com.Utils.SendEmail;
import com.dao.UserDao;
import com.domain.Favorite;
import com.domain.FavoriteItem;
import com.domain.User;
import com.exception.UserException;
import com.exception.UserExistException;

public class UserService {

	UserDao ud = new UserDao();
	public void register(User user) throws UserException {
		try {
			user.setState(0);
			ud.addUser(user);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new UserException("ע��ʧ��");
		}
	}
	public boolean ckGetEmail(String getemail) {
		try {
			return ud.ckgetEmail(getemail);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	
	
	}
	public boolean ckGetName(String getname) {
		try {
			return ud.ckgetname(getname);
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	public boolean findNameByName(String name) {
		try {
			return ud.findNameByName(name);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean findEmailByEmail(String email) {
		return ud.findEmailByEmail(email);
	}
	//ע��󼤻�
	public void active(String name, String code) throws UserException {
		try {
			ud.active(name,code);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new UserException("����ʧ��");
		}
		
	}
	public User login(User user) throws UserException {
		User u=null;
	try {
		u=ud.findUser(user);
		if(u==null){
			throw new UserException("�û��������ڻ��������");
		}
		if(u.getState()!=1){
			throw new UserException("�û�Ȩ�޲��㣬�뼤��");
		}
	} catch (HibernateException e) {
		e.printStackTrace();
		//�Ժ�Ҫ����־����д����Ϣ
	}
	return u;
	}
	public void modifiedUser(User user) throws UserException {
		try {
			ud.modifiedUser(user);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new UserException();
			//�Ժ�Ҫ����־����д����Ϣ
		}
		
	}
	public void deleteUser(int id) {
		ud.deleteUser(id);
	}
	//�����û����ղص�ͼ��id�ҵ��ղ���
	public FavoriteItem findFavoriteItem(User user, int id) {
		return ud.findFavoriteItem(user,id);
		
	}
	public User createFavorite(int id) {
		return ud.createFavorite(id);
		
	}
	//�����ղ���
	public User createFavoriteItem(User user) {
		return ud.createFavoriteItem(user);
	}
	public User finUserById(int id) {
		return  ud.finUserById(id);
	}
	public List<FavoriteItem> getFavoriteItems(int id) {
		return ud.getFavoriteItems(id);
	}
	public void deleteFavoriteItem(int id) {
		ud.deleteFavoriteItem(id);
		
	}
	

}
