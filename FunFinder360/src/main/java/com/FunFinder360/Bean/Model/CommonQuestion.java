package com.FunFinder360.Bean.Model;

public class CommonQuestion {
	private int questionId;
	private String userId;
	private String title;
	private String content;
	private String postedDate;
	private int readhit;
	
	public CommonQuestion () {}
	
	public CommonQuestion(int question_id, String userId, String title, String content, String postedDate,
			int readhit) {
		super();
		this.questionId = question_id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.postedDate = postedDate;
		this.readhit = readhit;
	}

	@Override
	public String toString() {
		return "CommonQuestion [question_id=" + questionId + ", userId=" + userId + ", title=" + title + ", content="
				+ content + ", postedDate=" + postedDate + ", readhit=" + readhit + "]";
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public int getReadhit() {
		return readhit;
	}

	public void setReadhit(int readhit) {
		this.readhit = readhit;
	}

	
	
	
}
