package com.domain;

import java.lang.reflect.Array;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.Utils.HibernateUtils;

public class Demo {
	
		@Test
		public void Test00(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			/*Order order = (Order) session.createQuery("from Order where id=?").setParameter(0, 1111).uniqueResult();
			System.out.println(order);*/
		    session.getTransaction().commit();
			
			}	
		//hql��ҳ��ѯ����
		@Test
		public void Test0(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			//...................................................................
			Query query = session.createQuery("from Product");

			//�õ����������
			ScrollableResults scroll = query.scroll();
			//���������һ��
			scroll.last();
	        int i = scroll.getRowNumber() + 1;
	        System.out.println("�ܼ�·����" + i);

	        //���÷�ҳλ��
	        query.setFirstResult(0);
	        query.setMaxResults(2);

	        System.out.println(query.list());
	        //...................................................................
	        session.getTransaction().commit();
		
		}
		
		//java.sql.Date ��java.util.Date�Ļ���ת��
		@Test
		public void Test1(){ 
			//java.sql.Date date=java.sql.Date.valueof(str);
			/*Date nowday = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(nowday);
			java.sql.Date date = java.sql.Date.valueOf(time);
			System.out.println(date);*/
			/*Date date;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
				java.sql.Date hiredate = new java.sql.Date(date.getTime());
				System.out.println(hiredate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		
		}
		
		//��Sql��ѯ���ص�List<Object[]>���з�װ
		@Test
			public void Test2(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			//...................................................................
			List<Orderitem> list = (List<Orderitem>) session.createSQLQuery("select * from t_orderitem where oid = 1").addEntity(Orderitem.class).list();
			System.out.println(list.toString());
			//...................................................................
			session.getTransaction().commit();
		}
		
		//����̬ת�־�̬
		@Test
		public void Test3(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			//...................................................................
			User us = new User();
			us.setId(10);
			us.setGender("���в�Ů");
			session.saveOrUpdate(us);
			//...................................................................
			session.getTransaction().commit();
		}
		
		//��ΪUserû�������ά��,���Կ��԰�Order�����������,����get  Order���Եõ�User����
		@Test
		public void Test4(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			User user = (User) session.get(User.class, 4);
			Order o = new Order();
			user.getOrders().add(o);
			session.saveOrUpdate(user);
			session.getTransaction().commit();
			System.out.println("...............��session�ķֽ���....................");
			//...................................................................
			Session session1 = HibernateUtils.openSession();
			session1.beginTransaction();
			Order order = (Order) session1.get(Order.class, 10);
			System.out.println(order.getUser().getId());
			session1.getTransaction().commit();
		}
		
		//���һ�����ܷ���ά����ϵ ,����������ζ�����ʹ�ö��һ��ά����ϵ
		@Test
		public void Test5(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			//...................................................................
			User user = new User();
			user.setId(4);
			Order order = new Order();
			order.setUser(user);
			session.save(order);
			//����ֱ�Ӳ��,��Ϊsave��ִ��,
			User user1=(User) session.get(User.class, 4);
			System.out.println(user1.getOrders());
			//...................................................................
			session.getTransaction().commit();
		}
		
		//Order��������User,
		@Test
		public void Test6(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			//...................................................................
			Order order = (Order) session.get(Order.class,18);
			for(Orderitem oi : order.getOrderitem()){
				System.out.println(oi.getProduct());
			}
			//...................................................................
			session.getTransaction().commit();
			//...................................................................
			
		}
		
		//����ɾ����һЩsql���
		@Test
		public void Test7(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
		/*	//String queryString = "delete from UserFolder as model where model.userId = ?";
			String sql = "update Order set paystate=0 where id=?";
			session.createQuery (sql).setParameter(0, 1).executeUpdate() ;
			 */
			session.getTransaction().commit();
			//...................................................................
			
		}
		
		//���Զ�������,�໺��
		@Test
		public void Test8(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			session.get(Product.class, 1);
			session.getTransaction().commit();
			
			//...................................................................
			
			Session session1 = HibernateUtils.openSession();
			session1.beginTransaction();
			session1.get(Product.class, 1);
			session1.getTransaction().commit();
		}
		
		//���Զ�������,���ϻ������   �ڼ��ϻ�������ŵ���id   ��Ҫ��hibernate������Favorieitem��id��Ӧ������໺��   
		//���ϻ����������Favorite��id �� FavoriteItem��id
		@Test
		public void Test9(){ 
				Session session = HibernateUtils.openSession();
				session.beginTransaction();
				Favorite favorite = (Favorite) session.get(Favorite.class, 1);
				for(FavoriteItem fi : favorite.getFavoriteItems()){
					System.out.println(fi.getName());
				}
					
				session.clear();
				Favorite favorite2 = (Favorite) session.get(Favorite.class, 1);
				for(FavoriteItem fi : favorite2.getFavoriteItems()){
					System.out.println(fi.getName());
				}
				session.getTransaction().commit();
		}
		
