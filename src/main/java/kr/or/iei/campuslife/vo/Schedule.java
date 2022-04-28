package kr.or.iei.campuslife.vo;

public class Schedule {
	private int scheduleNo;
	private int subjectNo;
	private int profNo;
	private String scheduleA;
	private int scheduleB;
	private int stuNo;
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Schedule(int scheduleNo, int subjectNo, int profNo, String scheduleA, int scheduleB, int StuNo) {
		super();
		this.scheduleNo = scheduleNo;
		this.subjectNo = subjectNo;
		this.profNo = profNo;
		this.scheduleA = scheduleA;
		this.scheduleB = scheduleB;
		this.stuNo = stuNo;
	}
	public int getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	public int getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(int subjectNo) {
		this.subjectNo = subjectNo;
	}
	public int getProfNo() {
		return profNo;
	}
	public void setProfNo(int profNo) {
		this.profNo = profNo;
	}
	public String getScheduleA() {
		return scheduleA;
	}
	public void setScheduleA(String scheduleA) {
		this.scheduleA = scheduleA;
	}
	public int getScheduleB() {
		return scheduleB;
	}
	public void setScheduleB(int scheduleB) {
		this.scheduleB = scheduleB;
	}
	public int getStuNo() {
		return stuNo;
	}
	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}
	
}
