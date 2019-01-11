package com.tools;
/*
 * SearchNumber(String Token) 通过token查员工号
 * Token (String Number)  通过员工号查token
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnDb;

public class SearchZHandToken {
	public static String  SearchNumber(String Token){
		String NotFound = null;
		String EmployeeNumber = null;
		String sql = "select * from token where token ='"+Token+"'";
		ConnDb connDb = new ConnDb();	//连接数据库
		try {
		//      执行SQL语句
		      PreparedStatement ps =  connDb.conn().prepareStatement(sql);
		      ResultSet rs =  ps.executeQuery();
		      if (rs.next()) {
		         EmployeeNumber=rs.getString("EmployeeNumber").trim();
		        	return EmployeeNumber;
		        
		      }else {
		          return NotFound="查无此人";
		      }
		  } catch (SQLException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      System.out.println("执行SQL语句出错！");
		      return NotFound="查无此人";
		  }
		
	}

	//通过员工号查token
	
	public static String Token (String Number){
		
		String Token = null;
		String sql = "select * from token where EmployeeNumber ='"+Number+"'";
		ConnDb connDb = new ConnDb();	//连接数据库
		try {
		//      执行SQL语句
		      PreparedStatement ps =  connDb.conn().prepareStatement(sql);
		      ResultSet rs =  ps.executeQuery();
		      if (rs.next()) {
		    	  Token=rs.getString("token").trim();
		        	return Token;
		        
		      }else {
		          return Token="null";
		      }
		  } catch (SQLException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      System.out.println("执行SQL语句出错！");
		      return Token="null";
		  }
		
		
	}
	
	public static boolean searchZH (String Number){
		
		
		String sql = "select * from token where EmployeeNumber ='"+Number+"'";
		ConnDb connDb = new ConnDb();	//连接数据库
		try {
		//      执行SQL语句
		      PreparedStatement ps =  connDb.conn().prepareStatement(sql);
		      ResultSet rs =  ps.executeQuery();
		      if (rs.next()) {
		    	  
		        	return true;
		        
		      }else {
		          return false;
		      }
		  } catch (SQLException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      System.out.println("执行SQL语句出错！");
		      return false;
		  }
		
		
	}

}