		//���Զ�������,��ѯ����   �����hibernate�������Favoriteע�͵�, ��ô�᲻������.  
		//�ò�ѯ����ԭ��ͼ��ϻ����� �����ﻺ�����sql��� �� �����id
		@Test
		public void Test10(){ 
				Session session = HibernateUtils.openSession();
				session.beginTransaction();
				Query query = session.createQuery("from FavoriteItem");
				//Ӧ��hql��������,�����ֶ�����,��Ϊhql���ܲ�ѯ�ܶණ��
				//��ѯʱ,���ȴӶ���������ȡ����,ȡ������ִ�����ݿ��ѯ,
				query.setCacheable(true);//��ѯ���ݿ������������
				
				List<FavoriteItem> list = query.list();
				session.clear();
					
				Query query2 = session.createQuery("from FavoriteItem");
				//�������ע�͵�������һ��  hqlֱ��ȥ���ݿ��ѯ
				query2.setCacheable(true);
				List<FavoriteItem> list2 = query2.list();
					
				session.getTransaction().commit();
					
		}
		
		//��������Ĳ�ѯ����,����ڶ��� ��ѯ,��ʹ�ö�������,��һ��˵����,��ѯ��������ŵ���sql���Ͳ�ѯ�����id
		@Test
		public void Test11(){ 
				Session session = HibernateUtils.openSession();
				session.beginTransaction();
				Query query = session.createQuery("from FavoriteItem");
				//Ӧ��hql��������,�����ֶ�����,��Ϊhql���ܲ�ѯ�ܶණ��
				//��ѯʱ,���ȴӶ���������ȡ����,ȡ������ִ�����ݿ��ѯ,
				query.setCacheable(true);//��ѯ���ݿ������������
							
				List<FavoriteItem> list = query.list();
				session.clear();
							
				Query query2 = session.createQuery("from FavoriteItem where id = 4");
				//�������ע�͵�������һ��  hqlֱ��ȥ���ݿ��ѯ
				query2.setCacheable(true);
				List<FavoriteItem> list2 = query2.list();
							
				session.getTransaction().commit();
							
		}
		
		//ʱ���������,��¼���Ǳ��Լ���������ʱ��(�Զ���ķ�ʽ���в���)
		//�û��治Ҫ��������
		@Test
		public void Test12(){ 
				Session session = HibernateUtils.openSession();
				session.beginTransaction();
				FavoriteItem favoriteItem = (FavoriteItem) session.get(FavoriteItem.class, 4);
				//��ע�͵���ִ��2��
				/*session.createQuery("update FavoriteItem set name=? where id = ?")
						.setParameter(0, "uuid")  //����Ҳ��
						//.setString(0, "uuid")
						.setInteger(1, 4).executeUpdate();*/
				session.clear();
				FavoriteItem favoriteItem2 = (FavoriteItem) session.get(FavoriteItem.class, 4);	//�Ա�����ʱ��,���²�ѯ���ݿ�
				session.getTransaction().commit();
							
		}
		@Test
		public void Test13(){ 
				Session session = HibernateUtils.openSession();
				session.beginTransaction();
				FavoriteItem favoriteItem = (FavoriteItem) session.get(FavoriteItem.class, 4);
				session.clear();
				FavoriteItem favoriteItem2 = (FavoriteItem) session.get(FavoriteItem.class, 4);	//�Ա�����ʱ��,���²�ѯ���ݿ�
				session.getTransaction().commit();

				Session session1 = HibernateUtils.openSession();
				session1.beginTransaction();
				FavoriteItem favoriteItem3 = (FavoriteItem) session1.get(FavoriteItem.class, 4);	//�Ա�����ʱ��,���²�ѯ���ݿ�
				session1.getTransaction().commit();			
		}
		//sql.Date���new
		@Test
		public void Test14(){ 
			Date date = new Date(0);
			System.out.println(date);
			
		}	
		@Test
		public void Test15(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			List<User> list = session.createQuery("from User where email = :useremail")
					.setParameter("useremail", "m15566284139@163.com").list();
			System.out.println(list);
			User u = null;
			for (int i = 0; i < list.size(); i++) {
				u =  list.get(i);
				if (u != null)
					break;
			}
			if (u != null) {
				System.out.println("��Ϊ null");
			} else {
				session.getTransaction().commit();
				System.out.println("Ϊ0");
			}
		}
		//�ֹ�������
				@Test
				public void Test16(){ 
					Session session = HibernateUtils.openSession();
					session.beginTransaction();
					Purse purse = new Purse();
					purse.setMoney(1000);
					session.save(purse);
					session.getTransaction().commit();
				}
		//string��double
				@Test
				public void Test18(){ 
					Double money = Double.valueOf("1000");
					System.out.println(money);
				}
}
