package kr.or.iei.book.model.vo;

import java.util.ArrayList;
import java.util.HashMap;


public class BookViewData {
	private HashMap<Integer, String> map;
	private Book b;
	private ArrayList<BookComment> commentList;
	private ArrayList<BookComment> reCommentList;
	public BookViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookViewData(HashMap<Integer, String> map, Book b, ArrayList<BookComment> commentList,
			ArrayList<BookComment> reCommentList) {
		super();
		this.map = map;
		this.b = b;
		this.commentList = commentList;
		this.reCommentList = reCommentList;
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
	public ArrayList<BookComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(ArrayList<BookComment> commentList) {
		this.commentList = commentList;
	}
	public ArrayList<BookComment> getReCommentList() {
		return reCommentList;
	}
	public void setReCommentList(ArrayList<BookComment> reCommentList) {
		this.reCommentList = reCommentList;
	}
	
	
}
