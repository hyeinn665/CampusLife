package kr.or.iei.review.model.vo;

public class Subject {
	private int subjectNo;
	private String subjectName;
	private String startTime;
	private int durationTime;
	private int grade;
	private int memberNo;
	private int studentNo;
	private double scoreAvg;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subject(int subjectNo, String subjectName, String startTime, int durationTime, int grade, int memberNo,
			int studentNo, double scoreAvg) {
		super();
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
		this.startTime = startTime;
		this.durationTime = durationTime;
		this.grade = grade;
		this.memberNo = memberNo;
		this.scoreAvg = scoreAvg;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public double getScoreAvg() {
		return scoreAvg;
	}

	public void setScoreAvg(double scoreAvg) {
		this.scoreAvg = scoreAvg;
	}
}
