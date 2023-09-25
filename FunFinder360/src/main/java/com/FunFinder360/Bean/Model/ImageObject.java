package com.FunFinder360.Bean.Model;

public class ImageObject {
	private String image;
	private int order;
	
	public ImageObject() {}

	public ImageObject(String image, int order) {
		super();
		this.image = image;
		this.order = order;
	}

	@Override
	public String toString() {
		return "ImageObject [image=" + image + ", order=" + order + "]";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	};
	
	
}
