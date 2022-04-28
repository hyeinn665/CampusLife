package kr.or.iei.campuslife.vo;

public class Subject {
	private int subjectNo;
	private String subjectName;
	private String startTime;
	private int durationTime;
	private int grade;
	private int scoreAvg;
	private int selectSubject;
	private int profNo;
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subject(int subjectNo, String subjectName, String startTime, int durationTime, int grade, int scoreAvg, int selectSubject, int profNo) {
		super();
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
		this.startTime = startTime;
		this.durationTime = durationTime;
		this.grade = grade;
		this.scoreAvg = scoreAvg;
		this.selectSubject = selectSubject;
		this.profNo = profNo;
	}
	public int getProfNo() {
		return profNo;
	}
	public void setProfNo(int profNo) {
		this.profNo = profNo;
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
	public int getScoreAvg() {
		return scoreAvg;
	}
	public void setScoreAvg(int scoreAvg) {
		this.scoreAvg = scoreAvg;
	}
	public int getSelectSubject() {
		return selectSubject;
	}
	public void setSelectSubject(int selectSubject) {
		this.selectSubject = selectSubject;
	}
	
	
}
