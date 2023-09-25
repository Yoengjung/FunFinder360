package com.FunFinder360.Bean.Model;

public class ContentObject {
	private String content;
	private int order;
	
	public ContentObject () {}

	public ContentObject(String content, int order) {
		super();
		this.content = content;
		this.order = order;
	}

	@Override
	public String toString() {
		return "ContentObject [content=" + content + ", order=" + order + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
