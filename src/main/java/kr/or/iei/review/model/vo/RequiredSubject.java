package kr.or.iei.review.model.vo;

public class RequiredSubject {
	private int subjectNo;
	private String subjectName;
	private int avgScore;
	private int studentNo;
	public RequiredSubject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequiredSubject(int subjectNo, String subjectName, int avgScore, int studentNo) {
		super();
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
		this.avgScore = avgScore;
		this.studentNo = studentNo;
	}
	public int getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(int subjectNo) {
		this.subjectNo = subjectNo;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(int avgScore) {
		this.avgScore = avgScore;
	}
	public int getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}
	
}
