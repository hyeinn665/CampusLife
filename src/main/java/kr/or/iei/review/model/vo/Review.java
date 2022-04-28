package kr.or.iei.review.model.vo;

public class Review {
	private int reviewNo;
	private int subjectNo;
	private int memberNo;
	private String subjectName;
	private String reviewContent;
	private int reviewScore;
	private String reqDate;
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review(int reviewNo, int subjectNo, int memberNo, String subjectName, String reviewContent, int reviewScore,
			String reqDate) {
		super();
		this.reviewNo = reviewNo;
		this.subjectNo = subjectNo;
		this.memberNo = memberNo;
		this.subjectName = subjectName;
		this.reviewContent = reviewContent;
		this.reviewScore = reviewScore;
		this.reqDate = reqDate;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	
}
