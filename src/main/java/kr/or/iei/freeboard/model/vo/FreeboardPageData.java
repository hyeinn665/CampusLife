package kr.or.iei.freeboard.model.vo;

import java.util.ArrayList;

public class FreeboardPageData {
	private ArrayList<Freeboard> list;
    private String pageNavi;
	public FreeboardPageData(ArrayList<Freeboard> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public FreeboardPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Freeboard> getList() {
		return list;
	}
	public void setList(ArrayList<Freeboard> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
    

}
