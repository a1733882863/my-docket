package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.junit.Test;

import com.Utils.HibernateUtils;
import com.domain.Order;
import com.domain.Pagebean;
import com.domain.Product;

import javassist.expr.ExprEditor;

public class BookDao {
	int currentPage=1;int pageSize=3; String category="";
	//�õ�����ͼ���ܼ�¼��PageServlet.java
	public int count(String category) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Criteria criteria= session.createCriteria(Product.class);
		if(!"".equals(category)){
			criteria.add(Restrictions.eq("category", category));
		}
		//��ѯ�ܼ�¼��
		criteria.setProjection(Projections.rowCount());
		List list = criteria.list();
		//��List���ͱ�Ϊint����
		int i =Integer.valueOf(String.valueOf(list.get(0))).intValue();
		session.getTransaction().commit();
		return i;
		
	}
	//	��ҳ��ѯ
	public List<Product> findBooks(int currentPage, int pageSize, String category) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Criteria criteria= session.createCriteria(Product.class);
		criteria.setFirstResult((currentPage-1)*pageSize);
		criteria.setMaxResults(pageSize);
		criteria.setCacheable(true);
		if(!"".equals(category)){
			criteria.add(Restrictions.eq("category", category));
		}
		List<Product> list = criteria.list();
		session.getTransaction().commit();
		return list;
	}
	//admin�ķ�ҳ��ѯ������HQL
	public List<Product> findAllbooks(int currentPage, int pageSize, String category) {
		
        Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Product");

        //�õ����������
        ScrollableResults scroll = query.scroll();
        //���������һ��
        scroll.last();
        int i = scroll.getRowNumber() + 1;
        System.out.println("�ܼ�·����" + i);

        //���÷�ҳλ��
        query.setFirstResult((currentPage-1)*pageSize);
        query.setMaxResults(pageSize);

        List<Product> list = query.list();
		session.getTransaction().commit();
		return list;
	}
	//admin����ͼ��id����ͼ��
	public Product finBookById(String str) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		int id = Integer.parseInt(str);
		Criteria criteria= session.createCriteria(Product.class);
		/*criteria.add(Restrictions.eq("id", id)).setCacheable(true);
		Product product = (Product) criteria.uniqueResult();*/
		//Product product = (Product) session.createQuery("from Product where id=?").setParameter(0, id).uniqueResult();
		//֤����������ʹ�óɹ�
		Product product = (Product) session.get(Product.class, id);
		session.getTransaction().commit();
		return product;
		
	}
	//admin��bookdao,ɾ������ͼ��
	public void delBook(int productid) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.delete((Product) session.get(Product.class, productid));
		session.getTransaction().commit();
	}
	//admin�������ɾ��,hql��ƴװ��ɾ�����
	public void delAllBook(String hql) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		session.getTransaction().commit();
	}
	//admin,���ͼ��
	public void addBook(Product book) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.save(book);
		session.getTransaction().commit();
		
	}
	public void updateBook(Product book) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.update(book);
		session.getTransaction().commit();
		
	}
	//admin ����ͼ��,ģ����ѯ
	public List<Product> searchBook(String id1, String category, String name, String minprice1, String maxprice1) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		
		
		Criteria cr = session.createCriteria(Product.class);  
		if(!"".equals(id1.trim())){//ȥ���ո�
			int id=Integer.parseInt(id1);
			cr.add(Restrictions.eq("id", id));
		}
		if(!"".equals(category.trim())){
			cr.add(Restrictions.eq("category", category));
		}
		if(!"".equals(name.trim())){
			cr.add(Expression.like("name","%"+name+"%"));
		}
		if(!"".equals(minprice1.trim())){
			double minprice = Double.parseDouble(minprice1);
			cr.add(Restrictions.ge("price", minprice));
		}
		if(!"".equals(maxprice1.trim())){
			double maxprice = Double.parseDouble(maxprice1);
			cr.add(Restrictions.between("price", 0.0, maxprice));
		}
		
		List<Product> list = cr.list();
		session.getTransaction().commit();
		return list;
	}
	//ģ����ѯ
	public List<Object> searchBookByName(String name) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		String hql="select name from Product as product where product.name like :name"; 
		Query query = session.createQuery(hql);
		 query.setString("name", "%"+name+"%");
		List<Object> list = query.list();
		session.getTransaction().commit();
		return list;
	}
	//�������õ�
	public List<Product> findBookByName(String name) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		List<Product> list = session.createQuery("from Product where name = :name1").setString("name1", name).list();	
		session.getTransaction().commit();
		return list;
	}


}
