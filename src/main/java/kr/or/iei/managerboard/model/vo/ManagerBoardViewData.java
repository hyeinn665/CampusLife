package kr.or.iei.managerboard.model.vo;

import java.util.ArrayList;


public class ManagerBoardViewData {
	private ManagerBoard mb;
	private ArrayList<NoticeComment> commentList; //댓글용
	private ArrayList<NoticeComment> reCommentList;	//대댓글용
	public ManagerBoardViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerBoardViewData(ManagerBoard mb, ArrayList<NoticeComment> commentList,
			ArrayList<NoticeComment> reCommentList) {
		super();
		this.mb = mb;
		this.commentList = commentList;
		this.reCommentList = reCommentList;
	}
	public ManagerBoard getMb() {
		return mb;
	}
	public void setMb(ManagerBoard mb) {
		this.mb = mb;
	}
	public ArrayList<NoticeComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(ArrayList<NoticeComment> commentList) {
		this.commentList = commentList;
	}
	public ArrayList<NoticeComment> getReCommentList() {
		return reCommentList;
	}
	public void setReCommentList(ArrayList<NoticeComment> reCommentList) {
		this.reCommentList = reCommentList;
	}
	
	
}
