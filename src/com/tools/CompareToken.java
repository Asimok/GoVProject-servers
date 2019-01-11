package com.tools;
/*
 * selectToken(String Token) �жϸ�token�Ƿ��ڱ���
 * selectEmployeeNumberByToken(String Token)  ͨ��token ��Ա����
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnDb;

public class CompareToken {
	 public static boolean selectToken(String Token){
	        String sql = "select * from Token where Token= '"+Token+"'"; 
	        //��ѯ��token��Ϣ
	        ConnDb connDb = new ConnDb();
	        try {
//	            ִ��SQL���
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	                return true;//
	            }else {
	                return false;//û�д�����Ϣ
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("ִ��SQL������");
	            return false;
	        }
	    }
	 public static String selectEmployeeNumberByToken(String Token){
	        String sql = "select EmployeeNumber from Token where Token= '"+Token+"'"; 
	        //��ѯ���˻���Ϣ
	        ConnDb connDb = new ConnDb();
	        try {
//	            ִ��SQL���
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	String EmployeeNumber=rs.getString("EmployeeNumber");
	                return EmployeeNumber;//������ȷ����
	            }else {
	                return "ERROR";//û�д�����Ϣ����ʾδע��
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("ִ��SQL������");
	            return "ERROR";
	        }
	    }
	 
	 public static String selectNameByEmployeeNumber(String EmployeeNumber){
	        String sql = "select Name from admininfo where EmployeeNumber= '"+EmployeeNumber+"'"; 
	        //��ѯ���˻���Ϣ
	        ConnDb connDb = new ConnDb();
	        try {
//	            ִ��SQL���
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	String Name=rs.getString("Name");
	                return Name;//������ȷ����
	            }else {
	                return "ERROR";//û�д�����Ϣ����ʾδע��
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("ִ��SQL������");
	            return "ERROR";
	        }
	    }
	 
}
