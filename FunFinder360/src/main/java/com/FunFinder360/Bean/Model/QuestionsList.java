package com.FunFinder360.Bean.Model;

public class QuestionsList {
	private int questionListId;
	private String personalUserId;
	private String ownerUserId;
	private String title;
	private String content;
	private int readhit;
	private String postedDate;

	public QuestionsList() {
		// TODO Auto-generated constructor stub
	}

	public QuestionsList(int questionListId, String personalUserId, String ownerUserId, String title, String content,
			int readhit, String postedDate) {
		super();
		this.questionListId = questionListId;
		this.personalUserId = personalUserId;
		this.ownerUserId = ownerUserId;
		this.title = title;
		this.content = content;
		this.readhit = readhit;
		this.postedDate = postedDate;
	}

	@Override
	public String toString() {
		return "QuestionsList [questionListId=" + questionListId + ", personalUserId=" + personalUserId
				+ ", ownerUserId=" + ownerUserId + ", title=" + title + ", content=" + content + ", readhit=" + readhit
				+ ", postedDate=" + postedDate + "]";
	}

	public int getQuestionListId() {
		return questionListId;
	}

	public void setQuestionListId(int questionListId) {
		this.questionListId = questionListId;
	}

	public String getPersonalUserId() {
		return personalUserId;
	}

	public void setPersonalUserId(String personalUserId) {
		this.personalUserId = personalUserId;
	}

	public String getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadhit() {
		return readhit;
	}

	public void setReadhit(int readhit) {
		this.readhit = readhit;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

}
