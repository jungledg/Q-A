package com.example.question;

public class Theme {
	
	private  int imageId;
	private  String name;
	
	
	public Theme(int imageId,String name)
	{
		this.imageId = imageId;
		this.name  =	name;
	}
	
	

	public  String getname(){
		return name;
	}
	
	public   int getImageId(){
		return imageId;
	}
}
