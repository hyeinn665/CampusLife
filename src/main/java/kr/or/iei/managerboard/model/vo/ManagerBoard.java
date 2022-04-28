package kr.or.iei.managerboard.model.vo;

public class ManagerBoard {
	private int mbNo;
	private String mbTitle;
	private String mbWriter;
	private String mbContent;
	private int readCount;
	private String regDate;
	private String filename;
	private String filepath;
	private String category;
	
	
	public ManagerBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerBoard(int mbNo, String mbTitle, String mbWriter, String mbContent, int readCount, String regDate,
			String filename, String filepath, String category) {
		super();
		this.mbNo = mbNo;
		this.mbTitle = mbTitle;
		this.mbWriter = mbWriter;
		this.mbContent = mbContent;
		this.readCount = readCount;
		this.regDate = regDate;
		this.filename = filename;
		this.filepath = filepath;
		this.category = category;

	}
	public int getMbNo() {
		return mbNo;
	}
	public void setMbNo(int mbNo) {
		this.mbNo = mbNo;
	}
	public String getMbTitle() {
		return mbTitle;
	}
	public void setMbTitle(String mbTitle) {
		this.mbTitle = mbTitle;
	}
	public String getMbWriter() {
		return mbWriter;
	}
	public void setMbWriter(String mbWriter) {
		this.mbWriter = mbWriter;
	}
	public String getMbContent() {
		return mbContent;
	}
	public String getMbContentBr() {
		return mbContent.replaceAll("\r\n", "<br>");
	}
	public void setMbContent(String mbContent) {
		this.mbContent = mbContent;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
}



