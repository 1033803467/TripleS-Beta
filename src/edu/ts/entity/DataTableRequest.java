package edu.ts.entity;

public class DataTableRequest {
	 Object orderColumn;
	 Object orderDir;
	 Object start;
	 Object length;
	 Object search;
	public DataTableRequest(Object orderColumn, Object orderDir, Object start,
			Object length, Object search) {
		super();
		this.orderColumn = orderColumn;
		this.orderDir = orderDir;
		this.start = start;
		this.length = length;
		this.search = search;
	}
	public DataTableRequest() {
		super();
	}
	
	public DataTableRequest(Object search) {
		super();
		this.search = search;
	}
	public Object getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(Object orderColumn) {
		this.orderColumn = orderColumn;
	}
	public Object getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(Object orderDir) {
		this.orderDir = orderDir;
	}
	public Object getStart() {
		return start;
	}
	public void setStart(Object start) {
		this.start = start;
	}
	public Object getLength() {
		return length;
	}
	public void setLength(Object length) {
		this.length = length;
	}
	public Object getSearch() {
		return search;
	}
	public void setSearch(Object search) {
		this.search = search;
	}
	 
}
