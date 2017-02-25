package cn.edu.ccnu.testsystem.entity;

public class Course {
	private int cid;
	private String name;
	private int tid;
	private String tname;
	private String major;
	private String type;
	
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getTname() {
		return tname;
	}
	
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTid() {
		return tid;
	}
	
	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		// TODO Auto-generated method stub
		this.type = type;
	}
}
