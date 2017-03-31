package edu.ts.entity;

public class Page {
	private int currentPage;//当前页
	private int pageSize;//页面容量
	private int pageNumber;//总页数
	private int record;//总记录数
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getpageSize() {
		return pageSize;
	}
	public void setpageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		pageNumber=(record%pageSize==0?record/pageSize:record/pageSize+1);
		return pageNumber;
	}
	public int getRecord() {
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}
	public Page(int currentPage, int pageSize, int record) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.record = record;
	}
	public Page() {
		super();
	}
}
