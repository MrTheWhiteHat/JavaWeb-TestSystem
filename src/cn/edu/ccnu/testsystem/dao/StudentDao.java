package cn.edu.ccnu.testsystem.dao;
import java.util.*;
import java.sql.*;
import cn.edu.ccnu.testsystem.entity.*;

public class StudentDao {
	
	// 增加学生
	public int addStudent(Student stu) {
		
		int result = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			PreparedStatement pStat = conn.prepareStatement("Insert into student (SID, NAME, AGE, MAJOR, GRADE, GENDER, PASSWORD) values (?, ?, ?, ?, ?, ?, ?)");
			pStat.setInt(1, stu.getSid());
			pStat.setString(2, stu.getName());
			pStat.setInt(3, stu.getAge());
			pStat.setString(4, stu.getMajor());
			pStat.setInt(5, stu.getGrade());
			pStat.setString(6, stu.getGender());
			pStat.setString(7, stu.getPassword());
			
			result = pStat.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 修改专业
	public int modifyMajor(int sid, String newMajor) {
		
		int result = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			PreparedStatement pStat = conn.prepareStatement("Update students set MAJOR = ?" + " where SID = ? ");
			pStat.setString(1, newMajor);
			pStat.setInt(2, sid);
			
			result = pStat.executeUpdate(); // 执行数据更新
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 通过专业搜索学生
	@SuppressWarnings("rawtypes")
	public List searchStudentByMajor(String major) {
		
		List studentList = new ArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			PreparedStatement pStat = conn.prepareStatement("Select * from students" + "where MAJOR = ?");
			pStat.setString(1, major);
			
			ResultSet result = pStat.executeQuery(); // 执行查询
			
			Student student;
			
			while (result.next()) {
				student = new Student();
				student.setSid(result.getInt("SID"));
				student.setName(result.getString("NAME"));
				student.setMajor(major);
				student.setAge(result.getInt("AGE"));
				student.setGrade(result.getInt("GRADE"));
				student.setGender(result.getString("GENDER"));
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
		
	}
	
	//
	public Student searchStudentBySid(int sid){
		
		Student student  = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			PreparedStatement pStat = conn.prepareStatement("Select * from students " + " where SID = ?"); 
			// 双引号内一定要留空格，否则sql语句就会变成“studentswhere”，造成sql错误。
			pStat.setInt(1, sid);
			
			ResultSet result = pStat.executeQuery();
			
			if (result.next()) {
				student = new Student();
				student.setSid(sid);
				student.setName(result.getString("NAME"));
				student.setMajor(result.getString("MAJOR"));
				student.setAge(result.getInt("AGE"));
				student.setGrade(result.getInt("GRADE"));
				student.setGender(result.getString("GENDER"));	
				student.setPassword(result.getString("PASSWORD"));
			}
			
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
       return student;		
	}

	/**
	 * test StudentDao!!!
	 * @param args
	 */
	public static void main(String[] args) {
		StudentDao stuDao = new StudentDao();
		Student stu = stuDao.searchStudentBySid(2014214666);
		System.out.println("|   学号     |    姓名        |专业| 年龄 |年级| 密码");
	
			System.out.println(stu.getSid()+"    "+
			             stu.getName()+"    "+stu.getMajor()+"    "+
					    stu.getAge()+"    "+stu.getGrade() + "   "+ stu.getPassword());
			
		

	}

}
