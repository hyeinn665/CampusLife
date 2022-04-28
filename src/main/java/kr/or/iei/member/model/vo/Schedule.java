package kr.or.iei.member.model.vo;

public class Schedule {
	private int memberNo;
	private int subjectNo;
	private String subjectName;
	private double scoreAvg;
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Schedule(int memberNo, int subjectNo, String subjectName, double scoreAvg) {
		super();
		this.memberNo = memberNo;
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
		this.scoreAvg = scoreAvg;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
	public double getScoreAvg() {
		return scoreAvg;
	}
	public void setScoreAvg(double scoreAvg) {
		this.scoreAvg = scoreAvg;
	}
	
}
