package com.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import com.db.ConnDb;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
/*
 * selectPosition(String number)  通过员工号查职位
 * Regist() 执行注册 插入数据库
 * */
import com.tools.SearchZHandToken;

public class Regist {

	public static String selectPosition(String number){
        String NotFound = null;
        String Number = null;//员工号
        String Position = null;//职位
        //String Password = null;
        String sql = "select * from allinfo  where  EmployeeNumber = '"+number+"'";
        //查询该账户信息
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	Number =rs.getString("EmployeeNumber");
            	Position = rs.getString("Position");//职位
                //获取职位和员工号
                return Position;//返回职位
            }else {
                return NotFound="查无此人";//没有此人信息。提示无法注册
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
            return NotFound="执行SQL语句出错！";
        }
   
    }
	
	//注册信息方法
	public static String Regist(String EmployeeNumber,String Password, String Name, String Sex,
			String PhoneNumber,String Email, String Ministry,String Position) {
        String NotFound = null;
        String Sucess=null;
        int level=0; //权限
        switch (Position)
        {
        case "董事长" :
        	level =4;
        break;
        
        case "总经理":
        	level =3;
        break;
        case "部门经理":
        	level =2;
        break;
        case "员工":
        	level =1;
        break;
        }
        String insql4 = "insert into admininfo values ("+EmployeeNumber
   			 +",'"+Password+"','"+Name+"',"+Sex+",'"+PhoneNumber
   			 +"','"+Email+"','"+Ministry+"',"+null+","+null+","+null+
   			 ","+level+","+System.currentTimeMillis()+")";
        
        System.out.println("insert into admininfo values ("+EmployeeNumber
      			 +",'"+Password+"','"+Name+"',"+Sex+",'"+PhoneNumber
       			 +"','"+Email+"','"+Ministry+"',"+0+","+null+","+0+
       			 ","+level+","+System.currentTimeMillis()+")");

        ConnDb connDb1 = new ConnDb();
        try {
        	if(!searchZH1(EmployeeNumber))
        	{
        	
        	 PreparedStatement ps1 =  connDb1.conn().prepareStatement(insql4);
             int rs1 =  ps1.executeUpdate();
             if(rs1==1)
             {
            	 System.out.println("插入注册信息成功！");
            	 return "注册成功";
             }
             else
             {   
            	 System.out.println("插入注册信息失败！");
            	 return "该员工号已注册！";
             }
        }
        	else
            {     
        	 System.out.println("该员工号已注册！");
           	 return "该员工号已注册！";
            }
        }
        catch (SQLException e) {
           
            e.printStackTrace();
            System.out.println("该员工号已注册！  catch抛出");
            return NotFound="该员工号已注册！";
        }

        } 
		        
public static boolean searchZH1 (String Number){
		
		
		String sql = "select * from admininfo where EmployeeNumber ='"+Number+"'";
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


	

