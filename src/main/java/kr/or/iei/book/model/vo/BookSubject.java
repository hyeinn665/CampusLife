package kr.or.iei.book.model.vo;

import java.util.HashMap;

public class BookSubject {
	private HashMap<Integer, String> map;
	private Book b;
	public BookSubject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookSubject(HashMap<Integer, String> map, Book b) {
		super();
		this.map = map;
		this.b = b;
	}
	public HashMap<Integer, String> getMap() {
		return map;
	}
	public void setMap(HashMap<Integer, String> map) {
		this.map = map;
	}
	public Book getB() {
		return b;
	}
	public void setB(Book b) {
		this.b = b;
	}
	
}
