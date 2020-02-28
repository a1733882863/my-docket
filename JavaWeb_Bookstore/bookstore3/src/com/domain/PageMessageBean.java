package com.domain;

import java.util.List;

public class PageMessageBean {
	
	private int currPage;//当前页
	private int totalRecords;//总记录数
	
	private List<?> list;//结果集
	private int pageSize;//每页记录数
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
	public int getTotalPage(){//获得总页数
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
