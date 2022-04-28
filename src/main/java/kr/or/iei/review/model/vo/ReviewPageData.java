package kr.or.iei.review.model.vo;

import java.util.ArrayList;


public class ReviewPageData {
	private ArrayList<Review> list;
	private String pageNavi;
	public ReviewPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewPageData(ArrayList<Review> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Review> getList() {
		return list;
	}
	public void setList(ArrayList<Review> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
