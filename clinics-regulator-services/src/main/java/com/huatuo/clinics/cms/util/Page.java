package com.huatuo.clinics.cms.util;

import java.io.Serializable;
import java.util.List;
/**
 * 分页对象
 * @author zhanglm
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class Page<T>  implements Serializable{

	private int start;
	private int limit;
	private int total;//总记录 
	private int pageSize;//每页显示
	private int totalPage;//共多少页
	private int currentPage;//当前页
	private List<T> list;//结果集
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Override
	public String toString() {
		return "Page [start=" + start + ", limit=" + limit + ", total=" + total
				+ ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", currentPage=" + currentPage + ", list=" + list + "]";
	}
	
	public Page(int total, int pageNo, int pageSize){
		if(pageNo == 0 ){
			pageNo ++;
		}
		this.start = (pageNo -1) * pageSize;
		this.total = total;
		this.pageSize = pageSize;
		this.currentPage = pageNo;
		this.totalPage = (total + pageSize -1) / pageSize;
	}
}
