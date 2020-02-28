package com.service;

import com.dao.MessageDao;
import com.domain.Message;
import com.domain.PageMessageBean;

public class MessageService {
	MessageDao md =new MessageDao();
	//ɾ������
	public void deleteMessage(Integer id) {
		md.deleteMessage(id);	
	}
	//�����û�����
	public void saveMessage(Message message) {
		md.saveMessage(message);
	}
	//��ҳ����
	public PageMessageBean findPage(int currPage, int pageSize) {
		return md.findPage(currPage, pageSize);
	}
	//����message��id�ҵ�message��¼
	public Message getMessage(Integer id) {
		return md.getMessage(id);
	}

}
