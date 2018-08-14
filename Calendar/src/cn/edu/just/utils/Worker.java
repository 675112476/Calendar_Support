package cn.edu.just.utils;

public class Worker {

	private Integer id;
	
	private String name;//工作人员姓名
	private String phone;//工作人员电话
	private String work_Num;
	public Worker(Integer id, String name, String phone, String work_Num) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.work_Num = work_Num;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWork_Num() {
		return work_Num;
	}
	public void setWork_Num(String work_Num) {
		this.work_Num = work_Num;
	}
	public Worker(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
