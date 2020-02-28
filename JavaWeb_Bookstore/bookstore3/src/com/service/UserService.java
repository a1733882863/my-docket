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
			throw new UserException("注册失败");
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
	//注册后激活
	public void active(String name, String code) throws UserException {
		try {
			ud.active(name,code);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new UserException("激活失败");
		}
		
	}
	public User login(User user) throws UserException {
		User u=null;
	try {
		u=ud.findUser(user);
		if(u==null){
			throw new UserException("用户名不存在或密码错误");
		}
		if(u.getState()!=1){
			throw new UserException("用户权限不足，请激活");
		}
	} catch (HibernateException e) {
		e.printStackTrace();
		//以后要往日志里面写入信息
	}
	return u;
	}
	public void modifiedUser(User user) throws UserException {
		try {
			ud.modifiedUser(user);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new UserException();
			//以后要往日志里面写入信息
		}
		
	}
	public void deleteUser(int id) {
		ud.deleteUser(id);
	}
	//根据用户和收藏的图书id找到收藏项
	public FavoriteItem findFavoriteItem(User user, int id) {
		return ud.findFavoriteItem(user,id);
		
	}
	public User createFavorite(int id) {
		return ud.createFavorite(id);
		
	}
	//保存收藏项
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
