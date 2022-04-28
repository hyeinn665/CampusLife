package kr.or.iei.member.model.vo;

public class Qna {
	private int qnaNo;
	private String writer;
	private String receiver;
	private String qnaTitle;
	private String qnaContent;
	private String reqDate;
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Qna(int qnaNo, String writer, String receiver, String qnaTitle, String qnaContent, String reqDate) {
		super();
		this.qnaNo = qnaNo;
		this.writer = writer;
		this.receiver = receiver;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.reqDate = reqDate;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public String getQnaContentBr() {
		return qnaContent.replaceAll("\r\n", "<br>");
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	
}
