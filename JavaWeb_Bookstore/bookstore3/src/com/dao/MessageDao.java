package com.dao;

import java.util.List;

import org.hibernate.classic.Session;

import com.Utils.HibernateUtils;
import com.domain.Message;
import com.domain.PageMessageBean;

public class MessageDao {
	//保存用户留言
	public void saveMessage(Message message){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.saveOrUpdate(message);
		session.getTransaction().commit();	
	}
	//分页查找留言
	public PageMessageBean findPage(int currPage,int pageSize){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		String hql = "from Message m order by m.createTime desc";
		List<Message> list = session.createQuery(hql)
				.setFirstResult((currPage-1)*pageSize)
				.setMaxResults(pageSize).list();
		PageMessageBean p = new PageMessageBean();
		p.setPageSize(pageSize);
		p.setCurrPage(currPage);
		p.setList(list);
		p.setTotalRecords(getTotalRecords(session));
		session.getTransaction().commit();	
		return p;
	}
	//获取留言总条数,不查找数据库
	private int getTotalRecords(Session session) {
		String hql = "select count(*) from Message";
		Long totalRecords = (Long) session.createQuery(hql).uniqueResult();
		return totalRecords.intValue();
	}
	//删除留言
	public void deleteMessage(Integer id){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Message message = (Message) session.get(Message.class, id);
		session.delete(message);
		session.getTransaction().commit();	
	}
	//根据message的id找到message记录
	public Message getMessage(Integer id) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		Message message = (Message) session.get(Message.class, id);
		/*String hql = "from Message where uid= ?";
		Message message =   (Message) session.createQuery(hql).setInteger(0, id).uniqueResult();*/
		session.getTransaction().commit();	
		return message;
	}
}
