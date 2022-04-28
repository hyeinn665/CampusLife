package kr.or.iei.book.model.vo;

public class BookComment {
	private int bcNo;
	private String bcWriter;
	private String bcContent;
	private String bcDate;
	private int bookRef;
	private int bcRef;
	private String bcSecret;
	public BookComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookComment(int bcNo, String bcWriter, String bcContent, String bcDate, int bookRef, int bcRef,
			String bcSecret) {
		super();
		this.bcNo = bcNo;
		this.bcWriter = bcWriter;
		this.bcContent = bcContent;
		this.bcDate = bcDate;
		this.bookRef = bookRef;
		this.bcRef = bcRef;
		this.bcSecret = bcSecret;
	}
	public int getBcNo() {
		return bcNo;
	}
	public void setBcNo(int bcNo) {
		this.bcNo = bcNo;
	}
	public String getBcWriter() {
		return bcWriter;
	}
	public void setBcWriter(String bcWriter) {
		this.bcWriter = bcWriter;
	}
	public String getBcContent() {
		return bcContent;
	}
	public void setBcContent(String bcContent) {
		this.bcContent = bcContent;
	}
	public String getBcDate() {
		return bcDate;
	}
	public void setBcDate(String bcDate) {
		this.bcDate = bcDate;
	}
	public int getBookRef() {
		return bookRef;
	}
	public void setBookRef(int bookRef) {
		this.bookRef = bookRef;
	}
	public int getBcRef() {
		return bcRef;
	}
	public void setBcRef(int bcRef) {
		this.bcRef = bcRef;
	}
	public String getBcSecret() {
		return bcSecret;
	}
	public void setBcSecret(String bcSecret) {
		this.bcSecret = bcSecret;
	}
}
