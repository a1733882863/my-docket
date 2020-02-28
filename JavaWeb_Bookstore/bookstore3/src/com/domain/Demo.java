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
		//hql分页查询测试
		@Test
		public void Test0(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			//...................................................................
			Query query = session.createQuery("from Product");

			//得到滚动结果集
			ScrollableResults scroll = query.scroll();
			//滚动到最后一行
			scroll.last();
	        int i = scroll.getRowNumber() + 1;
	        System.out.println("总计路数：" + i);

	        //设置分页位置
	        query.setFirstResult(0);
	        query.setMaxResults(2);

	        System.out.println(query.list());
	        //...................................................................
	        session.getTransaction().commit();
		
		}
		
		//java.sql.Date 和java.util.Date的互相转换
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
		
		//对Sql查询返回的List<Object[]>进行封装
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
		
		//游离态转持久态
		@Test
		public void Test3(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			//...................................................................
			User us = new User();
			us.setId(10);
			us.setGender("不男不女");
			session.saveOrUpdate(us);
			//...................................................................
			session.getTransaction().commit();
		}
		
		//因为User没放弃外键维护,所以可以帮Order设置他的外键,并且get  Order可以得到User对象
		@Test
		public void Test4(){ 
			Session session = HibernateUtils.openSession();
			session.beginTransaction();
			User user = (User) session.get(User.class, 4);
			Order o = new Order();
			user.getOrders().add(o);
			session.saveOrUpdate(user);
			session.getTransaction().commit();
			System.out.println("...............两session的分界线....................");
			//...................................................................
			Session session1 = HibernateUtils.openSession();
			session1.beginTransaction();
			Order order = (Order) session1.get(Order.class, 10);
			System.out.println(order.getUser().getId());
			session1.getTransaction().commit();
		}
		
		//多的一方不能放弃维护关系 ,所以无论如何都可以使用多的一方维护关系
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
			//可以直接查出,因为save先执行,
			User user1=(User) session.get(User.class, 4);
			System.out.println(user1.getOrders());
			//...................................................................
			session.getTransaction().commit();
		}
		
		//Order左外链接User,
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
		
		//更新删除的一些sql语句
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
		
		//测试二级缓存,类缓存
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
		
		//测试二级缓存,集合缓存测试   在集合缓冲区里放的是id   需要在hibernate里配置Favorieitem的id对应的类的类缓存   
		//集合缓冲区里放了Favorite的id 和 FavoriteItem的id
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
		
		//测试二级缓存,查询缓存   如果把hibernate配置里的Favorite注释掉, 那么会不起作用.  
		//该查询缓存原理和集合缓存差不多 但这里缓存的是sql语句 和 结果的id
		@Test
		public void Test10(){ 
				Session session = HibernateUtils.openSession();
				session.beginTransaction();
				Query query = session.createQuery("from FavoriteItem");
				//应用hql二级缓存,必须手动开启,因为hql可能查询很多东西
				//查询时,会先从二级缓存中取数据,取不到就执行数据库查询,
				query.setCacheable(true);//查询数据库后放入二级缓存
				
				List<FavoriteItem> list = query.list();
				session.clear();
					
				Query query2 = session.createQuery("from FavoriteItem");
				//这里如果注释掉下面这一行  hql直接去数据库查询
				query2.setCacheable(true);
				List<FavoriteItem> list2 = query2.list();
					
				session.getTransaction().commit();
					
		}
		
		//继续上面的查询缓存,这里第二次 查询,不使用二级缓存,进一步说明了,查询缓存区里放的是sql语句和查询结果的id
		@Test
		public void Test11(){ 
				Session session = HibernateUtils.openSession();
				session.beginTransaction();
				Query query = session.createQuery("from FavoriteItem");
				//应用hql二级缓存,必须手动开启,因为hql可能查询很多东西
				//查询时,会先从二级缓存中取数据,取不到就执行数据库查询,
				query.setCacheable(true);//查询数据库后放入二级缓存
							
				List<FavoriteItem> list = query.list();
				session.clear();
							
				Query query2 = session.createQuery("from FavoriteItem where id = 4");
				//这里如果注释掉下面这一行  hql直接去数据库查询
				query2.setCacheable(true);
				List<FavoriteItem> list2 = query2.list();
							
				session.getTransaction().commit();
							
		}
		
		//时间戳缓冲区,记录的是表以及最后操作的时间(以对象的方式进行操作)
		//该缓存不要额外配置
		@Test
		public void Test12(){ 
				Session session = HibernateUtils.openSession();
				session.beginTransaction();
				FavoriteItem favoriteItem = (FavoriteItem) session.get(FavoriteItem.class, 4);
				//不注释掉会执行2次
				/*session.createQuery("update FavoriteItem set name=? where id = ?")
						.setParameter(0, "uuid")  //这样也行
						//.setString(0, "uuid")
						.setInteger(1, 4).executeUpdate();*/
				session.clear();
				FavoriteItem favoriteItem2 = (FavoriteItem) session.get(FavoriteItem.class, 4);	//对比两个时间,重新查询数据库
				session.getTransaction().commit();
							
		}
		@Test
		public void Test13(){ 
				Session session = HibernateUtils.openSession();
				session.beginTransaction();
				FavoriteItem favoriteItem = (FavoriteItem) session.get(FavoriteItem.class, 4);
				session.clear();
				FavoriteItem favoriteItem2 = (FavoriteItem) session.get(FavoriteItem.class, 4);	//对比两个时间,重新查询数据库
				session.getTransaction().commit();

				Session session1 = HibernateUtils.openSession();
				session1.beginTransaction();
				FavoriteItem favoriteItem3 = (FavoriteItem) session1.get(FavoriteItem.class, 4);	//对比两个时间,重新查询数据库
				session1.getTransaction().commit();			
		}
		//sql.Date如何new
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
				System.out.println("不为 null");
			} else {
				session.getTransaction().commit();
				System.out.println("为0");
			}
		}
		//乐观锁测试
				@Test
				public void Test16(){ 
					Session session = HibernateUtils.openSession();
					session.beginTransaction();
					Purse purse = new Purse();
					purse.setMoney(1000);
					session.save(purse);
					session.getTransaction().commit();
				}
		//string变double
				@Test
				public void Test18(){ 
					Double money = Double.valueOf("1000");
					System.out.println(money);
				}
}
