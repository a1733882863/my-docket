package com.domain;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Demo1 {
	public static void main(String [] args){
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		 try {
		Session session1=sessionFactory.openSession();
        Session session2=sessionFactory.openSession();
        Purse pur1=(Purse)session1.createQuery("from Purse s where s.id=1").uniqueResult();
        Purse pur2=(Purse)session2.createQuery("from Purse s where s.id=1").uniqueResult();
        
        //这时候，两个版本号是相同的
        System.out.println("v1="+pur1.getVersion()+"--v2="+pur2.getVersion());
        
       
			Transaction tx1=session1.beginTransaction();
			pur1.setMoney(700);
			tx1.commit();
			session1.close();
			//这时候，两个版本号是不同的，其中一个的版本号递增了
			System.out.println("v1="+pur1.getVersion()+"--v2="+pur2.getVersion());
			
			Transaction tx2=session2.beginTransaction();
			pur2.setMoney(1000);
			tx2.commit();
			session2.close();
		} catch (org.hibernate.StaleObjectStateException e) {
			System.out.println("这里错了,异常捕获到了");
		}
        
		
		
	}
}
