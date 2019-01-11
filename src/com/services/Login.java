package com.services;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import com.db.ConnDb;
import com.tools.getUUID;
/*
 * selectNumber(String number) 通过账号查数据库密码
 * InsertToken(String zhanghu,String Token)  插入账户 token
 * UpdateToken(String zhanghu,String Token) 通过账户更新 token
 * selectadminpower(String number) 通过账户查权限
 * 
 * */
public class Login {

    public static String selectNumber(String number){
        String NotFound = null;
        String Number = null;
        String Password = null;
        String sql = "select * from admininfo  where  EmployeeNumber = '"+number+"'";
        //查询该账户信息
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	Number =rs.getString("EmployeeNumber");
            	Password = rs.getString("Password");
                //获取账号密码
                return Password;//返回正确密码
            }else {
                return null;//没有此人信息。提示未注册
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
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
        //查询该账户信息
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	
            	adminpower = rs.getString("AdminPower");
                //获取账号密码
                return adminpower;//返回正确密码
            }else {
                return NotFound="查无此人";//没有此人信息。提示未注册
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
        }
        return null;
    }
    
    
}