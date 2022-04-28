package kr.or.iei.book.model.vo;

import java.util.ArrayList;

public class BookSearchData {
	private ArrayList<Book> list;
	private String search;
	public BookSearchData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookSearchData(ArrayList<Book> list, String search) {
		super();
		this.list = list;
		this.search = search;
	}
	public ArrayList<Book> getList() {
		return list;
	}
	public void setList(ArrayList<Book> list) {
		this.list = list;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
}
