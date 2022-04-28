package kr.or.iei.freeboard.model.vo;

import java.util.ArrayList;

import kr.or.iei.book.model.vo.Book;

public class MainPageData {
	private ArrayList<Book> list;
	private ArrayList<Freeboard> freeList;
	public MainPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MainPageData(ArrayList<Book> list, ArrayList<Freeboard> freeList) {
		super();
		this.list = list;
		this.freeList = freeList;
	}
	public ArrayList<Book> getList() {
		return list;
	}
	public void setList(ArrayList<Book> list) {
		this.list = list;
	}
	public ArrayList<Freeboard> getFreeList() {
		return freeList;
	}
	public void setFreeList(ArrayList<Freeboard> freeList) {
		this.freeList = freeList;
	}
	
}
