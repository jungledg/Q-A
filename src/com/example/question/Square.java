package com.example.question;

public class Square {
	private int ImgId;
	private String Textview;
	public Square(int ImgId,String Textview){
		this.ImgId=ImgId;
		this.Textview=Textview;
	}
	public int getImgId() {
		return ImgId;
	}
	public void setImgId(int imgId) {
		ImgId = imgId;
	}
	public String getTextview() {
		return Textview;
	}
	public void setTextview(String textview) {
		Textview = textview;
	}
}
