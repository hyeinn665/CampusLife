package kr.or.iei.member.model.vo;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private int age;
	private String phone;
	private String memberAddr;
	private String grade;
	private String enrollDate;
	private int black1;
	private int black2;
	private int black3;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(int memberNo, String memberId, String memberPw, String memberName, int age, String phone,
			String memberAddr, String grade, String enrollDate, int black1, int black2, int black3) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.age = age;
		this.phone = phone;
		this.memberAddr = memberAddr;
		this.grade = grade;
		this.enrollDate = enrollDate;
		this.black1 = black1;
		this.black2 = black2;
		this.black3 = black3;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public int getBlack1() {
		return black1;
	}
	public void setBlack1(int black1) {
		this.black1 = black1;
	}
	public int getBlack2() {
		return black2;
	}
	public void setBlack2(int black2) {
		this.black2 = black2;
	}
	public int getBlack3() {
		return black3;
	}
	public void setBlack3(int black3) {
		this.black3 = black3;
	}

}
