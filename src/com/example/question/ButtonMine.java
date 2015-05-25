package com.example.question;

public class ButtonMine	 {
	
	int MImage;
	String	name;
	Boolean sex;
	String major;
	
	
	public ButtonMine(int MImage,String	name,Boolean sex,String major) {
		this.major=major;
		this.MImage=MImage;
		this.name=name;
		this.sex=sex;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setSex(Boolean sex){
		this.sex=sex;
	}
	public void setMImage(int MImage){
		this.MImage=MImage;
	}
	public void setMajor(String major){
		this.major=major;
	}
	
	
	public String getName(){
		return name;
	}
	public Boolean getSex(){
		return sex;
	}
	public String getMajor(){
		return major;
	}
	public int getMimage(){
		return MImage;
	}
	
	
}
