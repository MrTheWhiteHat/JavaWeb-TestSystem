package cn.edu.ccnu.testsystem.bean; 

/**
 *��װ�û���Ϣ��JavaBean����ģ��
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
 