package com.example.question;

public class Line {
	
	private int ImageId;
	private String lesson;
	private String teacher;
	private String place;
	private String month;
	private String day;
	
	public  Line(int ImageId,String lesson,String teacher,String place,String month,String day){
		this.setDay(day);
		this.setImageId(ImageId);
		this.setLesson(lesson);
		this.setMonth(month);
		this.setDay(day);
		this.setPlace(place);
		this.setTeacher(teacher);
	}

	public int getImageId() {
		return ImageId;
	}

	public void setImageId(int imageId) {
		ImageId = imageId;
	}

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}


	
	
}
