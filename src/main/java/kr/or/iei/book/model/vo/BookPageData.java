package kr.or.iei.book.model.vo;

import java.util.ArrayList;
import java.util.HashMap;

public class BookPageData {
	private ArrayList<Book> list;
	private String pageNavi;
	private HashMap<Integer, String> map;
	private int totalCount;
	private int pageNo;
	public BookPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookPageData(ArrayList<Book> list, String pageNavi, HashMap<Integer, String> map, int totalCount,
			int pageNo) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.map = map;
		this.totalCount = totalCount;
		this.pageNo = pageNo;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public ArrayList<Book> getList() {
		return list;
	}
	public void setList(ArrayList<Book> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public HashMap<Integer, String> getMap() {
		return map;
	}
	public void setMap(HashMap<Integer, String> map) {
		this.map = map;
	}
	
}
