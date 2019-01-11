package com.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnDb;

public class Logout {
	public static boolean DeleteToken(String Token){
        
            ConnDb connDb = new ConnDb();
            //添加增添的房间信息
            String sql = "delete from token where token = '"+Token+"'";
               System.out.println("delete from token where token = '"+Token+"'");
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
                    System.out.println("rs"+rs);
                    if(rs==1)
                	{
                	return true;
                	}
                else
                {
                	return false;
                }

               }
               catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("ERROR");
                   return false;
               }             
               
	}          
}
