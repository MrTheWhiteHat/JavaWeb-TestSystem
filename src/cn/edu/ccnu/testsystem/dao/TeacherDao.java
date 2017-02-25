package cn.edu.ccnu.testsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import cn.edu.ccnu.testsystem.entity.Score;
import cn.edu.ccnu.testsystem.entity.Student;
import cn.edu.ccnu.testsystem.entity.Teacher;

@SuppressWarnings("unused")
public class TeacherDao {
	
	// 通过TID搜索查询老师
	public Teacher searchTeacherByTid(int tid) {
		
		Teacher teacher = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			
			PreparedStatement pStat = conn.prepareStatement("Select * from teachers " + " where TID = ? ");
			pStat.setInt(1, tid);
			
			ResultSet result = pStat.executeQuery();
			
			if (result.next()) {
				teacher = new Teacher();
				teacher.setTid(tid);
				teacher.setName(result.getString("NAME"));
				teacher.setAge(result.getInt("AGE"));
				teacher.setMajor(result.getString("MAJOR"));
				teacher.setTitle(result.getString("TITLE"));
				teacher.setPassword(result.getString("PASSWORD"));
				teacher.setGender(result.getString("GENDER"));
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return teacher;
	}

	public Teacher searchTeacherByCid(int cid) {
		// TODO Auto-generated method stub
		Teacher teacher = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			
			PreparedStatement pStat = conn.prepareStatement("Select * from teachers t , courses c" + " where c.cid = ? and c.tid = t.tid ");
			pStat.setInt(1, cid);
			
			ResultSet result = pStat.executeQuery();
			
			if (result.next()) {
				teacher = new Teacher();
				teacher.setTid(result.getInt("t.TID"));
				teacher.setName(result.getString("t.NAME"));
				teacher.setAge(result.getInt("AGE"));
				teacher.setMajor(result.getString("t.MAJOR"));
				teacher.setTitle(result.getString("TITLE"));
				teacher.setPassword(result.getString("PASSWORD"));
				teacher.setGender(result.getString("GENDER"));
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return teacher;
	}
	
	public static void main(String[] args) {
		TeacherDao teaDao = new TeacherDao();
		Teacher tea = teaDao.searchTeacherByCid(2);
  		
		System.out.println("|   学号     |    姓名        |专业| 年龄 |年级| 密码");
	
			System.out.println(tea.getTid()+tea.getGender()+tea.getMajor()+tea.getName()+tea.getPassword()+tea.getTitle());
  		}	
		
}
