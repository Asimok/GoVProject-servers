package com.services;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import com.db.ConnDb;
import com.tools.getUUID;
/*
 * selectNumber(String number) ͨ���˺Ų����ݿ�����
 * InsertToken(String zhanghu,String Token)  �����˻� token
 * UpdateToken(String zhanghu,String Token) ͨ���˻����� token
 * selectadminpower(String number) ͨ���˻���Ȩ��
 * 
 * */
public class Login {

    public static String selectNumber(String number){
        String NotFound = null;
        String Number = null;
        String Password = null;
        String sql = "select * from admininfo  where  EmployeeNumber = '"+number+"'";
        //��ѯ���˻���Ϣ
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	Number =rs.getString("EmployeeNumber");
            	Password = rs.getString("Password");
                //��ȡ�˺�����
                return Password;//������ȷ����
            }else {
                return null;//û�д�����Ϣ����ʾδע��
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ִ��SQL������");
        }
        return null;
    }
    public static void InsertToken(String zhanghu,String Token)
    {
    	
    	
    	String sql="insert into token values ('"+zhanghu+"','"+Token+"')";
    	System.out.println("insert into admininfo values ('"+zhanghu+"','"+Token+"')");
    	ConnDb connDb = new ConnDb();
    	  try {
         	 PreparedStatement ps1 =  connDb.conn().prepareStatement(sql);
              int rs1 =  ps1.executeUpdate();
         }
         catch (SQLException e) {
            
             e.printStackTrace();
             
         }
    }
    public static void UpdateToken(String zhanghu,String Token)
    {
    	
    	
    	String sql="update token set token = '"+Token+"' where EmployeeNumber = '"+zhanghu+"'";
    	System.out.println("update token set token = '"+Token+"' where EmployeeNumber = '"+zhanghu+"'");
    	ConnDb connDb = new ConnDb();
    	  try {
         	 PreparedStatement ps1 =  connDb.conn().prepareStatement(sql);
              int rs1 =  ps1.executeUpdate();
         }
         catch (SQLException e) {
            
             e.printStackTrace();
             
         }
    }
    
    public static String selectadminpower(String number){
        String NotFound = null;
        String adminpower = null;
      
        String sql = "select * from admininfo  where  EmployeeNumber = '"+number+"'";
        //��ѯ���˻���Ϣ
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	
            	adminpower = rs.getString("AdminPower");
                //��ȡ�˺�����
                return adminpower;//������ȷ����
            }else {
                return NotFound="���޴���";//û�д�����Ϣ����ʾδע��
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ִ��SQL������");
        }
        return null;
    }
    
    
}