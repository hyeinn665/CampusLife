package kr.or.iei.freeboard.model.vo;

public class Freeboard {

	private int freeNo;
	private int memberNo;
	private String freeTitle;
	private String freeContent;
	private int readCount;
	private int goodCount;
	private String freeDate;
	private String fileName;
	private String filePath;
	public Freeboard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Freeboard(int freeNo, int memberNo, String freeTitle, String freeContent, int readCount, int goodCount,
			String freeDate, String fileName, String filePath) {
		super();
		this.freeNo = freeNo;
		this.memberNo = memberNo;
		this.freeTitle = freeTitle;
		this.freeContent = freeContent;
		this.readCount = readCount;
		this.goodCount = goodCount;
		this.freeDate = freeDate;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	public int getFreeNo() {
		return freeNo;
	}
	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getFreeTitle() {
		return freeTitle;
	}
	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}
	public String getFreeContent() {
		return freeContent;
	}
	public String getFreeContentBr() {
		return freeContent.replaceAll("\r\n", "<br>");
	}
	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}
	public String getFreeDate() {
		return freeDate;
	}
	public void setFreeDate(String freeDate) {
		this.freeDate = freeDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}