package kr.or.iei.book.model.vo;

public class Book {
	private int bookNo;
	private int subjectNo;
	private int memberNo;
	private String bookName;
	private String bookWriter;
	private String publisher;
	private int listPrice;
	private int hopePrice;
	private String writtenTrace;
	private String tornTrace;
	private String discolor;
	private String nameTrace;
	private String sellEnd;
	private String filepath;
	private String bookComment;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int bookNo, int subjectNo, int memberNo, String bookName, String bookWriter, String publisher,
			int listPrice, int hopePrice, String writtenTrace, String tornTrace, String discolor, String nameTrace,
			String sellEnd, String filepath, String bookComment) {
		super();
		this.bookNo = bookNo;
		this.subjectNo = subjectNo;
		this.memberNo = memberNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.publisher = publisher;
		this.listPrice = listPrice;
		this.hopePrice = hopePrice;
		this.writtenTrace = writtenTrace;
		this.tornTrace = tornTrace;
		this.discolor = discolor;
		this.nameTrace = nameTrace;
		this.sellEnd = sellEnd;
		this.filepath = filepath;
		this.bookComment = bookComment;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getListPrice() {
		return listPrice;
	}
	public void setListPrice(int listPrice) {
		this.listPrice = listPrice;
	}
	public int getHopePrice() {
		return hopePrice;
	}
	public void setHopePrice(int hopePrice) {
		this.hopePrice = hopePrice;
	}
	public String getWrittenTrace() {
		return writtenTrace;
	}
	public void setWrittenTrace(String writtenTrace) {
		this.writtenTrace = writtenTrace;
	}
	public String getTornTrace() {
		return tornTrace;
	}
	public void setTornTrace(String tornTrace) {
		this.tornTrace = tornTrace;
	}
	public String getDiscolor() {
		return discolor;
	}
	public void setDiscolor(String discolor) {
		this.discolor = discolor;
	}
	public String getNameTrace() {
		return nameTrace;
	}
	public void setNameTrace(String nameTrace) {
		this.nameTrace = nameTrace;
	}
	public String getSellEnd() {
		return sellEnd;
	}
	public void setSellEnd(String sellEnd) {
		this.sellEnd = sellEnd;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public int getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(int subjectNo) {
		this.subjectNo = subjectNo;
	}
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getBookComment() {
		return bookComment;
	}

	public void setBookComment(String bookComment) {
		this.bookComment = bookComment;
	}

}
