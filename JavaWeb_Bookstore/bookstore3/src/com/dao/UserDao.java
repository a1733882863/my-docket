package com.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.classic.Session;

import com.Utils.HibernateUtils;
import com.domain.Favorite;
import com.domain.FavoriteItem;
import com.domain.Guest;
import com.domain.Product;
import com.domain.User;
import com.exception.UserException;

public class UserDao {
	
	public void addUser(User user) throws HibernateException {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	public boolean ckgetEmail(String getemail) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		List<User> list = session.createQuery("from Guest where email = :useremail")
				.setParameter("useremail", getemail).list();
		System.out.println(list);
		User u = null;
		for (int i = 0; i < list.size(); i++) {
			u =  list.get(i);
			if (u != null)
				break;
		}
		if (u != null) {
			session.getTransaction().commit();
			return true;
		} else {
			session.getTransaction().commit();
			return false;
		}

	}

	public boolean ckgetname(String getname) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		List<User> list = (List<User>) session.createQuery("from User where name = :username")
				.setParameter("username", getname).list();
		System.out.println(list);
		User u = null;
		for (int i = 0; i < list.size(); i++) {
			u = list.get(i);
			if (u != null)
				break;
		}
		if (u != null) {
			session.getTransaction().commit();
			return true;
		} else {
			session.getTransaction().commit();
			return false;
		}
	}

	// ֻ���ز�ѯ�Ĳ���//�ҵ����� �з����棬�޷��ؼ� //��ѯ�ֶ�
	public boolean findNameByName(String name) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		List<Object> list = session.createQuery("select name from User where name=?").setParameter(0, name).list();
		for (Object o : list) {
			if (o.toString() != null) {
				return true;
			}
		}
		return false;
		/*
		 * User user = new User(); user.setName(null); List<User>
		 * list=(List<User>)session.createQuery(
		 * "select name from User where name=?").setParameter(0, name).list();
		 * user.setName(list.get(0).getName());
		 * if(user.getName().trim()==""||user.getName()==null){
		 * session.getTransaction().commit(); return false; }else{
		 * session.getTransaction().commit(); return true; }
		 */

	}

	public boolean findEmailByEmail(String email) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		List<Object> list = session.createQuery("select email from User where email=?")
				.setParameter(0, email)
				.list();
		for (Object o : list) {
			if (o.toString() != null) {
				return true;
			}
		}
		return false;
	}

	public void active(String name, String code) throws UserException {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		User user = (User) session.createQuery("from User where name=? and activeCode=?")
				.setParameter(0, name)
				.setParameter(1, code).uniqueResult();

		if (user != null) {
			user.setState(1);// ���ǳ־�̬�£������ύ
		} else {
			throw new UserException("����ʧ��");
		}
		session.getTransaction().commit();
	}

	public User findUser(User user) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		User user1 = (User) session.createQuery("from User where name=? and password=?")
				.setParameter(0, user.getName())
				.setParameter(1, user.getPassword())
				.setCacheable(true)
				.uniqueResult();
		session.getTransaction().commit();
		return user1;
	}

	public void modifiedUser(User user) { //����̬��־�̬  �Ժ��޸ĳ�name��email���ܱ��޸ĵĲ���
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		 session.createQuery("update User set password=? where id=?")
				.setParameter(0, user.getPassword())
				.setParameter(1, user.getId())
				.executeUpdate();
				
		session.getTransaction().commit();
		
	}

	public void deleteUser(int id) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class,id);
		session.delete(user);
		session.getTransaction().commit();
		
	}
	//�����û����ղص�ͼ��id�ҵ��ղ���
	public FavoriteItem findFavoriteItem(User user, int pid) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		Favorite favorite=(Favorite) session.get(Favorite.class, user.getId());
		//favoriteitem�������Ʒ��id
		for(FavoriteItem fi :favorite.getFavoriteItems()){
			if(fi.getPid()==pid){
				return fi;
			}
		}
		session.getTransaction().commit();
		return null;
	}

	public User createFavorite(int id) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, id);
		Favorite favorite = new Favorite();
		user.setFavorite(favorite);
		favorite.setUser(user);
		session.save(favorite);
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		return user;
	}
	//�����ղ���
	public User createFavoriteItem(User user) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.update(user.getFavorite());
		session.getTransaction().commit();
		return user;
		/*Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		session.getTransaction().commit();*/
		 /*
	        //�õ����������
	        ScrollableResults scroll = query.scroll();
	        //���������һ��
	        scroll.last();
	        int i = scroll.getRowNumber() + 1;
	        System.out.println("�ܼ�·����" + i);
	    */
		
	}

	public User finUserById(int id) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		User user=(User) session.get(User.class, id);
		session.getTransaction().commit();
		return user;
	}

	public List<FavoriteItem> getFavoriteItems(int id) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		//���ܽ���������
		//Favorite favorite = (Favorite) session.get(Favorite.class, id);
		List<FavoriteItem> list =  (List<FavoriteItem>) session.createQuery("from FavoriteItem where fid = :fid1")
				.setParameter("fid1", id)
				.setCacheable(true)
				.list();
		//Favorite favorite =  (Favorite) session.createQuery("from Favorite where id = :id1").setParameter("id1", id).uniqueResult();
		//Favorite favorite = (Favorite) session.load(Favorite.class, id);
		session.getTransaction().commit();
		return list;
	}

	public void deleteFavoriteItem(int id) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		FavoriteItem fi = new FavoriteItem();
		fi.setId(id);
		session.delete(fi);
		session.getTransaction().commit();		
	}

}
