package cn.edu.ccnu.testsystem.bean; 

/**
 *封装用户信息的JavaBean对象模型
 * 
 */

public class User {
	private int id;
	private String userName;
	private String password;
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
 