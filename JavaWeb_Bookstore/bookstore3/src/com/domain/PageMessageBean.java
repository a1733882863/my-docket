package com.domain;

import java.util.List;

public class PageMessageBean {
	
	private int currPage;//��ǰҳ
	private int totalRecords;//�ܼ�¼��
	
	private List<?> list;//�����
	private int pageSize;//ÿҳ��¼��
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage(){//�����ҳ��
		return (totalRecords + pageSize-1)/pageSize;
	}
	public int getFirstPage(){
		return 1;
	}
	public int getPreviousPage(){
		return currPage <=1?1:currPage-1;
	}
	public int getNextPage(){
		if(currPage >= getTotalPage()){
			return getLastPage();
		}
		return currPage+1;
	}
	private int getLastPage() {
		return getTotalPage()<=0?1:getTotalPage();
	}
}
