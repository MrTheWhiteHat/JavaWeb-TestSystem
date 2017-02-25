package cn.edu.ccnu.testsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import cn.edu.ccnu.testsystem.entity.Course;
import cn.edu.ccnu.testsystem.entity.Score;
import cn.edu.ccnu.testsystem.entity.Student;
import cn.edu.ccnu.testsystem.entity.Teacher;

public class ScoreDao {
	
	// 通过Cid查找学生并输入分数
	public List searchInputScoresByCid(int cid) {
		
		List scoreList = new ArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			PreparedStatement pStat = conn.prepareStatement("Select s.name as sname, s.sid as sid from studentscourses sc, students s " + 
					" where sc.cid = ? and sc.sid = s.sid");
			pStat.setInt(1, cid);
			ResultSet result = pStat.executeQuery();
			Score score;
			while (result.next()) {
				score = new Score();				
				score.setSname(result.getString("sname"));
				score.setSid(result.getInt("sid"));
				scoreList.add(score);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return scoreList;
		
	}	
	public List queryCourseBySid(int sid) {
		
		List couteaList = new ArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			PreparedStatement pStat = conn.prepareStatement("select * from courses c, studentscourses sc ,teachers t where t.tid = c.tid and sc.cid = c.cid and sid = ? ");
			pStat.setInt(1, sid);
			ResultSet result = pStat.executeQuery();
			Course courses;
			Teacher tea;
			while (result.next()) {
				courses = new Course();
				tea = new Teacher();
				courses.setCid(result.getInt("CID"));
				courses.setTid(result.getInt("TID"));
				courses.setName(result.getString("c.NAME")); //区别不同表中的同一字段属性
				courses.setMajor(result.getString("MAJOR"));
				courses.setType(result.getString("Type"));
				tea.setName(result.getString("t.NAME"));
				tea.setTitle(result.getString("TITLE"));
				couteaList.add(courses);
				couteaList.add(tea);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return couteaList;
		
	}	
	
	public List queryScoreBySid(int sid) {
		
		List scoresList = new ArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			PreparedStatement pStat = conn.prepareStatement("select * from studentscourses sc, courses c where sc.cid = c. cid and sid = ?");
			pStat.setInt(1, sid);
			ResultSet result = pStat.executeQuery();
			Score sco;
			Course cou;
			while (result.next()) {
				sco= new Score();
				cou = new Course();
				sco.setCid(result.getInt("CID"));
				sco.setSid(result.getInt("SID"));
				sco.setScore(result.getFloat("SCORE"));
				cou.setName(result.getString("NAME"));
				cou.setType(result.getString("TYPE"));
				scoresList.add(sco);
				scoresList.add(cou);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return scoresList;
		
	}
	
	public boolean saveScores(Map hm, int cid) {
		boolean result = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");
			
			PreparedStatement pStat = conn.prepareStatement("Update studentscourses set score = ? where sid = ? and cid = ?");
			
			Set sidSet = hm.keySet();
			Iterator iter = sidSet.iterator();
			int sid;
			float score;
			
			while (iter.hasNext()) {
				Object key = iter.next();
				sid = ((Integer) key).intValue();
				score = ((Float) hm.get(key)).floatValue();
				pStat.setFloat(1, score);
				pStat.setInt(2, sid);
				pStat.setInt(3, cid);
				pStat.addBatch();
			}
			pStat.executeBatch();
			
			conn.close();
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public List searchScoresByCid(int cid) {
		List scoreList = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/testsystem", "root", "toor");

			PreparedStatement pStat = conn
					.prepareStatement("Select s.name as sname,s.sid as sid,sc.score as score from studentscourses sc,students s "
							+ "where sc.cid = ? and sc.sid = s.sid");
			pStat.setInt(1, cid);
			ResultSet result = pStat.executeQuery();
			Score score;
			while (result.next()) {
				score = new Score();
				score.setSname(result.getString("sname"));
				score.setSid(result.getInt("sid"));
				score.setScore(result.getFloat("score"));
				scoreList.add(score);

			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scoreList;
	}
	
	public static void main(String[] args) {
		ScoreDao scoDao = new ScoreDao();
		List cour = scoDao.searchInputScoresByCid(2);
		Iterator iter = cour.iterator();
  		Score sco;
  		
  		while(iter.hasNext()) {
  			sco = (Score)iter.next();
		System.out.println("|   学号     |    姓名        |专业| 年龄 |年级| 密码");
	
			System.out.println(sco.getSname());
  		}	
		

	}

}