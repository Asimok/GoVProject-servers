package com.tools;
/*
 * SearchNumber(String Token) ͨ��token��Ա����
 * Token (String Number)  ͨ��Ա���Ų�token
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
		ConnDb connDb = new ConnDb();	//�������ݿ�
		try {
		//      ִ��SQL���
		      PreparedStatement ps =  connDb.conn().prepareStatement(sql);
		      ResultSet rs =  ps.executeQuery();
		      if (rs.next()) {
		         EmployeeNumber=rs.getString("EmployeeNumber").trim();
		        	return EmployeeNumber;
		        
		      }else {
		          return NotFound="���޴���";
		      }
		  } catch (SQLException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      System.out.println("ִ��SQL������");
		      return NotFound="���޴���";
		  }
		
	}

	//ͨ��Ա���Ų�token
	
	public static String Token (String Number){
		
		String Token = null;
		String sql = "select * from token where EmployeeNumber ='"+Number+"'";
		ConnDb connDb = new ConnDb();	//�������ݿ�
		try {
		//      ִ��SQL���
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
		      System.out.println("ִ��SQL������");
		      return Token="null";
		  }
		
		
	}
	
	public static boolean searchZH (String Number){
		
		
		String sql = "select * from token where EmployeeNumber ='"+Number+"'";
		ConnDb connDb = new ConnDb();	//�������ݿ�
		try {
		//      ִ��SQL���
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
		      System.out.println("ִ��SQL������");
		      return false;
		  }
		
		
	}

}
