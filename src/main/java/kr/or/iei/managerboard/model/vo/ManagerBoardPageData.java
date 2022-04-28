package kr.or.iei.managerboard.model.vo;

import java.util.ArrayList;

public class ManagerBoardPageData {
	private ArrayList<ManagerBoard> list;
	private String pageNavi;
	
	public ManagerBoardPageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManagerBoardPageData(ArrayList<ManagerBoard> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}

	public ArrayList<ManagerBoard> getList() {
		return list;
	}

	public void setList(ArrayList<ManagerBoard> list) {
		this.list = list;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
