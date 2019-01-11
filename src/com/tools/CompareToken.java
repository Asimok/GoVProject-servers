package com.tools;
/*
 * selectToken(String Token) 判断该token是否在表里
 * selectEmployeeNumberByToken(String Token)  通过token 查员工号
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnDb;

public class CompareToken {
	 public static boolean selectToken(String Token){
	        String sql = "select * from Token where Token= '"+Token+"'"; 
	        //查询该token信息
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	                return true;//
	            }else {
	                return false;//没有此人信息
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	            return false;
	        }
	    }
	 public static String selectEmployeeNumberByToken(String Token){
	        String sql = "select EmployeeNumber from Token where Token= '"+Token+"'"; 
	        //查询该账户信息
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	String EmployeeNumber=rs.getString("EmployeeNumber");
	                return EmployeeNumber;//返回正确密码
	            }else {
	                return "ERROR";//没有此人信息。提示未注册
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	            return "ERROR";
	        }
	    }
	 
	 public static String selectNameByEmployeeNumber(String EmployeeNumber){
	        String sql = "select Name from admininfo where EmployeeNumber= '"+EmployeeNumber+"'"; 
	        //查询该账户信息
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	String Name=rs.getString("Name");
	                return Name;//返回正确密码
	            }else {
	                return "ERROR";//没有此人信息。提示未注册
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	            return "ERROR";
	        }
	    }
	 
}
