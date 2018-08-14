package cn.edu.just.utils;

public class Calendar {

	private String date;//招生时间
	private String school;//招生学校
	private String scene_person;//现场支撑人员
	private String back_person;//远程支撑人员
	private int property;
	
	public int getProperty() {
		return property;
	}
	public void setProperty(int property) {
		this.property = property;
	}
	public Calendar(String date, String school, String scene_person, String back_person) {
		super();
		this.date = date;
		this.school = school;
		this.scene_person = scene_person;
		this.back_person = back_person;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getScene_person() {
		return scene_person;
	}
	public void setScene_person(String scene_person) {
		this.scene_person = scene_person;
	}
	public String getBack_person() {
		return back_person;
	}
	public void setBack_person(String back_person) {
		this.back_person = back_person;
	}
	
	
}
