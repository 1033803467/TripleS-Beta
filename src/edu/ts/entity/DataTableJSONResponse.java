package edu.ts.entity;

public class DataTableJSONResponse {
	 Object draw;
	 Object recordsTotal; //查询的记录数
	 Object recordsFiltered; //过滤后的记录数
	 Object infos;
	 
	public DataTableJSONResponse() {
		super();
	}
	public DataTableJSONResponse(Object draw, Object recordsTotal,
			Object recordsFiltered, Object infos) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.infos = infos;
	}
	
	public DataTableJSONResponse(Object infos) {
		super();
		this.infos = infos;
	}
	public Object getDraw() {
		return draw;
	}
	public void setDraw(Object draw) {
		this.draw = draw;
	}
	public Object getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Object recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Object getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Object recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Object getInfos() {
		return infos;
	}
	public void setInfos(Object infos) {
		this.infos = infos;
	}
	 
}
