package cn.edu.ccnu.testsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.ccnu.testsystem.entity.Course;
import cn.edu.ccnu.testsystem.entity.Student;

public class CourseDao {
	
	// 通过Tid搜索课程
	public List searchCoursesByTid(int tid) {
		
		List courseList = new ArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			PreparedStatement pStat = conn.prepareStatement("Select * from courses " + "where TID = ? " );
			pStat.setInt(1, tid);
			ResultSet result = pStat.executeQuery();
			Course course;
			while (result.next()) {
				course = new Course();
				course.setCid(result.getInt("CID"));
				course.setName(result.getString("NAME"));
				course.setTid(tid);
				courseList.add(course);
				
			}
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return courseList;
	}
	
	// 通过 Cid搜索课程
	public Course searchCourseByCid(int cid) {
		
		Course course = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			PreparedStatement pStat = conn.prepareStatement("Select c.name as cname, t.name as tname, c.major as cmajor " + 
					"from courses c, teachers t where c.cid = ? and c.tid = t.tid");
			pStat.setInt(1, cid);
			
			ResultSet result = pStat.executeQuery();
			
			
			if (result.next()) {
				course = new Course();
				course.setCid(cid);
				course.setName(result.getString("cname"));
				course.setMajor(result.getString("cmajor"));
				course.setTname(result.getString("tname"));
				
			}
			
			conn.close();
		}	catch (Exception e) {
			e.printStackTrace();
		}
		
		return course;
	}
}