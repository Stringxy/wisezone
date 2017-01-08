package com.web.util;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 分页工具类
 * @author Administrator
 *
 * @param <T>  代替对象    User,Employee
 */
@Component
public class PageUtil<T> {

	
    private int pageIndex = 1; //当前的页码    默认为1
    private int pageSize = 3;//每页显示的条数
    private int totalCount;//总的条数(注明：需要计算）
    private int totalPage;//总共多少页 = 总的条数  / 每页显示的条数
    private List<T> data;//要显示的数据
    
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return (int)Math.ceil((this.totalCount * 1.0) / this.pageSize);
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
    
	/**
	 * limit ?,?
	 * 排除前面多少条数据
	 * >= ((2-1)*3 + 1)
	 * @return
	 */
	public int getBegin(){
//		return (this.pageIndex - 1) * this.pageSize + 1;//jdbc编程：Oracle
		return (this.pageIndex - 1) * this.pageSize;//Hibernate:MySql、Oracle
	}
    
	/**
	 * 取得多少条数
	 * <= (2*3))t1
	 * @return
	 */
	public int getEnd(){
//		return this.pageIndex * this.pageSize;//jdbc编程:Oracle
		return this.pageSize;//Hibernate:Mysql、Oracle
	}
	
	/**
	 * 上一页
	 * @return
	 */
	public int getPre(){
		return this.pageIndex - 1 > 0 ? this.pageIndex - 1 : 1;
	}
	/**
	 * 下一页
	 * @return
	 */
	public int getNext(){
		return this.getPageIndex() + 1 > this.getTotalPage() ? this.getTotalPage() : this.getPageIndex() + 1;
	}
}
