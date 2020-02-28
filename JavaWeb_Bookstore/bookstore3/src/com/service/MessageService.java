package com.service;

import com.dao.MessageDao;
import com.domain.Message;
import com.domain.PageMessageBean;

public class MessageService {
	MessageDao md =new MessageDao();
	//删除留言
	public void deleteMessage(Integer id) {
		md.deleteMessage(id);	
	}
	//保存用户留言
	public void saveMessage(Message message) {
		md.saveMessage(message);
	}
	//分页查找
	public PageMessageBean findPage(int currPage, int pageSize) {
		return md.findPage(currPage, pageSize);
	}
	//根据message的id找到message记录
	public Message getMessage(Integer id) {
		return md.getMessage(id);
	}

}
